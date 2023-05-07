using System.ComponentModel.DataAnnotations;

namespace EveningViewingServer.Models.DB
{
    public class Movie
    {
        [Key]
        public string imdbID { get; set; }
        public string Title { get; set; }
        public string Year { get; set; }
        public string Type { get; set; }
        public string Poster { get; set; }
    }

}
