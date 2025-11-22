package com.flightapp.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightTypeTest {

    @Test
    void testEnumValues() {
        assertEquals(FlightType.ONE_WAY, FlightType.valueOf("ONE_WAY"));
        assertEquals(FlightType.ROUND_TRIP, FlightType.valueOf("ROUND_TRIP"));
    }
}
