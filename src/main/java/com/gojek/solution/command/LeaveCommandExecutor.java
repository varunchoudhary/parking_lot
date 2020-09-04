package com.gojek.solution.command;

import com.gojek.solution.OutputPrinter;
import com.gojek.solution.exceptions.ParkingLotException;
import com.gojek.solution.model.Command;
import com.gojek.solution.service.ParkingLotService;

import java.util.List;

public class LeaveCommandExecutor extends CommandExecutor {
    public static final String COMMAND_NAME = "LEAVE";

    public LeaveCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService,outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
    final List<String> params = command.getParams();
        if(params.size()!=1) return false;
        try {
            Integer.parseInt(params.get(0));
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    @Override
    public void execute(Command command) throws ParkingLotException {

    }
}
