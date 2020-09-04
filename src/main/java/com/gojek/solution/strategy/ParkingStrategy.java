package com.gojek.solution.strategy;

import com.gojek.solution.exceptions.NoFreeSlotAvailableException;

public interface ParkingStrategy {
    public void addSlot(Integer slotNumber);
    public void removeSlot(Integer SlotNumber);
    public Integer getNextSlot() throws NoFreeSlotAvailableException;
}
