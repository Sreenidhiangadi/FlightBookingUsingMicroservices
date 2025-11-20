package com.flightapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightapp.entity.*;
import com.flightapp.service.AuthService;
import com.flightapp.service.FlightService;
import com.flightapp.service.TicketService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @MockBean
    private FlightService flightService;

    @MockBean
    private TicketService ticketService;

    // ------------------------------
    // USER REGISTRATION
    // ------------------------------
    @Test
    void testUserRegistration() throws Exception {
        User user = new User();

        Mockito.when(authService.register(Mockito.any())).thenReturn(user);

        mockMvc.perform(post("/api/v1.0/flight/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    // ------------------------------
    // USER LOGIN
    // ------------------------------
    @Test
    void testUserLoginSuccess() throws Exception {
        User user = new User();
        user.setEmail("test@mail.com");
        user.setPassword("123");

        Mockito.when(authService.login("test@mail.com", "123"))
               .thenReturn("SESSION123");

        mockMvc.perform(post("/api/v1.0/flight/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("SESSION123"));
    }

    @Test
    void testUserLoginInvalid() throws Exception {
        User user = new User();
        user.setEmail("test@mail.com");
        user.setPassword("wrong");

        Mockito.when(authService.login("test@mail.com", "wrong"))
               .thenReturn(null);

        mockMvc.perform(post("/api/v1.0/flight/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid credentials"));
    }

    // ------------------------------
    // SEARCH FLIGHTS BY DATE
    // ------------------------------
    @Test
    void testSearchFlights() throws Exception {
        Map<String, String> req = Map.of(
                "fromPlace", "DEL",
                "toPlace", "HYD",
                "start", LocalDateTime.now().toString(),
                "end", LocalDateTime.now().plusDays(1).toString()
        );

        Mockito.when(flightService.searchFlights(Mockito.anyString(), Mockito.anyString(),
                Mockito.any(), Mockito.any()))
                .thenReturn(new ArrayList<>());

        mockMvc.perform(post("/api/v1.0/flight/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    // ------------------------------
    // SEARCH BY AIRLINE
    // ------------------------------
    @Test
    void testSearchByAirline() throws Exception {
        Map<String, String> req = Map.of(
                "fromPlace", "DEL",
                "toPlace", "HYD",
                "airline", "Indigo"
        );

        Mockito.when(flightService.searchFlightsByAirline(Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString()))
                .thenReturn(new ArrayList<>());

        mockMvc.perform(post("/api/v1.0/flight/search/airline")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    // ------------------------------
    // BOOK TICKET
    // ------------------------------
    @Test
    void testBookTicket() throws Exception {

        Map<String, Object> request = new HashMap<>();
        request.put("userId", 1);
        request.put("departureFlightId", 10);
        request.put("tripType", "ONE_WAY");
        request.put("returnFlightId", null);

        List<Map<String, Object>> passengers = new ArrayList<>();
        passengers.add(Map.of(
                "name", "John",
                "age", 30,
                "gender", "Male",
                "seatNumber", "12A",
                "mealPreference", "Veg"
        ));

        request.put("passengers", passengers);

        Mockito.when(ticketService.bookTicket(Mockito.anyLong(), Mockito.anyLong(),
                Mockito.any(), Mockito.anyList(), Mockito.any()))
                .thenReturn("PNR123");

        mockMvc.perform(post("/api/v1.0/flight/booking")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().string("PNR123"));
    }

    // ------------------------------
    // GET ALL FLIGHTS
    // ------------------------------
    @Test
    void testGetAllFlights() throws Exception {
        Mockito.when(flightService.getAllFlights()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/v1.0/flight/allflights"))
                .andExpect(status().isOk());
    }

    // ------------------------------
    // GET TICKET BY PNR
    // ------------------------------
    @Test
    void testGetTicket() throws Exception {
        Mockito.when(ticketService.getTicketByPnr("PNR123"))
               .thenReturn(new Ticket());

        mockMvc.perform(get("/api/v1.0/flight/ticket/PNR123"))
                .andExpect(status().isOk());
    }

    // ------------------------------
    // BOOKING HISTORY
    // ------------------------------
    @Test
    void testBookingHistory() throws Exception {
        Mockito.when(ticketService.getHistory("test@mail.com"))
               .thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/v1.0/flight/booking/history/test@mail.com"))
                .andExpect(status().isOk());
    }

    // ------------------------------
    // CANCEL TICKET
    // ------------------------------
    @Test
    void testCancelTicket() throws Exception {
        Map<String, String> req = Map.of("email", "test@mail.com");

        Mockito.when(ticketService.cancelTicket("PNR123", "test@mail.com"))
               .thenReturn("Cancelled");

        mockMvc.perform(delete("/api/v1.0/flight/booking/cancel/PNR123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cancelled"));
    }
}
