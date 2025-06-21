package com.gairolas.journalApp.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gairolas.journalApp.entity.User;
import com.gairolas.journalApp.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.saveEntry(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllEntries();
    }

    @GetMapping("id/{myId}")
    public Optional<User> getUserById(@PathVariable ObjectId myId) {
        return userService.getEntryById(myId);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName) {
        User userInDb = userService.findByUserName(userName);
        if (userInDb != null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public void deleteUser(@PathVariable ObjectId myId) {
        userService.deleteEntryById(myId);
    }
}
