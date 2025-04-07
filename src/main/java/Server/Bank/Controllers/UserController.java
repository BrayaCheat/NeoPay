package Server.Bank.Controllers;

import Server.Bank.DTO.Request.ChangePasswordRequestDTO;
import Server.Bank.DTO.Request.RegisterRequestDTO;
import Server.Bank.Models.User;
import Server.Bank.Services.ServiceImpl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users Management")
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    // For user
    @GetMapping("/users/me")
    public ResponseEntity<User> getUser() {
        return ResponseEntity.status(200).body(userService.getUser());
    }

    @PostMapping("/users/{userId}/change-password")
    public ResponseEntity<?> changePassword(@PathVariable Long userId, @Valid @RequestBody ChangePasswordRequestDTO dto){
        userService.changePassword(userId, dto);
        return ResponseEntity.status(201).body("Password changed.");
    }

    // For admin
    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> allUser(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return ResponseEntity.status(200).body(userService.allUsers(pageable));
    }

    @PostMapping("/admin/users")
    public ResponseEntity<User> createUser(@RequestBody RegisterRequestDTO dto) {
        return ResponseEntity.status(201).body(userService.createUser(dto));
    }

    @PutMapping("/admin/users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody RegisterRequestDTO dto){
        return ResponseEntity.status(201).body(userService.updateUser(userId, dto));
    }

    @DeleteMapping("/admin/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.status(200).body("User id: " + userId + " deleted.");
    }

    @PostMapping("/admin/users/{userId}/block-user")
    public ResponseEntity<?> blockUser(@PathVariable Long userId){
        userService.blockUser(userId);
        return ResponseEntity.status(200).body("User id: " + userId + " blocked.");
    }

    @PostMapping("/admin/users/{userId}/unblock-user")
    public ResponseEntity<?> unblockUser(@PathVariable Long userId){
        userService.unblockUser(userId);
        return ResponseEntity.status(200).body("User id: " + userId + " unblocked.");
    }
}
