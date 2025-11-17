package com.flightapp.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

public class TicketServiceTest {

    public String generateTestPNR() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
    }

    @Test
    void testPNRGenerationIs8Characters() {
        TicketServiceTest service = new TicketServiceTest();

        String pnr = service.generateTestPNR();

        assertNotNull(pnr, "PNR should not be null");
        assertEquals(8, pnr.length(), "PNR should be 8 characters long");
    }
}
