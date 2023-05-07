using EveningViewingServer.Services.HttpServices.InterfaceHttp;

namespace EveningViewingServer.Services.HttpServices
{
    public class HttpService : IHttpService
    {
        public async Task<string> ResponseAsync(string url)
        {
            using (var httpClient = new HttpClient())
            {
                var response = await httpClient.GetAsync(url);
                return await response.Content.ReadAsStringAsync();
            }
        }

        public async Task ResponseAsync(string url, Action<string> action)
        {
            using (var httpClient = new HttpClient())
            {
                var response = await httpClient.GetAsync(url);
                action.Invoke(await response.Content.ReadAsStringAsync());
            }
        }
    }
}
