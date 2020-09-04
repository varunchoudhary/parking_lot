package com.gojek.solution.command;

import com.gojek.solution.OutputPrinter;
import com.gojek.solution.exceptions.ParkingLotException;
import com.gojek.solution.model.Command;
import com.gojek.solution.model.Slot;
import com.gojek.solution.service.ParkingLotService;

import java.util.List;
import java.util.stream.Collectors;

public class ColorToSlotNumberCommandExecutor extends CommandExecutor {
    public static final String COMMAND_NAME = "slot_numbers_for_cars_with_colour";

    public ColorToSlotNumberCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService,outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().size() == 1;
    }

    @Override
    public void execute(Command command) throws ParkingLotException {
        final List<Slot> slotsForColor = parkingLotService.getSlotsForColor(command.getParams().get(0));
        if(slotsForColor.isEmpty()){
            outputPrinter.notFound();
        }else{
            final String result =
                    slotsForColor.stream()
                            .map(slot -> slot.getSlotNumber().toString())
                            .collect(Collectors.joining(", "));
            outputPrinter.printWithNewLine(result);
        }
    }
}
