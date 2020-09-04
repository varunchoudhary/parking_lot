package com.gojek.solution.mode;

import com.gojek.solution.OutputPrinter;
import com.gojek.solution.command.CommandExecutor;
import com.gojek.solution.command.CommandFactory;
import com.gojek.solution.exceptions.InvalidCommandException;
import com.gojek.solution.exceptions.ParkingLotException;
import com.gojek.solution.model.Command;

import java.io.IOException;

public abstract class Mode {
    private CommandFactory commandFactory;
    protected OutputPrinter outputPrinter;

    public Mode(CommandFactory commandFactory, OutputPrinter outputPrinter) {
        this.commandFactory = commandFactory;
        this.outputPrinter = outputPrinter;
    }
    protected void processCommand(final Command command) throws InvalidCommandException, ParkingLotException {
        final CommandExecutor commandExecutor = commandFactory.getCommandExecuter(command);
        if(commandExecutor.validate(command)){
            commandExecutor.execute(command);
        }else{
            throw new InvalidCommandException();
        }
    }

    public abstract void process() throws InvalidCommandException, IOException, ParkingLotException;
}
