package com.gojek.solution.mode;

import com.gojek.solution.OutputPrinter;
import com.gojek.solution.command.CommandFactory;
import com.gojek.solution.exceptions.InvalidCommandException;
import com.gojek.solution.model.Command;

import java.io.*;
import java.nio.Buffer;

public class FileMode extends Mode {
    private String fileName;

    public FileMode(final CommandFactory commandFactory, final OutputPrinter outputPrinter,final String fileName) {
        super(commandFactory, outputPrinter);
        this.fileName = fileName;
    }

    @Override
    public void process() throws InvalidCommandException, IOException {
        final File file = new File(fileName);
        final BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            outputPrinter.invalidFile();
            return;
        }
        String instruction = reader.readLine();
        while(instruction != null){
            final Command command = new Command(instruction);
            processCommand(command);
            instruction = reader.readLine();
        }
    }
}
