package service;

import model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    void registerUser(User user);
    String loginUser(User user);
    void updateUser(Long userId, User user);

    List<User> getAllUsers();

    boolean validateUserCredentials(String username, String password);
    // Other methods for user account management
}
