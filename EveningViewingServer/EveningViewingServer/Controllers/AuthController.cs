using EveningViewingServer.Models.DB;
using EveningViewingServer.Models.ValidationsRequest;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Security.Cryptography;
using System.Text;
using EveningViewingServer.Models.Helper;

namespace EveningViewingServer.Controllers
{
    [Route("api")]
    [ApiController]
    public class AuthController : Controller
    {
        private DbContextRepositories _dbContext;
        private AuthorizationHelper _authorizationHelper;
        public AuthController(IConfiguration configuration, DbContextRepositories db)
        {
            _authorizationHelper=new AuthorizationHelper(configuration);
             _dbContext = db;
        }

        [AllowAnonymous]
        [HttpPost("register")]
        public async Task<IActionResult> Register(UserRegisterDto userDto)
        {
            if (await _dbContext.Users.AnyAsync(u => u.Email == userDto.Email))
            {
                return new ConflictObjectResult(new
                {
                    message = "A user with this mail is already registered."
                });
            }

            var user = new User
            {
                UserName = userDto.UserName,
                Email = userDto.Email,
            };

            var passwordHasher = new PasswordHasher<User>();
            user.Password = passwordHasher.HashPassword(user, userDto.Password);

            await _dbContext.Users.AddAsync(user);
            await _dbContext.SaveChangesAsync();

            var token = _authorizationHelper.GenerateAccessToken(user);

            return Ok(new
            {
                Token = token
            });
        }
        [AllowAnonymous]
        [HttpPost("login")]
        public async Task<IActionResult> Login(LoginModel userDto)
        {
            var user = await _dbContext.Users.FirstOrDefaultAsync(u => u.Email == userDto.Email);
            if (user == null)
            {
                return Unauthorized("Invalid mail");
            }

            var passwordHasher = new PasswordHasher<User>();
            var result = passwordHasher.VerifyHashedPassword(user, user.Password, userDto.Password);
            if (result != PasswordVerificationResult.Success)
            {
                return Unauthorized("Invalid password");
            }

            var token = _authorizationHelper.GenerateAccessToken(user);
            return Ok(new
            {
                name=user.UserName,
                Token = token,
            });
        }
    }
   
}
