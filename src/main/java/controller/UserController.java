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
            // Retrieve user role
            String userRole = userService.getUserRole(user.getUsername());

            // Redirect to the appropriate dashboard based on user role
            String dashboardUrl;
            if ("TENNIS_PLAYER".equals(userRole)) {
                dashboardUrl = "/api/users/player_dashboard.html";
            } else if ("REFEREE".equals(userRole)) {
                dashboardUrl = "/api/users/referee_dashboard.html";
            } else if ("ADMIN".equals(userRole)) {
                dashboardUrl = "/api/users/admin_dashboard.html";
            } else {
                // Default dashboard for unknown roles
                dashboardUrl = "/api/users/player_dashboard.html";
            }
            return ResponseEntity.status(HttpStatus.OK).body(dashboardUrl);
        } else {
            // User authentication failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @GetMapping("/player_dashboard.html")
    public String showPlayerDashboard() {
        return "player_dashboard"; // Return the name of the HTML file (without the extension)
    }
    @GetMapping("/referee_dashboard.html")
    public String showRefereeDashboard() {
        return "referee_dashboard"; // Return the name of the HTML file (without the extension)
    }
    @GetMapping("/admin_dashboard.html")
    public String showAdminDashboard() {
        return "admin_dashboard"; // Return the name of the HTML file (without the extension)
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
