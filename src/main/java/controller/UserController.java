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

    @GetMapping("/register.html")
    public String showRegisterPage(Model model) {
        return "register";
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user: " + e.getMessage());
        }
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
            // Retrieve user role and ID
            String userRole = userService.getUserRole(user.getUsername());
            Long userId = userService.getUserIdByUsername(user.getUsername());

            // Redirect to the appropriate dashboard based on user role
            String dashboardUrl;
            if ("TENNIS_PLAYER".equals(userRole)) {
                dashboardUrl = "/api/users/player_dashboard.html?userId=" + userId;
            } else if ("REFEREE".equals(userRole)) {
                dashboardUrl = "/api/users/referee_dashboard.html?userId=" + userId;
            } else if ("ADMIN".equals(userRole)) {
                dashboardUrl = "/api/users/admin_dashboard.html?userId=" + userId;
            } else {
                // Default dashboard for unknown roles
                dashboardUrl = "/api/users/player_dashboard.html?userId=" + userId;
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
    @GetMapping("/admin_user_management.html")
    public String showAdminUserManagement(Model model) {
        // Fetch all users from the database
        List<User> users = userService.getAllUsers();

        // Add the list of users to the model
        model.addAttribute("users", users);

        // Return the name of the HTML file
        return "admin_user_management";
    }
    @GetMapping("/create_user.html")
    public String showCreateUser(Model model) {
        List<User> users = userService.getAllUsers();

        // Add the list of users to the model
        model.addAttribute("users", users);
        return "create_user"; // Return the name of the HTML file (without the extension)
    }
    @PostMapping("/create_user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
            return ResponseEntity.ok("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user: " + e.getMessage());
        }
    }
    @GetMapping("/update_user.html")
    public String showUpdateUser(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "update_user";
    }
    @PutMapping("/update_user/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody User user) {
        try {
            userService.updateUser(userId, user);
            return ResponseEntity.ok("User updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user: " + e.getMessage());
        }
    }

    @GetMapping("/delete_user.html")
    public String showDeleteUser(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "delete_user";
    }
    @DeleteMapping("/delete_user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
           // List<User> users = userService.getAllUsers();
            //model.addAttribute("users", users);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user: " + e.getMessage());
        }
    }

    @GetMapping("/admin_view_users.html")
    public String showAdminViewUsers(Model model) {
        // Fetch all users from the database
        List<User> users = userService.getAllUsers();

        // Add the list of users to the model
        model.addAttribute("users", users);

        // Return the name of the HTML file
        return "admin_view_users";
    }


    @GetMapping("/update_account.html")
    public String showUpdateAccountForm(@RequestParam Long userId, Model model) {
        // Fetch the user based on the userId
        User user = userService.getUserById(userId);

        // Add the user to the model
        model.addAttribute("user", user);

        // Return the name of the HTML file
        return "update_account";
    }

    @PutMapping("/update_account/{userId}")
    public ResponseEntity<String> updateAccount(@PathVariable Long userId, @RequestBody User user) {
        try {
            // Fetch the existing user by ID
            User existingUser = userService.getUserById(userId);

            // Update user fields if provided in the request body
            if (user.getUsername() != null && !user.getUsername().isEmpty()) {
                existingUser.setUsername(user.getUsername());
            }
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                existingUser.setPassword(user.getPassword());
            }
            if (user.getEmail() != null && !user.getEmail().isEmpty()) {
                existingUser.setEmail(user.getEmail());
            }

            // Save the updated user
            userService.updateUser(userId, existingUser);

            return ResponseEntity.ok("User account updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update user account: " + e.getMessage());
        }
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserById(@RequestParam Long userId) {
        try {
            // Fetch the user by ID
            User user = userService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/get_user_by_id/{userId}")
    public ResponseEntity<User> getUserByIdForMessage(@PathVariable Long userId) {
        try {
            // Fetch the user by ID
            User user = userService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


    // Other methods for user account management
}
