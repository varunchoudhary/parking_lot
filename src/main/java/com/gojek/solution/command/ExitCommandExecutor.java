package com.gojek.solution.command;

import com.gojek.solution.OutputPrinter;
import com.gojek.solution.model.Command;
import com.gojek.solution.service.ParkingLotService;
import com.gojek.solution.strategy.ParkingStrategy;

public class ExitCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "exit";

    public ExitCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().isEmpty();
    }

    @Override
    public void execute(Command command) {
        outputPrinter.end();
    }
}
