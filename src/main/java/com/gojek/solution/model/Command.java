package com.gojek.solution.model;

import com.gojek.solution.exceptions.InvalidCommandException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {
    private static final String SPACE =" ";
    private String commandName;
    private List<String> params;

    public String getCommandName() { return commandName; }

    public List<String> getParams() { return params; }

    public Command(final String inputLine) throws InvalidCommandException {
        final List<String> inputList = Arrays.stream(inputLine.trim().split(SPACE))
                .map(String::trim)
                .filter(token -> (token.length() > 0)).collect(Collectors.toList());
        if (inputList.size() == 0) { throw new InvalidCommandException(); }
        commandName = inputList.get(0).toLowerCase();
        inputList.remove(0);
        params = inputList;
    }
}
