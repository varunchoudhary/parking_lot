package com.gojek.solution.service;

import com.gojek.solution.exceptions.InvalidSlotException;
import com.gojek.solution.exceptions.NoFreeSlotAvailableException;
import com.gojek.solution.exceptions.ParkingLotException;
import com.gojek.solution.exceptions.SlotAlreadyOccupiedException;
import com.gojek.solution.model.Car;
import com.gojek.solution.model.ParkingLot;
import com.gojek.solution.model.Slot;
import com.gojek.solution.strategy.ParkingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLotService {
    private ParkingLot parkingLot;
    private ParkingStrategy parkingStrategy;

    public void createParkingLot(final ParkingLot parkingLot,final ParkingStrategy parkingStrategy) throws ParkingLotException {
        if(this.parkingLot != null)
            throw new ParkingLotException("Parking lot already exists.");
        this.parkingLot = parkingLot;
        this.parkingStrategy = parkingStrategy;
        for(int i=1;i<=parkingLot.getCapacity();i++){
            parkingStrategy.addSlot(i);
        }
    }
    public Integer park(final Car car) throws ParkingLotException, NoFreeSlotAvailableException, SlotAlreadyOccupiedException, InvalidSlotException {
        validateParkingLotExists();
        final Integer nextFreeSlot = parkingStrategy.getNextSlot();
        parkingLot.park(car,nextFreeSlot);
        parkingStrategy.removeSlot(nextFreeSlot);
        return nextFreeSlot;
    }

    public void makeSlotFree(final Integer slotNumber) throws ParkingLotException, InvalidSlotException {
        validateParkingLotExists();
        parkingLot.makeSlotFree(slotNumber);
        parkingStrategy.addSlot(slotNumber);
    }
    public List<Slot> getOccupiedSlots() throws ParkingLotException {
        validateParkingLotExists();
        final List<Slot> occupiedSlotsList = new ArrayList<Slot>();
        final Map<Integer, Slot> allSlots = parkingLot.getSlots();

        for (int i = 1; i <= parkingLot.getCapacity(); i++) {
            if (allSlots.containsKey(i)) {
                final Slot slot = allSlots.get(i);
                if (!slot.isSlotFree()) {
                    occupiedSlotsList.add(slot);
                }
            }
        }
        return occupiedSlotsList;
    }

    public List<Slot>getSlotsForColor(final String color) throws ParkingLotException {
        final List<Slot> occupiedSlots = getOccupiedSlots();
        List<Slot> final_colored_car = new ArrayList<Slot>();
        for (Slot slot : occupiedSlots){
            if (slot.getParkedCar().getColor().equals(color)) {
                final_colored_car.add(slot);
            }
        }
    return final_colored_car;
    }

    private void validateParkingLotExists() throws ParkingLotException {
        if (parkingLot == null) {
            throw new ParkingLotException("Parking lot does not exists to park.");
        }
    }


}
