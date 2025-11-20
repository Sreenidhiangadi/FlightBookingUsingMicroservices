package com.flightapp.service;

<<<<<<< Updated upstream
=======
import com.flightapp.entity.*;
import com.flightapp.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

>>>>>>> Stashed changes
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

<<<<<<< Updated upstream
import com.flightapp.entity.Flight;
import com.flightapp.entity.FlightType;
import com.flightapp.entity.Passenger;
import com.flightapp.entity.Ticket;
import com.flightapp.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class TicketServiceTest {

    @Test
    void testBookTicketOneWay() {
        User user = new User();
        user.setId(1L);
        user.setName("Sreenidhi");
        user.setEmail("sreenidhi@gmail.com");

        Flight departureFlight = new Flight();
        departureFlight.setId(1L);
        departureFlight.setPrice(5000);
        departureFlight.setAvailableSeats(10);
        departureFlight.setDepartureTime(LocalDateTime.now().plusDays(2));

     
        Passenger passenger = new Passenger();
        passenger.setName("Passenger1");
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger);

        int seatCount = passengers.size();
        assertTrue(departureFlight.getAvailableSeats() >= seatCount, "Enough seats available");

        departureFlight.setAvailableSeats(departureFlight.getAvailableSeats() - seatCount);

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setDepartureFlight(departureFlight);
        ticket.setTripType(FlightType.ONE_WAY);
        ticket.setPassengers(passengers);
        ticket.setPnr(UUID.randomUUID().toString().substring(0, 8));
        ticket.setBookingTime(LocalDateTime.now());
        ticket.setTotalPrice((double) (departureFlight.getPrice() * seatCount));


        for (Passenger p : passengers) {
            p.setTicket(ticket);
        }

        
        assertEquals(user, ticket.getUser());
        assertEquals(FlightType.ONE_WAY, ticket.getTripType());
        assertEquals(9, departureFlight.getAvailableSeats());
        assertNotNull(ticket.getPnr());
        assertEquals(5000, ticket.getTotalPrice());
    }

    @Test
    void testCancelTicket() {
        User user = new User();
        user.setEmail("sreenidhi@gmail.com");

        Flight departureFlight = new Flight();
        departureFlight.setAvailableSeats(5);
        departureFlight.setDepartureTime(LocalDateTime.now().plusDays(2));

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setDepartureFlight(departureFlight);
        ticket.setCanceled(false);

        List<Passenger> passengers = new ArrayList<>();
        Passenger passenger = new Passenger();
        passengers.add(passenger);
        ticket.setPassengers(passengers);

        if (!ticket.getUser().getEmail().equals("sreenidhi@gmail.com")) {
            fail("Cannot cancel another user's ticket");
        }

        assertFalse(ticket.isCanceled(), "Ticket is not already canceled");
        assertTrue(LocalDateTime.now().plusHours(24).isBefore(departureFlight.getDepartureTime()), 
                   "Cancellation allowed");

       
        int seats = ticket.getPassengers().size();
        departureFlight.setAvailableSeats(departureFlight.getAvailableSeats() + seats);
        ticket.setCanceled(true);

    
        assertTrue(ticket.isCanceled());
        assertEquals(6, departureFlight.getAvailableSeats());
=======
@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock FlightRepository flightRepository;
    @Mock UserRepository userRepository;
    @Mock TicketRepository ticketRepository;

    @InjectMocks TicketService ticketService;

    @Test
    void testBookTicketSuccess() {
        User user = new User();
        user.setId(1L);

        Flight dep = new Flight();
        dep.setId(10L);
        dep.setAvailableSeats(10);
        dep.setPrice(100);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(flightRepository.findById(10L)).thenReturn(Optional.of(dep));

        List<Passenger> passengers = List.of(new Passenger(), new Passenger());

        Ticket savedTicket = new Ticket();
        savedTicket.setPnr("12345678");

        when(ticketRepository.save(any())).thenReturn(savedTicket);

        String pnr = ticketService.bookTicket(1L, 10L, null, passengers, FlightType.ONE_WAY);

        assertNotNull(pnr);
        verify(flightRepository).save(dep);
    }

    @Test
    void testBookTicketInsufficientSeats() {
        User user = new User();
        Flight dep = new Flight();
        dep.setAvailableSeats(1);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(flightRepository.findById(10L)).thenReturn(Optional.of(dep));

        List<Passenger> passengers = List.of(new Passenger(), new Passenger());

        assertThrows(RuntimeException.class,
                () -> ticketService.bookTicket(1L, 10L, null, passengers, FlightType.ONE_WAY));
    }

    @Test
    void testGetHistory() {
        User user = new User();
        when(userRepository.findByEmail("x@gmail.com")).thenReturn(Optional.of(user));

        when(ticketRepository.findByUser(user)).thenReturn(List.of(new Ticket()));

        assertEquals(1, ticketService.getHistory("x@gmail.com").size());
    }

    @Test
    void testGetTicketByPnr() {
        Ticket t = new Ticket();
        when(ticketRepository.findByPnr("PNR123")).thenReturn(Optional.of(t));

        assertEquals(t, ticketService.getTicketByPnr("PNR123"));
    }

    @Test
    void testCancelTicketSuccess() {
        Ticket ticket = new Ticket();
        User user = new User();
        user.setEmail("abc@gmail.com");
        ticket.setUser(user);
        ticket.setCanceled(false);

        Flight dep = new Flight();
        dep.setAvailableSeats(5);
        dep.setDepartureTime(LocalDateTime.now().plusDays(2));

        ticket.setDepartureFlight(dep);
        ticket.setPassengers(List.of(new Passenger()));

        when(ticketRepository.findByPnr("P1")).thenReturn(Optional.of(ticket));

        String result = ticketService.cancelTicket("P1", "abc@gmail.com");

        assertEquals("Cancelled Successfully", result);
        verify(flightRepository).save(dep);
>>>>>>> Stashed changes
    }
}
