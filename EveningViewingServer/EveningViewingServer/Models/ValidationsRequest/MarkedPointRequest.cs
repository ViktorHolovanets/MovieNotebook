using System.ComponentModel.DataAnnotations;

namespace EveningViewingServer.Models.ValidationsRequest
{
    public class MarkedPointRequest
    {
        [Required]
        public string? IdMovie { get; set; }
        [Required]
        public int? Season  { get; set; }
        [Required]
        public int? Episodes { get; set; }
    }
}
