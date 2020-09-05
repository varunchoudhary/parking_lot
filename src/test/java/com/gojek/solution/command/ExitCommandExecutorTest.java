package com.gojek.solution.command;

import com.gojek.solution.OutputPrinter;
import com.gojek.solution.model.Command;
import com.gojek.solution.service.ParkingLotService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ExitCommandExecutorTest {
    private ParkingLotService parkingLotService;
    private OutputPrinter outputPrinter;
    private ExitCommandExecutor exitCommandExecutor;

    @Before
    public void setup() throws Exception{
        parkingLotService = mock(ParkingLotService.class);
        outputPrinter = mock(OutputPrinter.class);
        exitCommandExecutor = new ExitCommandExecutor(parkingLotService,outputPrinter);
    }
    @Test
    public void testValidCommand() {
        assertTrue(exitCommandExecutor.validate(new Command("exit")));
    }

    @Test
    public void testInvalidCommand() {
        assertFalse(exitCommandExecutor.validate(new Command("exit 1")));
        assertFalse(exitCommandExecutor.validate(new Command("exit 1 2")));
        assertFalse(exitCommandExecutor.validate(new Command("exit a")));
    }

    @Test
    public void textCommandExecution() {
        exitCommandExecutor.execute(new Command("exit"));
        verify(outputPrinter).end();
    }
}