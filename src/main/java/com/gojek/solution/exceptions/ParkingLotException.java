package com.gojek.solution.exceptions;

public class ParkingLotException extends Throwable {
    public ParkingLotException() {
    }

    public ParkingLotException(String message) {
        super(message);
    }
}
