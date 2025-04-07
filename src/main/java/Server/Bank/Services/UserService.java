package Server.Bank.Services;

import Server.Bank.DTO.Request.RegisterRequestDTO;
import Server.Bank.Models.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    // For user
    List<User> allUsers(Pageable pageable);
    User getUser();
    void changePassword(Long userId, String oldPassword, String newPassword);
    void forgotPassword();

    // For admin
    User createUser(RegisterRequestDTO dto);
    User updateUser(Long userId, RegisterRequestDTO dto);
    void deleteUser(Long userId);
    void blockUser(Long userId);

}
