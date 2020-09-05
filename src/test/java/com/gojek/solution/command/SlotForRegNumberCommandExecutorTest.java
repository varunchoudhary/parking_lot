package com.gojek.solution.command;

import com.gojek.solution.OutputPrinter;
import com.gojek.solution.model.Car;
import com.gojek.solution.model.Command;
import com.gojek.solution.model.Slot;
import com.gojek.solution.service.ParkingLotService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SlotForRegNumberCommandExecutorTest {
    private ParkingLotService parkingLotService;
    private OutputPrinter outputPrinter;
    private SlotForRegNumberCommandExecutor slotForRegNumberCommandExecutor;

    @Before
    public void setup(){
        parkingLotService = mock(ParkingLotService.class);
        outputPrinter = mock(OutputPrinter.class);
        slotForRegNumberCommandExecutor = new SlotForRegNumberCommandExecutor(parkingLotService,outputPrinter);
    }

    @Test
    public void testValidCommand(){
       assertTrue(slotForRegNumberCommandExecutor.validate(new Command("slot_number_for_registration_number AB-01-CP-1230")));
    }
    @Test
    public void testInvalidCommand() {
        assertFalse(
                slotForRegNumberCommandExecutor.validate(
                        new Command("slot_number_for_registration_number")));
        assertFalse(
                slotForRegNumberCommandExecutor.validate(
                        new Command("slot_number_for_registration_number AB-01-CP-1230 b")));
    }

    @Test
    public void testCorrectSlotNumberForValidRegistrationNumber() {
        final Slot slot1 = new Slot(1);
        slot1.assignCar(new Car("reg-1", "white"));

        final Slot slot2 = new Slot(2);
        slot2.assignCar(new Car("AB-01-CP-1230", "blue"));

        final Slot slot3 = new Slot(3);
        slot3.assignCar(new Car("reg-2", "blue"));

        when(parkingLotService.getOccupiedSlots()).thenReturn(Arrays.asList(slot1, slot2, slot3));

        slotForRegNumberCommandExecutor.execute(
                new Command("slot_number_for_registration_number AB-01-CP-1230"));
        verify(outputPrinter).printWithNewLine("2");
    }
}