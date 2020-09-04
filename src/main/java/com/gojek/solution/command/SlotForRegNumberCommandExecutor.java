package com.gojek.solution.command;

import com.gojek.solution.OutputPrinter;
import com.gojek.solution.exceptions.ParkingLotException;
import com.gojek.solution.model.Command;
import com.gojek.solution.model.Slot;
import com.gojek.solution.service.ParkingLotService;

import java.util.List;

public class SlotForRegNumberCommandExecutor extends CommandExecutor {
    public static String COMMAND_NAME = "slot_number_for_registration_number";
    public SlotForRegNumberCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }
    public boolean validate(final Command command) {
        return command.getParams().size() == 1;
    }

    @Override
    public void execute(Command command) throws ParkingLotException {
        final List<Slot> occupiedSlot = parkingLotService.getOccupiedSlots();
        final String registrationNumberToFind = command.getParams().get(0);
        for(Slot slot:occupiedSlot){
            if(slot.getParkedCar().getRegistrationNumber().equals(registrationNumberToFind)){
                outputPrinter.printWithNewLine(slot.getSlotNumber().toString());
            }
        }

    }
}
