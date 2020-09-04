package com.gojek.solution;

import com.gojek.solution.command.CommandFactory;
import com.gojek.solution.exceptions.InvalidCommandException;
import com.gojek.solution.exceptions.InvalidModeException;
import com.gojek.solution.mode.FileMode;
import com.gojek.solution.mode.InteractiveMode;
import com.gojek.solution.service.ParkingLotService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InvalidCommandException {
        final OutputPrinter outputPrinter = new OutputPrinter();
        final ParkingLotService parkingLotService = new ParkingLotService();
        final CommandFactory commandFactory =
                new CommandFactory(parkingLotService);
        if (isInteractiveMode(args)) {
            new InteractiveMode(commandFactory, outputPrinter).process();
        } else if (isFileInputMode(args)) {
            new FileMode(commandFactory, outputPrinter, args[0]).process();
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
