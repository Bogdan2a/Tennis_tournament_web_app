package service;

import model.User;
import repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
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

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(Long userId, User user) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Update fields only if the new value is not null
        if (user.getUsername() != null && !Objects.equals(user.getUsername(), "")) {
            existingUser.setUsername(user.getUsername());
        }
        if (user.getEmail() != null && !Objects.equals(user.getEmail(), "")){
            existingUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null && !Objects.equals(user.getPassword(), "")){
            existingUser.setPassword(user.getPassword());
        }
        if (user.getRole() != null && !Objects.equals(user.getRole(), "")){
            existingUser.setRole(user.getRole());
        }

        userRepository.save(existingUser);
    }

    public void updateUserByUsername(String username, User user) {
        User existingUser = userRepository.findByUsername(username);

        // Update fields only if the new value is not null
        if (user.getUsername() != null && !Objects.equals(user.getUsername(), "")) {
            existingUser.setUsername(user.getUsername());
        }
        if (user.getEmail() != null && !Objects.equals(user.getEmail(), "")){
            existingUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null && !Objects.equals(user.getPassword(), "")){
            existingUser.setPassword(user.getPassword());
        }
        if (user.getRole() != null && !Objects.equals(user.getRole(), "")){
            existingUser.setRole(user.getRole());
        }

        userRepository.save(existingUser);
    }

    public Long getUserIdByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user.getId();
    }


    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

}
