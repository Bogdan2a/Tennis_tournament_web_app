package service;

import model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    void registerUser(User user);
    String loginUser(User user);
    public void createUser(User user);
    void updateUser(Long userId, User user);
    public void deleteUser(Long userId);
    List<User> getAllUsers();

    boolean validateUserCredentials(String username, String password);

    String getUserRole(String username);

    User getUserById(Long userId);



    Long getUserIdByUsername(String username);
    // Other methods for user account management
}
