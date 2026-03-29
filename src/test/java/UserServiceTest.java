package com.practice.masterslave.service;

import com.practice.masterslave.entity.User;
import com.practice.masterslave.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest {

    @Test
    void testSaveUser() {

        // Mock repository
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        // Create service
        UserService userService = new UserService(userRepository);

        // Create dummy user
        User user = new User();
        user.setName("Test User");

        // Mock behavior
        Mockito.when(userRepository.save(user)).thenReturn(user);

        // Call method
        User savedUser = userService.saveUser(user);

        // Assert
        assertNotNull(savedUser);
    }
}