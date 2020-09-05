package com.gojek.solution.command;

import com.gojek.solution.OutputPrinter;
import com.gojek.solution.model.Car;
import com.gojek.solution.model.Command;
import com.gojek.solution.service.ParkingLotService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class ParkCommandExecutorTest {
    private ParkingLotService parkingLotService;
    private OutputPrinter outputPrinter;
    private ParkCommandExecutor parkCommandExecutor;

    @Before
    public void setUp() throws Exception {
        parkingLotService = mock(ParkingLotService.class);
        outputPrinter = mock(OutputPrinter.class);
        parkCommandExecutor = new ParkCommandExecutor(parkingLotService, outputPrinter);
    }

    @Test
    public void testValidCommand() {
        assertTrue(parkCommandExecutor.validate(new Command("park test-command-number white")));
    }

    @Test
    public void testInvalidCommand() {
        assertFalse(parkCommandExecutor.validate(new Command("park")));
        assertFalse(parkCommandExecutor.validate(new Command("park test-car-number")));
        assertFalse(parkCommandExecutor.validate(new Command("park test-car-number white abcd")));
    }

    @Test
    public void testCommandExecutionWhenParkingSucceeds() {
        when(parkingLotService.park(any())).thenReturn(1);
        parkCommandExecutor.execute(new Command("park test-car-number white"));

        final ArgumentCaptor<Car> argument = ArgumentCaptor.forClass(Car.class);
        verify(parkingLotService).park(argument.capture());
        assertEquals("test-car-number", argument.getValue().getRegistrationNumber());
        assertEquals("white", argument.getValue().getColor());

        verify(outputPrinter).printWithNewLine("Allocated slot number: 1");
    }
}