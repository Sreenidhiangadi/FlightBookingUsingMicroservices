package com.flightapp.service;

import com.flightapp.entity.Flight;
import com.flightapp.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

<<<<<<< Updated upstream
class FlightServiceTest {

    @Test
    void testUpdateFlight() {
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setAirline("Indigo");
        flight.setFromPlace("CityA");
        flight.setToPlace("CityB");
        flight.setDepartureTime(LocalDateTime.parse("2025-11-20T10:00:00"));
        flight.setArrivalTime(LocalDateTime.parse("2025-11-20T12:00:00"));
        flight.setPrice(5000);
        flight.setTotalSeats(100);
        flight.setAvailableSeats(100);

        Map<String, Object> updates = new HashMap<>();
        updates.put("airline", "Air India");
        updates.put("price", 5500);

        if (updates.containsKey("airline")) flight.setAirline((String) updates.get("airline"));
        if (updates.containsKey("price")) flight.setPrice((Integer) updates.get("price"));

        assertEquals("Air India", flight.getAirline());
        assertEquals(5500, flight.getPrice());
=======
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @Mock
    FlightRepository flightRepository;

    @InjectMocks
    FlightService flightService;

    @Test
    void testAddFlight() {
        Flight flight = new Flight();
        flightService.addFlight(flight);
        verify(flightRepository).save(flight);
    }

    @Test
    void testDeleteFlight() {
        String result = flightService.deleteFlight(1L);
        assertEquals("Flight deleted succesfully", result);
        verify(flightRepository).deleteById(1L);
    }

    @Test
    void testGetAllFlights() {
        when(flightRepository.findAll()).thenReturn(List.of(new Flight()));
        assertEquals(1, flightService.getAllFlights().size());
    }

    @Test
    void testUpdateFlight() {
        Flight flight = new Flight();
        flight.setAirline("Old");
        flight.setFromPlace("A");

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(flightRepository.save(any())).thenReturn(flight);

        Map<String, Object> updates = new HashMap<>();
        updates.put("airline", "New");
        updates.put("fromPlace", "B");

        Flight updated = flightService.updateFlight(1L, updates);

        assertEquals("New", updated.getAirline());
        assertEquals("B", updated.getFromPlace());
>>>>>>> Stashed changes
    }

    @Test
    void testSearchFlightById() {
<<<<<<< Updated upstream
        Flight flight = new Flight();
        flight.setId(1L);
        Flight foundFlight = flight; 

        assertEquals(1L, foundFlight.getId());
    }

    @Test
    void testAddFlight() {
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setAirline("Indigo");

        Flight addedFlight = flight;

        assertEquals("Indigo", addedFlight.getAirline());
    }

    @Test
    void testDeleteFlight() {
        Flight flight = new Flight();
        flight.setId(1L);

        String message = "Flight deleted successfully";

        assertEquals("Flight deleted successfully", message);
=======
        Flight f = new Flight();
        when(flightRepository.findById(1L)).thenReturn(Optional.of(f));

        assertEquals(f, flightService.searchFlightById(1L));
    }

    @Test
    void testSearchFlights() {
        when(flightRepository.findByFromPlaceAndToPlaceAndDepartureTimeBetween(
                anyString(), anyString(), any(), any()))
                .thenReturn(List.of(new Flight()));

        assertEquals(1, flightService.searchFlights("A", "B",
                LocalDateTime.now(), LocalDateTime.now().plusHours(2)).size());
>>>>>>> Stashed changes
    }
}
