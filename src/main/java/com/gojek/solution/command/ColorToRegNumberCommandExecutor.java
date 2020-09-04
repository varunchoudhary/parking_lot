package com.gojek.solution.command;

import com.gojek.solution.OutputPrinter;
import com.gojek.solution.exceptions.ParkingLotException;
import com.gojek.solution.model.Command;
import com.gojek.solution.model.Slot;
import com.gojek.solution.service.ParkingLotService;

import java.util.List;

public class ColorToRegNumberCommandExecutor extends CommandExecutor {
    public static final String COMMAND_NAME = "registration_numbers_for_cars_with_colour";

    public ColorToRegNumberCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService,outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().size()==1;
    }

    @Override
    public void execute(Command command) throws ParkingLotException {
        final List<Slot> slots =  parkingLotService.getSlotsForColor(command.getParams().get(0));
        if(slots.isEmpty())
            outputPrinter.notFound();
        else{
             StringBuilder registrationNumberForColor= new StringBuilder();
             for (Slot slot:slots)
                 if(slot.getParkedCar().getColor().equals(command.getParams().get(0)))
                     registrationNumberForColor.append(slot.getParkedCar().getRegistrationNumber());
             outputPrinter.printWithNewLine(registrationNumberForColor.toString());
        }
    }
}
