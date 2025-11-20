package com.flightapp;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.flightapp.service.AuthService;
import com.flightapp.service.FlightService;
import com.flightapp.service.TicketService;

@TestConfiguration
public class TestConfig {

    @MockBean
    AuthService authService;

    @MockBean
    FlightService flightService;

    @MockBean
    TicketService ticketService;
}
