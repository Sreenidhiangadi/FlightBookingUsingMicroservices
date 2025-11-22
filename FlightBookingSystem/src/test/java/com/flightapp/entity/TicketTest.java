package com.flightapp.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class TicketTest {

	@Test
    void testSetAndGetUser() {
        Ticket ticket = new Ticket();
        User user = new User();
        user.setName("Poojitha");
        ticket.setUser(user);
        assertEquals("Poojitha", ticket.getUser().getName());
    }

    @Test
    void testSetAndGetDepartureFlight() {
        Ticket ticket = new Ticket();
        Flight flight = new Flight();
        flight.setAirline("Air India");
        ticket.setDepartureFlight(flight);
        assertEquals("Air India", ticket.getDepartureFlight().getAirline());
    }

    @Test
    void testSetAndGetReturnFlight() {
        Ticket ticket = new Ticket();
        Flight flight = new Flight();
        flight.setAirline("IndiGo");
        ticket.setReturnFlight(flight);
        assertEquals("IndiGo", ticket.getReturnFlight().getAirline());
    }

    @Test
    void testSetAndGetTripType() {
        Ticket ticket = new Ticket();
        ticket.setTripType(FlightType.ONE_WAY);
        assertEquals(FlightType.ONE_WAY, ticket.getTripType());
    }



    @Test
    void testSetAndGetTotalPrice() {
        Ticket ticket = new Ticket();
        ticket.setTotalPrice(1000.0);
        assertEquals(1000.0, ticket.getTotalPrice());
    }

    @Test
    void testSetAndGetBookingTime() {
        Ticket ticket = new Ticket();
        LocalDateTime now = LocalDateTime.now();
        ticket.setBookingTime(now);
        assertEquals(now, ticket.getBookingTime());
    }

    @Test
    void testSetAndGetCancel() {
        Ticket ticket = new Ticket();
        ticket.setCanceled(true);
        assertTrue(ticket.isCanceled());
    }

    @Test
    void testSetAndGetPnr() {
        Ticket ticket = new Ticket();
        ticket.setPnr("PNR12345");
        assertEquals("PNR12345", ticket.getPnr());
    }
    
    @Test
    void testPassengerAssociation() {
        Ticket ticket = new Ticket();
        Passenger passenger = new Passenger();
        passenger.setName("John");
        passenger.setAge(25);
        passenger.setGender("Male");
        passenger.setSeatNumber("A1");

        // Associate passenger with ticket
        passenger.setTicket(ticket);
        ticket.getPassengers().add(passenger);

        assertEquals(1, ticket.getPassengers().size());
        assertEquals(ticket, passenger.getTicket());
        assertEquals("John", ticket.getPassengers().get(0).getName());
    }
    
    @Test
    void testMultiplePassengers() {
        Ticket ticket = new Ticket();

        Passenger p1 = new Passenger();
        p1.setName("Alice");
        p1.setAge(30);
        p1.setGender("Female");
        p1.setSeatNumber("B1");
        p1.setTicket(ticket);

        Passenger p2 = new Passenger();
        p2.setName("Bob");
        p2.setAge(28);
        p2.setGender("Male");
        p2.setSeatNumber("B2");
        p2.setTicket(ticket);

        ticket.getPassengers().add(p1);
        ticket.getPassengers().add(p2);

        assertEquals(2, ticket.getPassengers().size());
        assertTrue(ticket.getPassengers().contains(p1));
        assertTrue(ticket.getPassengers().contains(p2));
    }
    
    @Test
    void testPassengerTicketReference() {
        Ticket ticket = new Ticket();

        Passenger passenger = new Passenger();
        passenger.setName("Charlie");
        passenger.setSeatNumber("C1");
        passenger.setTicket(ticket);

        ticket.getPassengers().add(passenger);

        // Check that passenger.ticket references the correct ticket
        assertNotNull(passenger.getTicket());
        assertEquals(ticket, passenger.getTicket());
    }
    
    @Test
    void testCancelFlagBehavior() {
        Ticket ticket = new Ticket();
        
        // Initially cancel should be false
        assertFalse(ticket.isCanceled());
        
        // Set cancel to true
        ticket.setCanceled(true);
        assertTrue(ticket.isCanceled());
        
        // Toggle back to false
        ticket.setCanceled(false);
        assertFalse(ticket.isCanceled());
    }
    
    @Test
    void testBookingTimeAssignment() {
        Ticket ticket = new Ticket();
        
        LocalDateTime now = LocalDateTime.now();
        ticket.setBookingTime(now);
        
        assertEquals(now, ticket.getBookingTime());
    }
    
    @Test
    void testPNRField() {
        Ticket ticket = new Ticket();
        
        String pnr = "ABCD1234";
        ticket.setPnr(pnr);
        
        assertEquals(pnr, ticket.getPnr());
    }
    
    @Test
    void testDepartureAndReturnFlightPresence() {
        Ticket ticket = new Ticket();
        ticket.setTripType(FlightType.ROUND_TRIP);

        // Case 1: returnFlight is null → should fail validation (custom logic)
        ticket.setReturnFlight(null);
        assertThrows(RuntimeException.class, () -> {
            if (ticket.getTripType() == FlightType.ROUND_TRIP && ticket.getReturnFlight() == null) {
                throw new RuntimeException("Return flight required for ROUND_TRIP");
            }
        });

        // Case 2: returnFlight is set → should pass
        Flight returnFlight = new Flight();
        ticket.setReturnFlight(returnFlight);
        assertDoesNotThrow(() -> {
            if (ticket.getTripType() == FlightType.ROUND_TRIP && ticket.getReturnFlight() == null) {
                throw new RuntimeException("Return flight required for ROUND_TRIP");
            }
        });
    }
    

    
    @Test
    void testTotalPriceCalculation() {
        Ticket ticket = new Ticket();
        Flight departureFlight = new Flight();
        departureFlight.setPrice(5000);

        Flight returnFlight = new Flight();
        returnFlight.setPrice(6000);

        ticket.setDepartureFlight(departureFlight);
        ticket.setReturnFlight(returnFlight);
        ticket.setTripType(FlightType.ROUND_TRIP);

        int passengerCount = 3;
        double calculatedPrice = departureFlight.getPrice() * passengerCount + returnFlight.getPrice() * passengerCount;
        ticket.setTotalPrice(calculatedPrice);

        assertEquals(33000, ticket.getTotalPrice()); // 5000*3 + 6000*3 = 33000
    }
}