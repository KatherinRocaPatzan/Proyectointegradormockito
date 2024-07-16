package org.adaschool.mokito.service;

import org.adaschool.mokito.service.User;
import org.adaschool.mokito.exception.UserNotFoundException;
import org.adaschool.mokito.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private List<User> mockUsers;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        // Mock data
        List<User> users = new ArrayList<>();
        users.add(new User("1", "User1"));
        users.add(new User("2", "User2"));

        // Mock behavior
        when(mockUsers.size()).thenReturn(users.size());
        when(mockUsers.get(0)).thenReturn(users.get(0));
        when(mockUsers.get(1)).thenReturn(users.get(1));

        // Test service method
        List<User> result = userService.getAllUsers();

        // Verify assertions
        assertEquals(2, result.size());
        assertEquals("User1", result.get(0).getName());
        assertEquals("User2", result.get(1).getName());

        // Verify mock interactions
        verify(mockUsers, times(1)).size();
        verify(mockUsers, times(2)).get(anyInt());
    }

    @Test
    void testGetUserById() {
        // Mock data
        User user = new User("1", "User1");
        when(mockUsers.stream()).thenReturn(mockUsers.stream());
        when(mockUsers.get(0)).thenReturn(user);
        when(mockUsers.get(1)).thenReturn(user);
        when(mockUsers.get(2)).thenReturn(user);
        when(mockUsers.get(3)).thenReturn(user);
        when(mockUsers.get(4)).thenReturn(user);

        // Test service method
        Optional<User> result = userService.getUserById("1");

        // Verify assertions
        assertTrue(result.isPresent());
        assertEquals("User1", result.get().getName());

        // Verify mock interactions
        verify(mockUsers, times(1)).stream();
        verify(mockUsers, times(5)).get(anyInt());
    }

    @Test
    void testCreateUser() {
        // Mock data
        User newUser = new User("3", "NewUser");

        // Mock behavior
        when(mockUsers.add(any(User.class))).thenReturn(true);

        // Test service method
        User createdUser = userService.createUser(newUser);

        // Verify assertions
        assertNotNull(createdUser);
        assertEquals("3", createdUser.getId());
        assertEquals("NewUser", createdUser.getName());

        // Verify mock interactions
        verify(mockUsers, times(1)).add(newUser);
    }

    @Test
    void testUpdateUser() {
        // Mock data
        User existingUser = new User("1", "User1");
        User updatedUser = new User("1", "UpdatedUser");

        // Mock behavior
        when(mockUsers.removeIf(any())).thenReturn(true);
        when(mockUsers.add(any(User.class))).thenReturn(true);

        // Test service method
        User result = userService.updateUser("1", updatedUser);

        // Verify assertions
        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("UpdatedUser", result.getName());

        // Verify mock interactions
        verify(mockUsers, times(1)).removeIf(any());
        verify(mockUsers, times(1)).add(updatedUser);
    }

    @Test
    void testUpdateUser_UserNotFound() {
        // Mock data
        User updatedUser = new User("1", "UpdatedUser");

        // Mock behavior
        when(mockUsers.removeIf(any())).thenReturn(false); // Simulate user not found

        // Test service method
        assertThrows(UserNotFoundException.class, () -> userService.updateUser("1", updatedUser));

        // Verify mock interactions
        verify(mockUsers, times(1)).removeIf(any());
    }

    @Test
    void testDeleteUser() {
        // Mock data
        User userToDelete = new User("1", "User1");

        // Mock behavior
        when(mockUsers.removeIf(any())).thenReturn(true);

        // Test service method
        userService.deleteUser("1");

        // Verify mock interactions
        verify(mockUsers, times(1)).removeIf(any());
    }
}