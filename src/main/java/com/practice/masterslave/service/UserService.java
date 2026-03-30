package com.practice.masterslave.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import com.practice.masterslave.config.DBContext;
import com.practice.masterslave.config.DBType;
import com.practice.masterslave.entity.User;
import com.practice.masterslave.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
// Service layer responsible for handling user operations using master-slave database routing
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        return userRepository.save(user);
    }

    public class UserServiceTest {

        @Test
        void testSaveUser() {
            // existing test
        }

        @Test
        void testSaveUser_Null() {
            UserRepository repo = Mockito.mock(UserRepository.class);
            UserService service = new UserService(repo);

            assertThrows(IllegalArgumentException.class, () -> {
                service.saveUser(null);
            });
        }
    }

//    public User saveUser(User user) {
//        return userRepository.save(user);
    }



//@Service
//
//public class UserService {
//
//    private final UserRepository userRepository;
//
//    public User saveUser(User user) {
//        try {
//            DBContext.set(DBType.MASTER);
//            return userRepository.save(user);
//        } finally {
//            DBContext.clear();
//        }
//    }

    public List<User> getAllUsers() {
        try {
            DBContext.set(DBType.SLAVE);
            return userRepository.findAll();
        } finally {
            DBContext.clear();
        }
    }
