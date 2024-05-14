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
        userRepository.save(user);
    }

    @Override
    public String loginUser(User user) {
        return "JWT_TOKEN";
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean validateUserCredentials(String username, String password) {

        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }


    @Override
    public String getUserRole(String username) {

        String userRole = userRepository.getUserRoleByUsername(username);
        if (userRole != null && !userRole.isEmpty()) {
            return userRole;
        } else {
            return "DEFAULT_ROLE";
        }
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(Long userId, User user) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

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

    public String getUserEmailById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return user.getEmail();
    }
}
