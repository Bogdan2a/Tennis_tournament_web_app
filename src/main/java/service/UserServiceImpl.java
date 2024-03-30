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
        if (username == null || password == null) {
            return false;
        }
        return true;
    }
}
