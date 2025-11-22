package com.flightapp.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flightapp.entity.Flight;

class FlightTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testFlightSettersAndGetters() {
        Flight f = new Flight();
        f.setAirline("Indigo");
        f.setFromPlace("Hyd");
        f.setToPlace("Delhi");
        f.setDepartureTime(LocalDateTime.now());
        f.setArrivalTime(LocalDateTime.now().plusHours(2));
        f.setPrice(5000);
        f.setTotalSeats(180);
        f.setAvailableSeats(150);

        assertEquals("Indigo", f.getAirline());
        assertEquals("Hyd", f.getFromPlace());
        assertEquals("Delhi", f.getToPlace());
        assertEquals(5000, f.getPrice());
        assertEquals(180, f.getTotalSeats());
        assertEquals(150, f.getAvailableSeats());
    }

    @Test
    void ForBlankAirline() {
        Flight f = new Flight();
        f.setAirline("");
        var violations = validator.validate(f);
        assertFalse(violations.isEmpty());
    }
    
    @Test
    void ForBlankFromPlace() {
        Flight f = new Flight();
        f.setFromPlace("");
        var violations = validator.validate(f);
        assertFalse(violations.isEmpty());
    }
    
    @Test
    void ForBlankToPlace() {
        Flight f = new Flight();
        f.setToPlace("");
        var violations = validator.validate(f);
        assertFalse(violations.isEmpty());
    }
    
    @Test
    void testDepartureTimeNullShouldFail() {
        Flight flight = new Flight();
        flight.setAirline("Indigo");
        flight.setFromPlace("Delhi");
        flight.setToPlace("Mumbai");
        flight.setDepartureTime(null);  // ❌ Should fail
        flight.setArrivalTime(LocalDateTime.now().plusHours(2));
        flight.setPrice(5000);
        flight.setTotalSeats(100);
        flight.setAvailableSeats(50);

        Set<ConstraintViolation<Flight>> violations = validator.validate(flight);

        boolean hasError = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("departureTime"));

        assertTrue(hasError, "Validation should fail when departureTime is null");
    }
    
    @Test
    void testArrivalTimeNullShouldFail() {
        Flight flight = new Flight();
        flight.setAirline("Indigo");
        flight.setFromPlace("Delhi");
        flight.setToPlace("Mumbai");
        flight.setDepartureTime(LocalDateTime.now());
        flight.setArrivalTime(null);  // ❌ Should fail
        flight.setPrice(5000);
        flight.setTotalSeats(100);
        flight.setAvailableSeats(50);

        Set<ConstraintViolation<Flight>> violations = validator.validate(flight);

        boolean hasError = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("arrivalTime"));

        assertTrue(hasError, "Validation should fail when arrivalTime is null");
    }
    
    private Flight getValidFlight() {
        Flight f = new Flight();
        f.setAirline("Indigo");
        f.setFromPlace("Hyd");
        f.setToPlace("Delhi");
        f.setDepartureTime(LocalDateTime.now().plusHours(1));
        f.setArrivalTime(LocalDateTime.now().plusHours(3));
        f.setPrice(1000);
        f.setTotalSeats(100);
        f.setAvailableSeats(50);
        return f;
    }
    
    @Test
    void testPriceLessThan1ShouldFail() {
        Flight f = getValidFlight();
        f.setPrice(0); // invalid

        Set<ConstraintViolation<Flight>> violations = validator.validate(f);
        assertFalse(violations.isEmpty(), "Price less than 1 should fail");
    }

    @Test
    void testPriceEqual1ShouldPass() {
        Flight f = getValidFlight();
        f.setPrice(1);

        Set<ConstraintViolation<Flight>> violations = validator.validate(f);
        assertTrue(violations.isEmpty(), "Price equal to 1 should pass");
    }

    @Test
    void testPricePositiveShouldPass() {
        Flight f = getValidFlight();
        f.setPrice(500);

        Set<ConstraintViolation<Flight>> violations = validator.validate(f);
        assertTrue(violations.isEmpty(), "Positive price should pass");
    }
    
    @Test
    void testTotalSeatsLessThan1ShouldFail() {
        Flight f = getValidFlight();
        f.setTotalSeats(0);

        Set<ConstraintViolation<Flight>> violations = validator.validate(f);
        assertFalse(violations.isEmpty(), "Total seats < 1 should fail");
    }

    @Test
    void testTotalSeatsEqual1ShouldPass() {
        Flight f = getValidFlight();
        f.setTotalSeats(1);

        Set<ConstraintViolation<Flight>> violations = validator.validate(f);
        assertTrue(violations.isEmpty(), "Total seats = 1 should pass");
    }
    
    @Test
    void testAvailableSeatsNegativeShouldFail() {
        Flight f = getValidFlight();
        f.setAvailableSeats(-1);

        Set<ConstraintViolation<Flight>> violations = validator.validate(f);
        assertFalse(violations.isEmpty(), "Available seats < 0 should fail");
    }

    @Test
    void testAvailableSeatsZeroShouldPass() {
        Flight f = getValidFlight();
        f.setAvailableSeats(0);

        Set<ConstraintViolation<Flight>> violations = validator.validate(f);
        assertTrue(violations.isEmpty(), "Available seats = 0 should pass");
    }

    @Test
    void testAvailableSeatsPositiveShouldPass() {
        Flight f = getValidFlight();
        f.setAvailableSeats(10);

        Set<ConstraintViolation<Flight>> violations = validator.validate(f);
        assertTrue(violations.isEmpty(), "Positive available seats should pass");
    }
    
    @Test
    void testEqualsAndHashCode() {
        Flight f1 = new Flight();
        f1.setId(1L);
        f1.setAirline("Indigo");
        f1.setFromPlace("Delhi");
        f1.setToPlace("Mumbai");

        Flight f2 = new Flight();
        f2.setId(1L);
        f2.setAirline("Indigo");
        f2.setFromPlace("Delhi");
        f2.setToPlace("Mumbai");

        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
    }
    
    @Test
    void testToStringNotNull() {
        Flight flight = new Flight();
        flight.setId(10L);
        flight.setAirline("Air India");

        String toString = flight.toString();

        assertNotNull(toString);
        assertFalse(toString.isEmpty());
        assertTrue(toString.contains("Air India"));
    }
    
    @Test
    void testTwoFlightsWithSameFieldsAreEqual() {
        Flight f1 = new Flight();
        f1.setAirline("Vistara");
        f1.setFromPlace("Hyderabad");
        f1.setToPlace("Chennai");

        Flight f2 = new Flight();
        f2.setAirline("Vistara");
        f2.setFromPlace("Hyderabad");
        f2.setToPlace("Chennai");

        assertEquals(f1, f2);
    }
    
    @Test
    void testTwoFlightsWithDifferentIdNotEqual() {
        Flight f1 = new Flight();
        f1.setId(1L);
        f1.setAirline("Air Asia");

        Flight f2 = new Flight();
        f2.setId(2L);
        f2.setAirline("Air Asia");

        assertNotEquals(f1, f2);
    }
    
    @Test
    void testArrivalTimeAfterDepartureTime() {
        Flight flight = new Flight();
        flight.setDepartureTime(LocalDateTime.of(2025, 1, 1, 10, 0));
        flight.setArrivalTime(LocalDateTime.of(2025, 1, 1, 13, 0));

        boolean isValid = flight.getArrivalTime().isAfter(flight.getDepartureTime());

        assertTrue(isValid, "Arrival time should be after departure time");
    }
    
    @Test
    void testArrivalTimeBeforeDepartureTimeShouldFail() {
        Flight flight = new Flight();
        flight.setDepartureTime(LocalDateTime.of(2025, 1, 1, 10, 0));
        flight.setArrivalTime(LocalDateTime.of(2025, 1, 1, 9, 0));

        boolean isInvalid = flight.getArrivalTime().isBefore(flight.getDepartureTime());

        assertTrue(isInvalid, "Arrival time is incorrectly before departure time");
    }
    
    @Test
    void testAvailableSeatsNotMoreThanTotalSeats() {
        Flight flight = new Flight();
        flight.setTotalSeats(50);
        flight.setAvailableSeats(40);

        boolean isValid = flight.getAvailableSeats() <= flight.getTotalSeats();

        assertTrue(isValid, "Available seats should not be more than total seats");
    }
    
    @Test
    void testAvailableSeatsMoreThanTotalSeatsShouldFail() {
        Flight flight = new Flight();
        flight.setTotalSeats(50);
        flight.setAvailableSeats(60);

        boolean isInvalid = flight.getAvailableSeats() > flight.getTotalSeats();

        assertTrue(isInvalid, "Available seats incorrectly exceed total seats");
    }
    
    @Test
    void testFlightFullyBookedWhenAvailableSeatsZero() {
        Flight flight = new Flight();
        flight.setAvailableSeats(0);

        boolean isFullyBooked = (flight.getAvailableSeats() == 0);

        assertTrue(isFullyBooked, "Flight should be considered fully booked when available seats are zero");
    }
    
    @Test
    void testFlightNotFullyBookedWhenSeatsAvailable() {
        Flight flight = new Flight();
        flight.setAvailableSeats(5);

        boolean isFullyBooked = (flight.getAvailableSeats() == 0);

        assertFalse(isFullyBooked, "Flight is not fully booked when available seats > 0");
    }

    @Test
    void testFlightTicketRelation() {
        Flight f = new Flight();
        Ticket t = new Ticket();

        f.getDepartureTickets().add(t);
        assertEquals(1, f.getDepartureTickets().size());
    }
}