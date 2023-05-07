 using System.ComponentModel.DataAnnotations;

namespace EveningViewingServer.Models.DB
{
    public class UserViews
    {
        [Key]
        public Guid Id { get; set; }= Guid.NewGuid();
        public int? Seasons { get; set; }
        public int? Episode { get; set; }
        public bool isView { get; set; } = false;
        public User User { get; set; }
        public FullInfoMovie myViews { get; set; }
    }
}
