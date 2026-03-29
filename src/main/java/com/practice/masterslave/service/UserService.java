package com.practice.masterslave.service;

import com.practice.masterslave.config.DBContext;
import com.practice.masterslave.config.DBType;
import com.practice.masterslave.entity.User;
import com.practice.masterslave.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}