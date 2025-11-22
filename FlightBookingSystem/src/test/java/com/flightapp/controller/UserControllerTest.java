package com.flightapp.controller;

import com.flightapp.entity.*;
import com.flightapp.service.AuthService;
import com.flightapp.service.FlightService;
import com.flightapp.service.TicketService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private AuthService authService;

    @Mock
    private FlightService flightService;

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        User user = new User();
        when(authService.register(user)).thenReturn(user);
        User result = userController.register(user);
        assertEquals(user, result);
    }

    @Test
    void testUserLoginSuccess() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setPassword("pass");

        when(authService.login(user.getEmail(), user.getPassword())).thenReturn("session123");

        String result = userController.userLogin(user);
        assertEquals("session123", result);
    }

    @Test
    void testUserLoginFailure() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setPassword("wrong");

        when(authService.login(user.getEmail(), user.getPassword())).thenReturn(null);

        String result = userController.userLogin(user);
        assertEquals("Invalid credentials", result);
    }

    @Test
    void testSearchFlights() {
        Map<String, String> body = new HashMap<>();
        body.put("fromPlace", "A");
        body.put("toPlace", "B");
        body.put("start", "2025-11-21T10:00:00");
        body.put("end", "2025-11-22T10:00:00");

        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight());
        when(flightService.searchFlights("A", "B", LocalDateTime.parse("2025-11-21T10:00:00"), LocalDateTime.parse("2025-11-22T10:00:00"))).thenReturn(flights);

        List<Flight> result = userController.searchFlights(body);
        assertEquals(1, result.size());
    }

    @Test
    void testSearchByAirline() {
        Map<String, String> body = new HashMap<>();
        body.put("fromPlace", "A");
        body.put("toPlace", "B");
        body.put("airline", "AirX");

        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight());
        when(flightService.searchFlightsByAirline("A", "B", "AirX")).thenReturn(flights);

        List<Flight> result = userController.searchByAirline(body);
        assertEquals(1, result.size());
    }




    @Test
    void testGetAllFlights() {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight());
        when(flightService.getAllFlights()).thenReturn(flights);

        List<Flight> result = userController.getAllFlights();
        assertEquals(1, result.size());
    }

    @Test
    void testGetTicket() {
        Ticket ticket = new Ticket();
        when(ticketService.getTicketByPnr("PNR123")).thenReturn(ticket);
        Ticket result = userController.getTicket("PNR123");
        assertEquals(ticket, result);
    }

    @Test
    void testHistory() {
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket());
        when(ticketService.getHistory("user@example.com")).thenReturn(tickets);

        List<Ticket> result = userController.history("user@example.com");
        assertEquals(1, result.size());
    }

    @Test
    void testCancelTicket() {
        Map<String, String> body = new HashMap<>();
        body.put("email", "user@example.com");

        when(ticketService.cancelTicket("PNR123", "user@example.com")).thenReturn("Cancelled successfully");

        String result = userController.cancel("PNR123", body);
        assertEquals("Cancelled successfully", result);
    }
}
