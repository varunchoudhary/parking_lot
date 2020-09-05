package com.gojek.solution.command;

import com.gojek.solution.OutputPrinter;
import com.gojek.solution.model.Command;
import com.gojek.solution.service.ParkingLotService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LeaveCommandExecutorTest {
    private ParkingLotService parkingLotService;
    private OutputPrinter outputPrinter;
    private LeaveCommandExecutor leaveCommandExecutor;

    @Before
    public void setUp() throws Exception {
        parkingLotService = mock(ParkingLotService.class);
        outputPrinter = mock(OutputPrinter.class);
        leaveCommandExecutor = new LeaveCommandExecutor(parkingLotService, outputPrinter);
    }

    @Test
    public void testValidCommand() {
        assertTrue(leaveCommandExecutor.validate(new Command("leave 1")));
    }

    @Test
    public void testInvalidCommand() {
        assertFalse(leaveCommandExecutor.validate(new Command("leave")));
        assertFalse(leaveCommandExecutor.validate(new Command("leave 1 2")));
        assertFalse(leaveCommandExecutor.validate(new Command("leave 1 a")));
        assertFalse(leaveCommandExecutor.validate(new Command("leave abcd")));
    }

    @Test
    public void testLeavingCarFromSlotMakesSlotFreeFromParkingService() {
        leaveCommandExecutor.execute(new Command("leave 1"));
        verify(parkingLotService).makeSlotFree(1);
        verify(outputPrinter).printWithNewLine("Slot number 1 is free");
    }
}