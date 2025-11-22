package com.flightapp.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory f = Validation.buildDefaultValidatorFactory();
        validator = f.getValidator();
    }

    @Test
    void testUserSettersGetters() {
        User u = new User();
        u.setName("Poojitha");
        u.setEmail("p@gmail.com");
        u.setAge(21);
        u.setGender("Female");
        u.setRole(Role.USER);

        assertEquals("Poojitha", u.getName());
        assertEquals("p@gmail.com", u.getEmail());
        assertEquals(21, u.getAge());
        assertEquals("Female", u.getGender());
        assertEquals(Role.USER, u.getRole());
    }

    @Test
    void testUserValidationFails() {
        User u = new User();
        u.setName("");
        var violations = validator.validate(u);
        assertFalse(violations.isEmpty());
    }
    
    @Test
    void testEmailNotBlank() {
        User u = new User();
        u.setName("John");
        u.setEmail("");  // blank email
        u.setAge(25);
        u.setGender("Male");

        Set<ConstraintViolation<User>> violations = validator.validate(u);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testAgeNotNull() {
        User u = new User();
        u.setName("John");
        u.setEmail("a@b.com");
        u.setGender("Male");

        Set<ConstraintViolation<User>> violations = validator.validate(u);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testGenderNotBlank() {
        User u = new User();
        u.setName("John");
        u.setEmail("a@b.com");
        u.setAge(22);
        u.setGender(""); // blank gender

        Set<ConstraintViolation<User>> violations = validator.validate(u);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testPasswordOptional() {
        User u = new User();
        u.setName("John");
        u.setEmail("a@b.com");
        u.setAge(22);
        u.setGender("Male");
        u.setPassword(null); // password is optional

        Set<ConstraintViolation<User>> violations = validator.validate(u);
        assertTrue(violations.isEmpty());
    }
    
    @Test
    void testRoleEnumAssignment() {
        User user = new User();

        user.setRole(Role.USER);
        assertEquals(Role.USER, user.getRole(), "Role should be USER");

        user.setRole(Role.ADMIN);
        assertEquals(Role.ADMIN, user.getRole(), "Role should be ADMIN");
    }
    
  


   
    @Test
    void testEmailFormatValidation() {
        User user = new User();
        user.setEmail("test@example.com");
        
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        assertTrue(user.getEmail().matches(emailRegex));

        user.setEmail("invalid-email");
        assertFalse(user.getEmail().matches(emailRegex));
    }
    
    @Test
    void testAgePositive() {
        User user = new User();
        user.setAge(25);
        assertTrue(user.getAge() > 0);

        user.setAge(-5);
        assertFalse(user.getAge() > 0);
    }
    

    
    @Test
    void testNullRoleAssignment() {
        User user = new User();
        user.setName("Alice");
        user.setEmail("alice@example.com");
        user.setAge(25);
        user.setGender("Female");
        user.setRole(null); // explicitly set role as null

        assertNull(user.getRole(), "Role should be null if not assigned");
    }

}