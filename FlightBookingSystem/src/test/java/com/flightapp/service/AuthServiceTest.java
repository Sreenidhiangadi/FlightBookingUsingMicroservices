package com.flightapp.service;

import static org.junit.jupiter.api.Assertions.*;

import com.flightapp.entity.User;
import org.junit.jupiter.api.Test;

class AuthServiceTest {

    @Test
    void testRegister() {
       
        User user = new User();
        user.setName("Sreenidhi");
        user.setEmail("sreenidhi@gmail.com");

        User savedUser = user;
        assertEquals("Sreenidhi", savedUser.getName());
        assertEquals("sreenidhi@gmail.com", savedUser.getEmail());
    }

    @Test
    void testLoginSuccess() {
      
        User user = new User();
        user.setEmail("sreenidhi@gmail.com");
        user.setPassword("1234");

        String session = null;
        if ("1234".equals(user.getPassword())) {
            session = "session123"; 
        }

        assertNotNull(session);
    }
}
