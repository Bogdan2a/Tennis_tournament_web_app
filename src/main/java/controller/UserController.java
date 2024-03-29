package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;

@Controller
@RequestMapping("/api/users")
public class UserController {

    /*@GetMapping("/")
    public String home() {
        return "index"; // Return the view name
    }*/

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Validate user data
        // Perform registration
        userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        // Validate user credentials
        // Perform login
        // Generate JWT token
        String token = userService.loginUser(user);
        return ResponseEntity.ok().body(token);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody User user) {
        // Validate user data
        // Perform update
        userService.updateUser(userId, user);
        return ResponseEntity.ok().build();
    }

    // Other methods for user account management
}
