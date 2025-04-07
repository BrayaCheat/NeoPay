package Server.Bank.Services;

import Server.Bank.DTO.Request.ChangePasswordRequestDTO;
import Server.Bank.DTO.Request.RegisterRequestDTO;
import Server.Bank.Models.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    // For user
    User getUser();
    void changePassword(Long userId, ChangePasswordRequestDTO dto);
    void forgotPassword();

    // For admin
    List<User> allUsers(Pageable pageable);
    User createUser(RegisterRequestDTO dto);
    User updateUser(Long userId, RegisterRequestDTO dto);
    void deleteUser(Long userId);
    void blockUser(Long userId);
    void unblockUser(Long userId);

}
