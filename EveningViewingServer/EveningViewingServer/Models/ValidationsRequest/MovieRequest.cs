namespace EveningViewingServer.Models.ValidationsRequest
{
    public class MovieRequest
    {
        public string? Search { get; set; }
        public int? Page { get; set; } = null;
        public string? Type { get; set; } = null;
        public string? Plot { get; set; } = null;
        public int? Year { get; set; } = null;
        public string? Id { get; set; } = null;
        public int? Season { get; set; }
    }
}
