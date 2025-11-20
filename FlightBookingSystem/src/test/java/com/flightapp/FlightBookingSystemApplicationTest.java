package com.flightapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FlightBookingSystemApplicationTest {

    @Test
    void mainMethodRuns() {
        assertDoesNotThrow(() -> {
            SpringApplication app = new SpringApplication(FlightBookingSystemApplication.class);
            app.setWebApplicationType(WebApplicationType.NONE); // 👈 prevents port binding
            app.run();
        });
    }
}
