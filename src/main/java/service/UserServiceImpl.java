package service;

import model.User;
import repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(User user) {
        // Implement registration logic
        userRepository.save(user);
    }

    @Override
    public String loginUser(User user) {
        // Implement login logic
        // Generate JWT token
        return "JWT_TOKEN";
    }

    @Override
    public void updateUser(Long userId, User user) {
        // Implement update logic
        // Find user by ID
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        // Update user data
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        // Save updated user
        userRepository.save(existingUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Implement other methods for user account management#
    @Override
    public boolean validateUserCredentials(String username, String password) {
        // Retrieve user from the database based on the username
        User user = userRepository.findByUsername(username);

        // Check if user exists and if the provided password matches the user's password
        return user != null && user.getPassword().equals(password);
    }


    @Override
    public String getUserRole(String username) {
        // Assuming you have a method in your UserRepository to fetch the user's role
        // Replace "username" with the actual parameter to fetch the user's role based on username
        String userRole = userRepository.getUserRoleByUsername(username); // Implement this method in your UserRepository

        // Check if user role is retrieved successfully
        if (userRole != null && !userRole.isEmpty()) {
            return userRole;
        } else {
            // Return a default role if user role is not found or there is an error
            return "DEFAULT_ROLE";
        }
    }

}
