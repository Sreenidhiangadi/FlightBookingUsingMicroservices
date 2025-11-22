package com.flightapp.service;

import com.flightapp.entity.Flight;
import com.flightapp.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddFlight() {
        Flight flight = new Flight();
        flightService.addFlight(flight);
        verify(flightRepository, times(1)).save(flight);
    }

    @Test
    void testDeleteFlight() {
        String result = flightService.deleteFlight(1L);
        verify(flightRepository, times(1)).deleteById(1L);
        assertEquals("Flight deleted succesfully", result);
    }

    @Test
    void testGetAllFlights() {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight());
        when(flightRepository.findAll()).thenReturn(flights);

        List<Flight> result = flightService.getAllFlights();
        assertEquals(1, result.size());
    }

    @Test
    void testUpdateFlight() {
        Flight flight = new Flight();
        flight.setId(1L);

        Map<String, Object> updates = new HashMap<>();
        updates.put("airline", "AirX");
        updates.put("fromPlace", "A");
        updates.put("toPlace", "B");
        updates.put("departureTime", "2025-11-21T10:00:00");
        updates.put("arrivalTime", "2025-11-21T12:00:00");
        updates.put("price", 500);
        updates.put("totalSeats", 100);
        updates.put("availableSeats", 50);

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(flightRepository.save(flight)).thenReturn(flight);

        Flight result = flightService.updateFlight(1L, updates);

        assertEquals("AirX", result.getAirline());
        assertEquals("A", result.getFromPlace());
        assertEquals("B", result.getToPlace());
        assertEquals(500, result.getPrice());
        assertEquals(100, result.getTotalSeats());
        assertEquals(50, result.getAvailableSeats());
    }

    @Test
    void testSearchFlightByIdFound() {
        Flight flight = new Flight();
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        Flight result = flightService.searchFlightById(1L);
        assertEquals(flight, result);
    }

    @Test
    void testSearchFlightByIdNotFound() {
        when(flightRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> flightService.searchFlightById(1L));
    }

    @Test
    void testSearchFlights() {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight());
        when(flightRepository.findByFromPlaceAndToPlaceAndDepartureTimeBetween(
                anyString(), anyString(), any(), any())).thenReturn(flights);

        List<Flight> result = flightService.searchFlights("A","B", LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        assertEquals(1, result.size());
    }

    @Test
    void testSearchFlightsByAirline() {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight());
        when(flightRepository.findByFromPlaceAndToPlaceAndAirline(anyString(), anyString(), anyString()))
                .thenReturn(flights);

        List<Flight> result = flightService.searchFlightsByAirline("A","B","AirX");
        assertEquals(1, result.size());
    }
}
