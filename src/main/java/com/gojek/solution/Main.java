package com.gojek.solution;

import com.gojek.solution.command.CommandFactory;
import com.gojek.solution.exceptions.InvalidModeException;
import com.gojek.solution.service.ParkingLotService;

public class Main {
    public static void main(String[] args) {
        final OutputPrinter outputPrinter = new OutputPrinter();
        final ParkingLotService parkingLotService = new ParkingLotService();
        final CommandFactory commandFactory =
                new CommandFactory(parkingLotService);
        if (isInteractiveMode(args)) {
            new InteractiveMode(commandExecutorFactory, outputPrinter).process();
        } else if (isFileInputMode(args)) {
            new FileMode(commandExecutorFactory, outputPrinter, args[0]).process();
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
