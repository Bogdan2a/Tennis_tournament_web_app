package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/users")
public class UserController {

    /*@GetMapping("/")
    public String home() {
        return "index"; // Return the view name
    }*/

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        List<User> users = userService.getAllUsers();
        List<String> usernames = users.stream().map(User::getUsername).collect(Collectors.toList());
        model.addAttribute("usernames", usernames);
        return "index";
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Validate user data
        // Perform registration
        userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/login.html")
    public String showLoginPage() {
        return "login"; // Return the name of the HTML file (without the extension)
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        // Validate user credentials
        boolean isValidUser = userService.validateUserCredentials(user.getUsername(), user.getPassword());
        if (isValidUser) {
            // Perform login logic (e.g., generate token, store in session)
            String token = userService.loginUser(user);
            // Redirect to the dashboard page
            return ResponseEntity.status(HttpStatus.OK).body("/dashboard.html");
        } else {
            // User authentication failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }


    @GetMapping("/dashboard.html")
    public String showDashboardPage() {
        return "dashboard"; // Return the name of the HTML file (without the extension)
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
