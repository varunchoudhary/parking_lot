package com.gojek.solution.command;

import com.gojek.solution.OutputPrinter;
import com.gojek.solution.exceptions.InvalidCommandException;
import com.gojek.solution.model.Command;
import com.gojek.solution.service.ParkingLotService;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private Map<String, CommandExecutor> commands = new HashMap<String, CommandExecutor>();

    public CommandFactory(final ParkingLotService parkingLotService) {
//        final OutputPrinter outputPrinter = new OutputPrinter();
//        commands.put(
//                CreateParkingLotCommandExecutor.COMMAND_NAME,
//                new CreateParkingLotCommandExecutor(parkingLotService, outputPrinter));
//        commands.put(
//                ParkCommandExecutor.COMMAND_NAME,
//                new ParkCommandExecutor(parkingLotService, outputPrinter));
//        commands.put(
//                LeaveCommandExecutor.COMMAND_NAME,
//                new LeaveCommandExecutor(parkingLotService, outputPrinter));
//        commands.put(
//                StatusCommandExecutor.COMMAND_NAME,
//                new StatusCommandExecutor(parkingLotService, outputPrinter));
//        commands.put(
//                ColorToRegNumberCommandExecutor.COMMAND_NAME,
//                new ColorToRegNumberCommandExecutor(parkingLotService, outputPrinter));
//        commands.put(
//                ColorToSlotNumberCommandExecutor.COMMAND_NAME,
//                new ColorToSlotNumberCommandExecutor(parkingLotService, outputPrinter));
//        commands.put(
//                SlotForRegNumberCommandExecutor.COMMAND_NAME,
//                new SlotForRegNumberCommandExecutor(parkingLotService, outputPrinter));
//        commands.put(
//                ExitCommandExecutor.COMMAND_NAME,
//                new ExitCommandExecutor(parkingLotService, outputPrinter));
    }

    public CommandExecutor getCommandExecuter(Command command) throws InvalidCommandException {
        final CommandExecutor commandExecutor = commands.get(command.getCommandName());
        if(commandExecutor ==null)
            throw new InvalidCommandException();
        return commandExecutor;

    }
}
