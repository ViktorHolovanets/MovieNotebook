using System.ComponentModel.DataAnnotations;

namespace EveningViewingServer.Models.DB
{
    public class FullInfoMovie
    {
        public Guid Id { get; set; }= Guid.NewGuid();
        public string? Released { get; set; }
        public string? Runtime { get; set; }
        public string? Genre { get; set; }
        public string? Director { get; set; }
        public string? Writer { get; set; }
        public string? Actors { get; set; }
        public string? Plot { get; set; }
        public string? Language { get; set; }
        public string? Country { get; set; }
        public string? Awards { get; set; }
        public string? Type { get; set; }
        public int? TotalSeasons { get; set; }
        [Required]
        public Movie Movie { get; set; }        
    }
}
