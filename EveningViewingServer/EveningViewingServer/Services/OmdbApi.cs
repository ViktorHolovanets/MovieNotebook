using EveningViewingServer.Models.DB;
using EveningViewingServer.Models.ValidationsRequest;
using EveningViewingServer.Services.HttpServices;
using EveningViewingServer.Services.HttpServices.InterfaceHttp;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;


namespace EveningViewingServer.Services
{
    public class OmdbApi
    {
        private string _apiKey = "d87cdbca";
        private string _url = "https://www.omdbapi.com/";
        IHttpService IHttpService { get; set; } = new HttpService();
        private DbContextRepositories _dbContext;

        public OmdbApi(DbContextRepositories dbContext)
        {
            this._dbContext = dbContext;
        }

        public async Task<ActionResult<List<Movie>>> GetMovies(string url)
        {
            return JsonConvert.DeserializeObject<List<Movie>>(await RequestOmdb(url));
        }

        public string BuildUrl(MovieRequest request)
        {
            return $"{_url}?apikey={_apiKey}" +
                   $"{(request.Page != null ? $"&page={request.Page}" : "")}" +
                   $"{(request.Type != null ? $"&type={request.Type}" : "")}" +
                   $"{(request.Plot != null ? $"&plot={request.Plot}" : "")}" +
                   $"{(request.Search != null ? $"&s={request.Search}" : "")}" +
                   $"{(request.Year != null ? $"&y={request.Year}" : "")}" +
                   $"{(request.Season != null ? $"&season={request.Season}" : "")}" +
                   $"{(request.Id != null ? $"&i={request.Id}" : "")}";
        }

        public async Task<UserViews?> AddMovies(Movie movie, string email)
        {
            User? user =await _dbContext.Users.FirstOrDefaultAsync(u => u.Email == email);
            if (user == null)
            {
                return null;
            }

            
            FullInfoMovie? myViews = await GetFullInfoAsync(movie);

            UserViews? userViews = await _dbContext.UserViews.FirstOrDefaultAsync(us => us.User == user && us.myViews==myViews);
            if (userViews != null)
            {
                //userViews.User = null;
                //userViews.myViews.Movie=movie;
                //return userViews;
                return null;
            }

            userViews = new UserViews()
            {
                User = user,
                myViews = myViews
            };
            _dbContext.UserViews.Add(userViews);
            await InfoSerialAsync(userViews, 1, 1, false);
            userViews.User = null;
            return userViews;
        }

        private async Task<string> RequestOmdb(string url)
        {
            var response = await IHttpService.ResponseAsync(url);
            var searchResult = JsonConvert.DeserializeObject<dynamic>(response);
            if (searchResult.ContainsKey("Search"))
            {
                return searchResult.Search.ToString();
            }

            return searchResult.ToString();
        }

        private async Task<bool> InfoSerialAsync(UserViews userViews, int? season, int? episode, bool isView=true)
        {
            var movie = userViews.myViews.Movie;
            if (movie.Type != "series" || userViews.myViews.TotalSeasons < season)
            {
                await _dbContext.SaveChangesAsync();
                return false;
            }

            Serial? serial= await GetInfoSerialTask(movie,season);

            if (serial.Episodes < episode)
            {
                return false;
            }

            userViews.Episode = episode;
            userViews.Seasons = season;
            userViews.isView=isView;
            _dbContext.UserViews.Update(userViews);
            await _dbContext.SaveChangesAsync();
            return true;
        }

        public async Task<bool> InfoSerialAsync(Movie movie, int? season, int? episode)
        {
            UserViews? userViews = await _dbContext.UserViews
                .Include(uv => uv.myViews)
                .ThenInclude(m => m.Movie)
                .FirstOrDefaultAsync(uv => uv.myViews.Movie == movie);
            if (userViews == null)
            {
                return false;
            }
            return await InfoSerialAsync(userViews, season, episode);
        }
        public async Task<Serial?> GetInfoSerialTask(Movie movie, int? season)
        {
            Serial? serial = await _dbContext.Serials
                .FirstOrDefaultAsync(s => s.Movie.imdbID == movie.imdbID && s.Season == season);

            if (serial == null)
            {
                var json = await RequestOmdb(BuildUrl(new MovieRequest() { Id = movie.imdbID, Season = season }));

                try
                {
                    serial = new Serial
                    {
                        Season = Convert.ToInt32(JObject.Parse(json)["Season"]),
                        Episodes = JObject.Parse(json)["Episodes"].Count(),
                        Movie = await _dbContext.Movies.FirstOrDefaultAsync(m=>m==movie)??movie
                    };
                    _dbContext.Serials.Add(serial);
                    await _dbContext.SaveChangesAsync();
                }
                catch (Exception e)
                {
                    return null;
                }
            }
            return serial; 
        }

        public async Task<FullInfoMovie?> GetFullInfoAsync(Movie movie)
        {
            FullInfoMovie? myViews =
                await _dbContext.FullInfoMovie.Include(mv => mv.Movie).FirstOrDefaultAsync(mv => mv.Movie.imdbID == movie.imdbID);
            if (myViews == null)
            {
                var json = await RequestOmdb(BuildUrl(new MovieRequest() { Id = movie.imdbID }));
                myViews = JsonConvert.DeserializeObject<FullInfoMovie>(json);
                if (myViews != null)
                    myViews.Movie = movie;
            }

            return myViews;
        }
        public async Task<bool> MarkedPointTask(MarkedPointRequest movie, string email)
        {
            User? user = await _dbContext.Users.FirstOrDefaultAsync(u => u.Email == email);
            if (user == null)
            {
                return false;
            }

            UserViews? userViews = await _dbContext.UserViews
                .FirstOrDefaultAsync(uv=>uv.User==user && uv.myViews.Movie.imdbID==movie.IdMovie);
            if (userViews == null)
            {
                return false;
            }

            return await InfoSerialAsync(userViews, movie.Season, movie.Episodes);
        }

        public async Task<IEnumerable<dynamic>> GetMyMoviesTask(string email)
        {
            User? user = await _dbContext.Users.SingleOrDefaultAsync(u => u.Email == email);
            if (user == null)
            {
                return Enumerable.Empty<dynamic>();
            }
            var views =  _dbContext.UserViews
                .Where(v => v.User == user)
                .Select(uv => new
                {
                    uv.isView,
                    uv.Seasons,
                    uv.Episode,
                    myViews = _dbContext.FullInfoMovie
                        .Include(mv => mv.Movie)
                        .Where(mv => mv == uv.myViews).
                        Select(v=>new
                        {
                            v.Movie,
                            v.Plot,
                            v.TotalSeasons,
                            v.Actors,
                            v.Genre
                        })
                        .FirstOrDefault()

                });
            return views;
        }

        public async Task<bool> DeleteMovie(Movie movie, string email)
        {
            User? user = await _dbContext.Users.FirstOrDefaultAsync(u => u.Email == email);
            if (user == null)
            {
                return false;
            }
            var r= _dbContext.UserViews.FirstOrDefault(v=>v.User == user&& v.myViews.Movie==movie);
            if (r != null)
            {
                _dbContext.UserViews.Remove(r);
                await _dbContext.SaveChangesAsync();
                return true;
            }
            return false;
        }
    }
}