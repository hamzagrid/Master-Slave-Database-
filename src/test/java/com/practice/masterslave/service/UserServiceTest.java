package com.practice.masterslave.service;

import com.practice.masterslave.entity.User;
import com.practice.masterslave.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceTest {

    @Test
    void testSaveUser() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        User user = new User();
        user.setName("Test User");

        Mockito.when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertNotNull(savedUser);
    }

    @Test
    void testSaveUser_Null() {
        UserRepository repo = Mockito.mock(UserRepository.class);
        UserService service = new UserService(repo);

        assertThrows(IllegalArgumentException.class, () -> service.saveUser(null));
    }

    @Test
    void testGetAllUsers() {
        UserRepository repo = Mockito.mock(UserRepository.class);
        UserService service = new UserService(repo);

        Mockito.when(repo.findAll()).thenReturn(List.of(new User()));

        List<User> users = service.getAllUsers();

        assertNotNull(users);
    }

    @Test
    void testGetUserById() {
        UserRepository repo = Mockito.mock(UserRepository.class);
        UserService service = new UserService(repo);

        User user = new User();
        user.setId(1L);
        user.setName("Hamza");

        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = service.getUserById(1L);

        assertTrue(result.isPresent());
        assertNotNull(result.get());
    }
}