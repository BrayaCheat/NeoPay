package Server.Bank.Controllers;

import Server.Bank.Models.User;
import Server.Bank.Services.ServiceImpl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "User")
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    // For user
    @GetMapping("/user/me")
    public ResponseEntity<User> getUser() {
        return ResponseEntity.status(200).body(userService.getUser());
    }

    // For admin
    @GetMapping("/admin/allUsers")
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
}
