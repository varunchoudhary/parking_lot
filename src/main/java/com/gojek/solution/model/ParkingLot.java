package com.gojek.solution.model;

import com.gojek.solution.exceptions.InvalidSlotException;
import com.gojek.solution.exceptions.ParkingLotException;
import com.gojek.solution.exceptions.SlotAlreadyOccupiedException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static int MAX_CAPACITY = 100000;
    private int capacity;
    private Map<Integer,Slot> slots;

    public int getCapacity() { return capacity; }
    public Map<Integer, Slot> getSlots() { return slots; }

    public ParkingLot(int capacity) throws ParkingLotException {
        if(capacity > MAX_CAPACITY ||capacity <=0)
            throw new ParkingLotException("capacity given is not valid");
        this.capacity = capacity;
        this.slots = new HashMap<Integer, Slot>();
    }

     private Slot getSlot(final Integer slotNumber) throws InvalidSlotException {
        if(slotNumber> getCapacity() || slotNumber<=0)
            throw new InvalidSlotException();
        final Map<Integer,Slot> allSlots = getSlots();
        if(!allSlots.containsKey(slotNumber)){
            allSlots.put(slotNumber,new Slot(slotNumber));
        }
        return allSlots.get(slotNumber);
     }

     public Slot park(final Car car,final Integer slotNumber) throws InvalidSlotException, SlotAlreadyOccupiedException {
        final Slot slot = getSlot(slotNumber);
        if(!slot.isSlotFree()){
            throw new SlotAlreadyOccupiedException();
        }
         slot.assignCar(car);
         return slot;
     }

     public Slot makeSlotFree(final Integer slotNumber) throws InvalidSlotException {
        final Slot slot = getSlot(slotNumber);
        slot.unassignCar();
        return slot;
     }
}
