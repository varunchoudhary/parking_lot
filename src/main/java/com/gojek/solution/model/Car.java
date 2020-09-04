package com.gojek.solution.model;

public class Car {
    private String registrationNumber;
    private String color;

    public Car(final String registrationNumber, final String color) {
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColor() {
        return color;
    }
}
