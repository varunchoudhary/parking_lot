package com.gojek.solution.command;

import com.gojek.solution.OutputPrinter;
import com.gojek.solution.exceptions.InvalidSlotException;
import com.gojek.solution.exceptions.NoFreeSlotAvailableException;
import com.gojek.solution.exceptions.ParkingLotException;
import com.gojek.solution.exceptions.SlotAlreadyOccupiedException;
import com.gojek.solution.model.Car;
import com.gojek.solution.model.Command;
import com.gojek.solution.service.ParkingLotService;

public class ParkCommandExecutor extends CommandExecutor {
    public static final String COMMAND_NAME = "park";

    public ParkCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService,outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().size() == 2;
    }

    @Override
    public void execute(Command command) throws ParkingLotException {
        final Car car =  new Car(command.getParams().get(0),command.getParams().get(1));
        try{
            final Integer slot = parkingLotService.park(car);
            outputPrinter.printWithNewLine("Allocated slot number: " + slot);
        } catch (NoFreeSlotAvailableException e) {
            outputPrinter.parkingLotFull();
        } catch (SlotAlreadyOccupiedException e) {
            outputPrinter.slotAlreadyfilled();
        } catch (InvalidSlotException e) {
            outputPrinter.notFound();
        }
    }
}
