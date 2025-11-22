package com.flightapp.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PassengerTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory f = Validation.buildDefaultValidatorFactory();
        validator = f.getValidator();
    }

    @Test
    void testPassengerSettersGetters() {
        Passenger p = new Passenger();
        p.setName("Ram");
        p.setGender("Male");
        p.setAge(22);
        p.setSeatNumber("12A");

        assertEquals("Ram", p.getName());
        assertEquals("Male", p.getGender());
        assertEquals(22, p.getAge());
        assertEquals("12A", p.getSeatNumber());
    }
    
    @Test
    void testTicketAssociation() {
        Passenger passenger = new Passenger();
        Ticket ticket = new Ticket();
        passenger.setTicket(ticket);

        assertEquals(ticket, passenger.getTicket());
    }
    
    @Test
    void testNameNotBlank() {
        Passenger passenger = new Passenger();
        passenger.setName("John");

        assertNotNull(passenger.getName());
        assertFalse(passenger.getName().isBlank());
    }

    @Test
    void testGenderNotBlank() {
        Passenger passenger = new Passenger();
        passenger.setGender("Female");

        assertNotNull(passenger.getGender());
        assertFalse(passenger.getGender().isBlank());
    }

    @Test
    void testSeatNumberNotBlank() {
        Passenger passenger = new Passenger();
        passenger.setSeatNumber("B2");

        assertNotNull(passenger.getSeatNumber());
        assertFalse(passenger.getSeatNumber().isBlank());
    }
    
    @Test
    void testAgeNotNull() {
        Passenger passenger = new Passenger();
        passenger.setAge(30);

        assertNotNull(passenger.getAge());
    }
    
    @Test
    void testMultiplePassengersBelongToOneTicket() {
        Ticket ticket = new Ticket();

        Passenger p1 = new Passenger();
        p1.setName("Alice");
        p1.setGender("Female");
        p1.setAge(25);
        p1.setSeatNumber("A1");
        p1.setTicket(ticket);

        Passenger p2 = new Passenger();
        p2.setName("Bob");
        p2.setGender("Male");
        p2.setAge(28);
        p2.setSeatNumber("A2");
        p2.setTicket(ticket);

        List<Passenger> passengers = new ArrayList<>();
        passengers.add(p1);
        passengers.add(p2);

        ticket.setPassengers(passengers);

        assertEquals(2, ticket.getPassengers().size(), "Ticket should have 2 passengers");
        assertTrue(ticket.getPassengers().contains(p1), "Ticket should contain passenger Alice");
        assertTrue(ticket.getPassengers().contains(p2), "Ticket should contain passenger Bob");
        assertEquals(ticket, p1.getTicket(), "Alice's ticket should be correctly set");
        assertEquals(ticket, p2.getTicket(), "Bob's ticket should be correctly set");
    }
    
    @Test
    void testMealPreferenceOptional() {
        Passenger passenger = new Passenger();
        passenger.setName("Charlie");
        passenger.setGender("Male");
        passenger.setAge(30);
        passenger.setSeatNumber("B1");

        // mealPreference not set
        assertNull(passenger.getMealPreference(), "Meal preference can be null");

        // mealPreference set
        passenger.setMealPreference("Veg");
        assertEquals("Veg", passenger.getMealPreference(), "Meal preference should be set correctly");
    }

}