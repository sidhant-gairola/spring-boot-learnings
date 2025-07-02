package com.gairolas.journalApp.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gairolas.journalApp.repository.UserRepository;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    // @Disabled    // used to skip the testing of the below test
    @Test
    public void test() {
        assertEquals(5, 2 + 3);
    }

    @ParameterizedTest
    @CsvSource({ "sidhant", "rahul", "ram" })
    public void testFindByUsername(String name) {
        assertNotNull(userRepository.findByUserName(name));
    }
}
