namespace EveningViewingServer.Services.HttpServices.InterfaceHttp
{
    public interface IHttpService
    {
        public Task<string> ResponseAsync(string url);
        public Task ResponseAsync(string url, Action<string> action);
    }
}
