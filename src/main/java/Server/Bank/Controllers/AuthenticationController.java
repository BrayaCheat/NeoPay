package Server.Bank.Controllers;

import Server.Bank.DTO.Request.LoginRequestDTO;
import Server.Bank.DTO.Request.RegisterRequestDTO;
import Server.Bank.DTO.Response.LoginResponseDTO;
import Server.Bank.Models.User;
import Server.Bank.Services.ServiceImpl.AuthenticationServiceImpl;
import Server.Bank.Services.ServiceImpl.JwtServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtServiceImpl jwtService;

    private final AuthenticationServiceImpl authenticationService;

    public AuthenticationController(JwtServiceImpl jwtService, AuthenticationServiceImpl authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterRequestDTO registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.status(201).body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody LoginRequestDTO loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponseDTO loginResponse = LoginResponseDTO.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();
        return ResponseEntity.status(201).body(loginResponse);
    }
}
