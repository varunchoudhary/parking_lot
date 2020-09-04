package com.gojek.solution;

import com.gojek.solution.exceptions.InvalidModeException;
import com.gojek.solution.service.ParkingLotService;

public class Main {
    public static void main(String[] args) {
        final OutputPrinter outputPrinter = new OutputPrinter();
        final ParkingLotService parkingLotService = new ParkingLotService();
        if (isInteractiveMode(args)) {

        } else if (isFileInputMode(args)) {

        } else {
            throw new InvalidModeException();
        }
    }

    private static boolean isFileInputMode(final String[] args) {
        return args.length == 1;
    }
    private static boolean isInteractiveMode(final String[] args) {
        return args.length == 0;
    }
}
