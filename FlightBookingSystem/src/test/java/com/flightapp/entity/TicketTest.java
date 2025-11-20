package com.flightapp.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    @Test
    void testTicketEntity() {
        Ticket ticket = new Ticket();
        ticket.setId(5L);
        ticket.setPnr("PNR12345");
        ticket.setBookingTime(LocalDateTime.now());
        ticket.setCanceled(false);
        ticket.setNumberOfSeats(2);
        ticket.setMealType("Veg");
        ticket.setTotalPrice(5600.0);

        assertEquals("PNR12345", ticket.getPnr());
        assertEquals(2, ticket.getNumberOfSeats());
        assertFalse(ticket.isCanceled());
    }

    @Test
    void testTicketFlightMapping() {
        Ticket ticket = new Ticket();
        Flight dep = new Flight();
        ticket.setDepartureFlight(dep);

        assertNotNull(ticket.getDepartureFlight());
    }

    @Test
    void testPassengerListMapping() {
        Ticket ticket = new Ticket();
        Passenger p = new Passenger();
        ticket.getPassengers().add(p);

        assertEquals(1, ticket.getPassengers().size());
    }
}
