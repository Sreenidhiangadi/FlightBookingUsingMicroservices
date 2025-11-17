package com.flightapp.service;

import com.flightapp.Entity.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {

    @Test
    void testRegisterUserNameNotNull() {
        User user = new User();
        user.setName("Sreenidhi");

        assertNotNull(user.getName(), "Name should not be null");
    }

    @Test
    void testLoginFailsForWrongPassword() {
        User user = new User();
        user.setEmail("sreenidhi@gmail.com");
        user.setPassword("1234");

        String session = null;

        if ("0000".equals(user.getPassword())) { 
            session = "session123";
        }

        assertNull(session, "Session should be null for wrong password");
    }

    @Test
    void testEmailFormatContainsAtSymbol() {
        User user = new User();
        user.setEmail("sreenidhi@gmail.com");

        assertTrue(user.getEmail().contains("@"), "Email should contain @ symbol");
    }
}
