package com.flightapp.service;

<<<<<<< Updated upstream
import com.flightapp.Entity.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {
=======
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.flightapp.entity.User;
import com.flightapp.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
>>>>>>> Stashed changes

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    @Test
<<<<<<< Updated upstream
    void testRegisterUserNameNotNull() {
        User user = new User();
        user.setName("Sreenidhi");

        assertNotNull(user.getName(), "Name should not be null");
    }

    @Test
    void testLoginFailsForWrongPassword() {
=======
    void testRegister() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        User result = authService.register(user);

        assertEquals(user, result);
        verify(userRepository).save(user);
    }

    @Test
    void testLoginSuccess() {
>>>>>>> Stashed changes
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("12345");

<<<<<<< Updated upstream
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
=======
        when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));

        String session = authService.login("test@gmail.com", "12345");

        assertNotNull(session);
        assertEquals(36, session.length());
    }

    @Test
    void testLoginInvalidPassword() {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("correct");

        when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));

        assertNull(authService.login("test@gmail.com", "wrong"));
    }

    @Test
    void testLoginUserNotFound() {
        when(userRepository.findByEmail("abc@gmail.com"))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> authService.login("abc@gmail.com", "123"));
    }

    @Test
    void testGetAdmin() {
        User user = new User();
        when(userRepository.findByEmail("admin@gmail.com"))
                .thenReturn(Optional.of(user));

        assertEquals(user, authService.getAdmin("admin@gmail.com"));
    }

    @Test
    void testGetLoggedInUser() {
        User user = new User();
        when(userRepository.findByEmail("u@gmail.com")).thenReturn(Optional.of(user));

        String sid = UUID.randomUUID().toString();
        authService.userSessions.put(sid, "u@gmail.com");

        assertEquals(user, authService.getLoggedInUser(sid));
>>>>>>> Stashed changes
    }
}
