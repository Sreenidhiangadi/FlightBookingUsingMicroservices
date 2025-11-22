package com.flightapp.controller;

import com.flightapp.entity.Flight;
import com.flightapp.entity.Ticket;
import com.flightapp.entity.User;
import com.flightapp.service.AuthService;
import com.flightapp.service.FlightService;
import com.flightapp.service.TicketService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    @Mock
    private AuthService authService;

    @Mock
    private FlightService flightService;

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdminLoginSuccess() {
        User user = new User();
        user.setEmail("admin@example.com");
        user.setPassword("pass");

        when(authService.login(user.getEmail(), user.getPassword())).thenReturn("session123");

        String result = adminController.adminLogin(user);
        assertEquals("session123", result);
    }

    @Test
    void testAdminLoginFailure() {
        User user = new User();
        user.setEmail("admin@example.com");
        user.setPassword("wrong");

        when(authService.login(user.getEmail(), user.getPassword())).thenReturn(null);

        String result = adminController.adminLogin(user);
        assertEquals("Invalid credentials", result);
    }


    @Test
    void testGetAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket());
        when(ticketService.getAllTickets()).thenReturn(tickets);

        List<Ticket> result = adminController.getAllTickets();
        assertEquals(1, result.size());
    }

    @Test
    void testAddFlight() {
        Flight flight = new Flight();
        String response = adminController.addFlight(flight);
        verify(flightService).addFlight(flight);
        assertEquals("Flight added successfully", response);
    }

    @Test
    void testUpdateFlight() {
        Flight flight = new Flight();
        flight.setId(1L);

        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "New Flight");

        when(flightService.updateFlight(1L, updates)).thenReturn(flight);

        Flight result = adminController.update(1L, updates);
        assertEquals(flight, result);
    }

    @Test
    void testDeleteFlight() {
        when(flightService.deleteFlight(1L)).thenReturn("Deleted successfully");
        String result = adminController.delete(1L);
        assertEquals("Deleted successfully", result);
    }
}
