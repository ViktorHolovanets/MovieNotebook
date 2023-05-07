using EveningViewingServer.Models.DB;
using EveningViewingServer.Models.ValidationsRequest;
using EveningViewingServer.Services;
using EveningViewingServer.Services.HttpServices.InterfaceHttp;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.IdentityModel.Tokens;
using Newtonsoft.Json;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using EveningViewingServer.Models.Helper;

namespace EveningViewingServer.Controllers
{

    public class MovieController : Controller
    {
        private readonly IHttpService _httpService;
        private OmdbApi _mdbApi;
        private AuthorizationHelper _authorizationHelper;
        public MovieController(IHttpService httpService, DbContextRepositories db, IConfiguration configuration)
        {
            _authorizationHelper = new AuthorizationHelper(configuration);
            _httpService = httpService;
            _mdbApi = new OmdbApi(db);
        }
        [Authorize]
        [HttpPost("search")]
        public async Task<IActionResult> GetMovies([FromBody] MovieRequest request)
        {
            try
            {
                return Ok(await _mdbApi.GetMovies(_mdbApi.BuildUrl(request)));
            }
            catch (Exception e)
            {
                return BadRequest();
            }
        }

        [Authorize]
        [HttpPost("new/views")]
        public async Task<IActionResult> AddMovie([FromBody] Movie movie)
        {
            string? email = _authorizationHelper.GetEmailFromToken(HttpContext);
            if (email == null)
            {
                return Unauthorized();
            }
            UserViews? myViews = await _mdbApi.AddMovies(movie, email);

            return myViews!=null?Ok(myViews):BadRequest(new {message= "already viewing" });
        }
        [Authorize]
        [HttpPost("info_movie")]
        public async Task<IActionResult> FullInfo([FromBody] Movie movie)
        {
            return Ok(await _mdbApi.GetFullInfoAsync(movie));
        }

        [Authorize]
        [HttpPost("marked")]
        public async Task<IActionResult> MarketPoint([FromBody] MarkedPointRequest movie)
        {
            string? email = _authorizationHelper.GetEmailFromToken(HttpContext);
            if (email == null)
            {
                return Unauthorized();
            }
            return Ok(await _mdbApi.MarkedPointTask(movie, email));
        }
        [Authorize]
        [HttpPost("serial/season")]
        public async Task<IActionResult> InfoSerialTask([FromBody] SerialRequest request)
        {
            var tmp = await _mdbApi.GetInfoSerialTask(request.Serial, request.Season);
            return Ok(tmp);
        }
        [Authorize]
        [HttpPost("update/serial")]
        public async Task<IActionResult> UpSerialTask([FromBody] SerialRequest request)
        {
            return Ok(new { isUpdate = await _mdbApi.InfoSerialAsync(request.Serial, request.Season, request.Episode) });
        }
        [Authorize]
        [HttpPost("views")]
        public async Task<IActionResult> MyViewsTask()
        {
            string? email = _authorizationHelper.GetEmailFromToken(HttpContext);
            if (email == null)
            {
                return Unauthorized();
            }
            return Ok(await _mdbApi.GetMyMoviesTask(email));
        }
        [Authorize]
        [HttpPost("delete")]
        public async Task<IActionResult> MarketPoint([FromBody] Movie movie)
        {
            string? email = _authorizationHelper.GetEmailFromToken(HttpContext);
            if (email == null)
            {
                return Unauthorized();
            }
            return Ok(new {isDelete= await _mdbApi.DeleteMovie(movie, email) });
        }
    }
}
