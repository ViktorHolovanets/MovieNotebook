using System.ComponentModel.DataAnnotations;

namespace EveningViewingServer.Models.DB
{
    public class Serial
    {
        [Key]
        public Guid Id { get; set; }= Guid.NewGuid();
        public Movie Movie { get; set; }
        public int Season { get; set;}
        public int Episodes { get; set;}
    }
}
