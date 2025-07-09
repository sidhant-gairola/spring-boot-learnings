package com.gairolas.journalApp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gairolas.journalApp.entity.User;
import com.gairolas.journalApp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean saveNewUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.error("Error for : {}", user.getUserName(), e);
/*
            log.error("new log inserted");
            log.warn("new log inserted");
            log.debug("new log inserted");
            log.trace("new log inserted");
*/
            return false;
        }
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllEntries() {
        return userRepository.findAll();
    }

    public Optional<User> getEntryById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteEntryById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(user);
    }
}
