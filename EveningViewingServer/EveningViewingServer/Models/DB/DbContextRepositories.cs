using Microsoft.EntityFrameworkCore;

namespace EveningViewingServer.Models.DB
{
    public class DbContextRepositories: DbContext
    {
        public virtual DbSet<User> Users { get; set; }
        public virtual DbSet<Movie> Movies { get; set; }
        public virtual DbSet<FullInfoMovie> FullInfoMovie { get; set; }
        public virtual DbSet<UserViews> UserViews { get; set; }
        public virtual DbSet<Serial> Serials { get; set; }
        public DbContextRepositories(DbContextOptions<DbContextRepositories> options)
            : base(options) => Database.EnsureCreated();
    }
}
