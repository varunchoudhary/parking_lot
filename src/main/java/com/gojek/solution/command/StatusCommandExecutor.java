package com.gojek.solution.command;

import com.gojek.solution.OutputPrinter;
import com.gojek.solution.exceptions.ParkingLotException;
import com.gojek.solution.model.Car;
import com.gojek.solution.model.Command;
import com.gojek.solution.model.Slot;
import com.gojek.solution.service.ParkingLotService;

import java.util.List;

public class StatusCommandExecutor extends CommandExecutor {
    public static String COMMAND_NAME = "status";

    public StatusCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService,outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().isEmpty();
    }

    @Override
    public void execute(Command command) throws ParkingLotException {
        final List<Slot> slots = parkingLotService.getOccupiedSlots();
        if(slots.isEmpty()){
            outputPrinter.parkingLotEmpty();
        }else{
            outputPrinter.statusHeader();
            for (Slot slot:slots){
                final Car car = slot.getParkedCar();
                final String slotNumber = slot.getSlotNumber().toString();
                outputPrinter.printWithNewLine(padString(slotNumber, 12)
                        + padString(car.getRegistrationNumber(), 19)
                        + car.getColor());
            }
        }
    }
    private static String padString(final String word, final int length) {
        String newWord = word;
        for(int count = word.length(); count < length; count++) {
            newWord = newWord + " ";
        }
        return newWord;
    }
}
