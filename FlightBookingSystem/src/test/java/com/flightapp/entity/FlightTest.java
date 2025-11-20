package com.flightapp.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class FlightTest {

    @Test
    void testFlightGettersAndSetters() {
        Flight flight = new Flight();
        flight.setAirline("AirIndia");
        flight.setFromPlace("Delhi");
        flight.setToPlace("Mumbai");
        flight.setDepartureTime(LocalDateTime.now());
        flight.setArrivalTime(LocalDateTime.now().plusHours(2));
        flight.setPrice(5000);
        flight.setTotalSeats(150);
        flight.setAvailableSeats(150);

        assertThat(flight.getAirline()).isEqualTo("AirIndia");
        assertThat(flight.getFromPlace()).isEqualTo("Delhi");
        assertThat(flight.getToPlace()).isEqualTo("Mumbai");
        assertThat(flight.getPrice()).isEqualTo(5000);
        assertThat(flight.getTotalSeats()).isEqualTo(150);
        assertThat(flight.getAvailableSeats()).isEqualTo(150);
    }
}
