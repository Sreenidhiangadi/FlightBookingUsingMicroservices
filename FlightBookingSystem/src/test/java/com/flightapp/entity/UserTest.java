package com.flightapp.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserEntity() {
        User user = new User();

        user.setId(100L);
        user.setName("Ram");
        user.setGender("Male");
        user.setAge(22);
        user.setEmail("ram@gmail.com");
        user.setPassword("secret");
        user.setRole(Role.ADMIN);

        assertEquals("Ram", user.getName());
        assertEquals("ADMIN", user.getRole().name());
        assertEquals("ram@gmail.com", user.getEmail());
    }

    @Test
    void testUserTicketMapping() {
        User user = new User();
        user.setTickets(new ArrayList<>());
        Ticket ticket = new Ticket();
        user.getTickets().add(ticket);

        assertEquals(1, user.getTickets().size());
    }
}
