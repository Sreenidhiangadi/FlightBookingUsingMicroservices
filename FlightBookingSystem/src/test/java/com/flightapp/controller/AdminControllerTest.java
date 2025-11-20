package com.flightapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightapp.entity.Flight;
import com.flightapp.entity.User;
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

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Services must be mocked
    @MockBean
    private AuthService authService;

    @MockBean
    private FlightService flightService;

    @MockBean
    private TicketService ticketService;

    // ------------------------------
    // ADMIN LOGIN
    // ------------------------------
    @Test
    void testAdminLoginSuccess() throws Exception {
        User user = new User();
        user.setEmail("admin@test.com");
        user.setPassword("pwd");

        Mockito.when(authService.login("admin@test.com", "pwd"))
               .thenReturn("SESSION123");

        mockMvc.perform(post("/api/v1.0/flight/airline/admin/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("SESSION123"));
    }

    @Test
    void testAdminLoginInvalid() throws Exception {
        User user = new User();
        user.setEmail("admin@test.com");
        user.setPassword("invalid");

        Mockito.when(authService.login("admin@test.com", "invalid"))
               .thenReturn(null);

        mockMvc.perform(post("/api/v1.0/flight/airline/admin/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid credentials"));
    }

    // ------------------------------
    // GET ADMIN DETAILS
    // ------------------------------
    @Test
    void testGetAdmin() throws Exception {
        User user = new User();
        user.setEmail("admin@test.com");

        Mockito.when(authService.getAdmin("admin@test.com"))
               .thenReturn(user);

        mockMvc.perform(post("/api/v1.0/flight/airline/getadmin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("admin@test.com"));
    }

    // ------------------------------
    // GET ALL TICKETS
    // ------------------------------
    @Test
    void testGetAllTickets() throws Exception {
        Mockito.when(ticketService.getAllTickets())
               .thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/v1.0/flight/airline/inventory/allTickets"))
                .andExpect(status().isOk());
    }

    // ------------------------------
    // ADD FLIGHT
    // ------------------------------
    @Test
    void testAddFlight() throws Exception {
        Flight flight = new Flight();

        mockMvc.perform(post("/api/v1.0/flight/airline/inventory/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(flight)))
                .andExpect(status().isOk())
                .andExpect(content().string("Flight added successfully"));

        Mockito.verify(flightService).addFlight(Mockito.any());
    }

    // ------------------------------
    // UPDATE FLIGHT
    // ------------------------------
    @Test
    void testUpdateFlight() throws Exception {
        Map<String, Object> updates = Map.of("price", 5000);

        Flight updated = new Flight();
        updated.setId(1L);

        Mockito.when(flightService.updateFlight(Mockito.eq(1L), Mockito.anyMap()))
               .thenReturn(updated);

        mockMvc.perform(put("/api/v1.0/flight/airline/inventory/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updates)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    // ------------------------------
    // DELETE FLIGHT
    // ------------------------------
    @Test
    void testDeleteFlight() throws Exception {
        Mockito.when(flightService.deleteFlight(1L))
               .thenReturn("Deleted");

        mockMvc.perform(delete("/api/v1.0/flight/airline/inventory/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));
    }
}
