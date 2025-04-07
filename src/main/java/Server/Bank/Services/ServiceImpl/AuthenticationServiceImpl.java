package Server.Bank.Services.ServiceImpl;

import Server.Bank.DTO.Request.LoginRequestDTO;
import Server.Bank.DTO.Request.RegisterRequestDTO;
import Server.Bank.Models.Role;
import Server.Bank.Models.User;
import Server.Bank.Repository.RoleRepository;
import Server.Bank.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public AuthenticationServiceImpl(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User signup(RegisterRequestDTO dto) {
        Role role = roleRepository.findByRole("USER").orElseGet(() -> roleRepository.save(Role.builder().role("USER").build()));
        User user = User.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(role)
                .build();

        return userRepository.save(user);
    }

    public User authenticate(LoginRequestDTO dto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getEmail(),
                            dto.getPassword()
                    )
            );
        } catch (DisabledException e) {
            throw new RuntimeException("Your account is blocked!");
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid email or password!");
        }
        return userRepository.findByEmail(dto.getEmail())
                .orElseThrow();
    }
}
