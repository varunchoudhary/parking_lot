package com.gojek.solution.mode;

import com.gojek.solution.OutputPrinter;
import com.gojek.solution.command.CommandFactory;
import com.gojek.solution.command.ExitCommandExecutor;
import com.gojek.solution.exceptions.InvalidCommandException;
import com.gojek.solution.model.Command;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InteractiveMode  extends Mode{

    public InteractiveMode(CommandFactory commandFactory, OutputPrinter outputPrinter) {
        super(commandFactory, outputPrinter);
    }

    @Override
    public void process() throws InvalidCommandException, IOException {
        outputPrinter.welcome();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            final String instruction = reader.readLine();
            final Command command =  new Command(instruction);
            processCommand(command);
            if(command.getCommandName().equals(ExitCommandExecutor.COMMAND_NAME)){
                break;
            }
        }
    }
}
