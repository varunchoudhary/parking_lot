package com.gojek.solution.command;

import com.gojek.solution.OutputPrinter;
import com.gojek.solution.exceptions.ParkingLotException;
import com.gojek.solution.model.Command;
import com.gojek.solution.model.Slot;
import com.gojek.solution.service.ParkingLotService;

import java.util.List;
import java.util.Optional;

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
        final List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();
        final String regNumberToFind = command.getParams().get(0);
        final Optional<Slot> foundSlot = occupiedSlots.stream()
                .filter(slot -> slot.getParkedCar().getRegistrationNumber().equals(regNumberToFind))
                .findFirst();
        if(foundSlot.isPresent()){
            outputPrinter.printWithNewLine(foundSlot.get().getSlotNumber().toString());
        }else {
            outputPrinter.notFound();
        }
    }
}
