package com.flightapp.controller;

import com.flightapp.entity.Flight;
import com.flightapp.entity.Ticket;
import com.flightapp.entity.User;
import com.flightapp.service.AuthService;
import com.flightapp.service.FlightService;
import com.flightapp.service.TicketService;

import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/api/v1.0/flight/airline")
public class AdminController {

    private final AuthService authService;
    private final FlightService flightService;
    private final TicketService ticketService;
    public AdminController(AuthService authService, FlightService flightService,TicketService ticketService) {
        this.authService = authService;
        this.flightService = flightService;
        this.ticketService=ticketService;
    }

    @PostMapping("/admin/login")
    public String adminLogin(@RequestBody User user) {
        String session = authService.login(user.getEmail(), user.getPassword());
        if (session == null) return "Invalid credentials";
        return session;
    }
    @PostMapping("/getadmin")
    public User getAdmin(@RequestBody User user) {
    	return authService.getAdmin(user.getEmail());
    	 
    }
    @GetMapping("/inventory/allTickets")
    public List<Ticket> getAllTickets(){
    	return ticketService.getAllTickets();
    }
    @PostMapping(value="/inventory/add",consumes = "application/json",
    	    produces = "application/json")
    public String addFlight(@RequestBody Flight flight) {
        flightService.addFlight(flight);
        return "Flight added successfully";
    }
    @PutMapping("/inventory/update/{id}")
    public Flight update(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return flightService.updateFlight(id, updates);
    }


    @DeleteMapping("/inventory/delete/{id}")
    public String delete(@PathVariable Long id) {
        return flightService.deleteFlight(id);
    }
}
