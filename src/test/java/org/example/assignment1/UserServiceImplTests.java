package org.example.assignment1;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.UserRepository;
import service.UserServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTests {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize all mocks
    }

    @Test
    public void testRegisterUser() {
        // Given
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        userService.registerUser(user);

        // Then
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetAllUsers() {
        // Given
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("user2");

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        // When
        List<User> retrievedUsers = userService.getAllUsers();

        // Then
        assertEquals(2, retrievedUsers.size());
        assertTrue(retrievedUsers.contains(user1));
        assertTrue(retrievedUsers.contains(user2));
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testValidateUserCredentials() {
        // Given
        String username = "testuser";
        String password = "password";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        when(userRepository.findByUsername(username)).thenReturn(user);

        // When
        boolean isValid = userService.validateUserCredentials(username, password);

        // Then
        assertTrue(isValid);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    public void testGetUserRole() {
        // Given
        String username = "testuser";
        String userRole = "PLAYER";

        when(userRepository.getUserRoleByUsername(username)).thenReturn(userRole);

        // When
        String retrievedUserRole = userService.getUserRole(username);

        // Then
        assertEquals(userRole, retrievedUserRole);
        verify(userRepository, times(1)).getUserRoleByUsername(username);
    }

    @Test
    public void testGetUserById() {
        // Given
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // When
        User retrievedUser = userService.getUserById(userId);

        // Then
        assertNotNull(retrievedUser);
        assertEquals(userId, retrievedUser.getId());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testGetUserEmailById() {
        // Given
        Long userId = 1L;
        String userEmail = "test@example.com";

        User user = new User();
        user.setId(userId);
        user.setEmail(userEmail);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // When
        String retrievedUserEmail = userService.getUserEmailById(userId);

        // Then
        assertEquals(userEmail, retrievedUserEmail);
        verify(userRepository, times(1)).findById(userId);
    }
}
