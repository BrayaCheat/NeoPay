package Server.Bank.Services.ServiceImpl;

import Server.Bank.DTO.Request.RegisterRequestDTO;
import Server.Bank.Exceptions.NotFoundException;
import Server.Bank.Models.User;
import Server.Bank.Repository.UserRepository;
import Server.Bank.Services.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationServiceImpl authenticationService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, AuthenticationServiceImpl authenticationService, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> allUsers(Pageable pageable) {
        List<User> listUsers = userRepository.findAll();
//        System.out.println(listUsers);
        return listUsers;
    }

    @Override
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    @Override
    public User createUser(RegisterRequestDTO dto) {
        return authenticationService.signup(dto);
    }

    @Override
    public User updateUser(Long userId, RegisterRequestDTO dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id: " + userId + " not found!"));
        user.setEmail(dto.getEmail() != null ? dto.getEmail() : user.getEmail());
        user.setFullName(dto.getFullName() != null ? dto.getFullName() : user.getFullName());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        if(!userRepository.existsById(userId)){
            throw new RuntimeException("User with id: " + userId + " not found!");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public void blockUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User id: " + userId + " not found!"));
        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User id: " + userId + " not found!"));
        if(!passwordEncoder.matches(oldPassword, user.getPassword())){
            throw new RuntimeException("Old password is incorrect!");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void forgotPassword() {

    }
}
