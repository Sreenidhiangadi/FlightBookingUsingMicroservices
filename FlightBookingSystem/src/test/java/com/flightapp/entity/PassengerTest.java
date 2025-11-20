package com.flightapp.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PassengerTest {

    @Test
    void testPassengerGettersAndSetters() {
        Passenger passenger = new Passenger();
        passenger.setName("John");
        passenger.setGender("Male");
        passenger.setAge(30);
        passenger.setSeatNumber("12A");
        passenger.setMealPreference("Veg");

        assertThat(passenger.getName()).isEqualTo("John");
        assertThat(passenger.getGender()).isEqualTo("Male");
        assertThat(passenger.getAge()).isEqualTo(30);
        assertThat(passenger.getSeatNumber()).isEqualTo("12A");
        assertThat(passenger.getMealPreference()).isEqualTo("Veg");
    }
}
