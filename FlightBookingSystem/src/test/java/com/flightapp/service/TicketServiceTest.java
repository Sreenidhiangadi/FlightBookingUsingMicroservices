package com.flightapp.service;

import com.flightapp.entity.*;
import com.flightapp.repository.FlightRepository;
import com.flightapp.repository.TicketRepository;
import com.flightapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    private User user;
    private Flight departureFlight;
    private Flight returnFlight;
    private List<Passenger> passengers;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setEmail("user@example.com");

        departureFlight = new Flight();
        departureFlight.setId(1L);
        departureFlight.setAvailableSeats(10);
        departureFlight.setPrice(500);

        returnFlight = new Flight();
        returnFlight.setId(2L);
        returnFlight.setAvailableSeats(10);
        returnFlight.setPrice(400);

        Passenger passenger = new Passenger();
        passenger.setName("John");
        passenger.setAge(30);
        passenger.setGender("M");
        passenger.setSeatNumber("A1");

        passengers = new ArrayList<>();
        passengers.add(passenger);
    }

    @Test
    void testBookTicketOneway() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(flightRepository.findById(1L)).thenReturn(Optional.of(departureFlight));
        when(ticketRepository.save(any(Ticket.class))).thenAnswer(i -> i.getArgument(0));

        String pnr = ticketService.bookTicket(1L, 1L, null, passengers, FlightType.ONE_WAY);

        assertNotNull(pnr);
        assertEquals(9, departureFlight.getAvailableSeats());
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    void testBookTicketRoundTrip() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(flightRepository.findById(1L)).thenReturn(Optional.of(departureFlight));
        when(flightRepository.findById(2L)).thenReturn(Optional.of(returnFlight));
        when(ticketRepository.save(any(Ticket.class))).thenAnswer(i -> i.getArgument(0));

        String pnr = ticketService.bookTicket(1L, 1L, 2L, passengers, FlightType.ROUND_TRIP);

        assertNotNull(pnr);
        assertEquals(9, departureFlight.getAvailableSeats());
        assertEquals(9, returnFlight.getAvailableSeats());
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    void testGetHistory() {
        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket());
        when(ticketRepository.findByUser(user)).thenReturn(tickets);

        List<Ticket> result = ticketService.getHistory("user@example.com");
        assertEquals(1, result.size());
    }

    @Test
    void testGetTicketByPnr() {
        Ticket ticket = new Ticket();
        when(ticketRepository.findByPnr("PNR123")).thenReturn(Optional.of(ticket));
        Ticket result = ticketService.getTicketByPnr("PNR123");
        assertEquals(ticket, result);
    }

    @Test
    void testGetAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket());
        when(ticketRepository.findAll()).thenReturn(tickets);
        List<Ticket> result = ticketService.getAllTickets();
        assertEquals(1, result.size());
    }


}
