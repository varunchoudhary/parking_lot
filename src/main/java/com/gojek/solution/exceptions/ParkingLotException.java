package com.gojek.solution.exceptions;

public class ParkingLotException extends RuntimeException {
    public ParkingLotException() {
    }

    public ParkingLotException(String message) {
        super(message);
    }
}
