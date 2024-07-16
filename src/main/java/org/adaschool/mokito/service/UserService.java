package org.adaschool.mokito.service;

import org.adaschool.mokito.service.User;
import org.adaschool.mokito.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public List<User> getAllUsers() {
        return users;
    }

    public Optional<User> getUserById(String userId) {
        return users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();
    }

    public User createUser(User user) {
        users.add(user);
        return user;
    }

    public User updateUser(String userId, User updatedUser) {
        boolean userExists = users.removeIf(user -> user.getId().equals(userId));
        if (!userExists) {
            throw new UserNotFoundException(userId);
        }
        users.add(updatedUser);
        return updatedUser;
    }

    public void deleteUser(String userId) {
        boolean userDeleted = users.removeIf(user -> user.getId().equals(userId));
        if (!userDeleted) {
            throw new UserNotFoundException(userId);
        }
    }
}