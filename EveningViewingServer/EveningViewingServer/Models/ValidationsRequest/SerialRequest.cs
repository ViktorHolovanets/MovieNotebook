using System.ComponentModel.DataAnnotations;
using EveningViewingServer.Models.DB;

namespace EveningViewingServer.Models.ValidationsRequest
{
    public class SerialRequest
    {
        [Required]
        public Movie Serial { get; set; }
        [Required]
        public int? Season { get; set; }
        public int? Episode { get; set; }
    }
}
