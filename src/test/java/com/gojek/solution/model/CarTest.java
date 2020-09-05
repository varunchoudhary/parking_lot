package com.gojek.solution.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {

    @Test
    public void testCreateCar() {
        final Car testCar = new Car("KA-01-HH-1234","White");
    }

    @Test
    public void testGetRegistrationNumberCar() {
        final Car testCar = new Car("KA-01-HH-1234","White");
        assertEquals("KA-01-HH-1234",testCar.getRegistrationNumber());
    }

    @Test
    public void testGetCarColor() {
        final Car testCar = new Car("KA-01-HH-1234","White");
        assertEquals("White",testCar.getColor());
    }

}