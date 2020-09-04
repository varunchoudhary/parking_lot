package com.gojek.solution.strategy;

public interface ParkingStrategy {
    public void addSlot(Integer slotNumber);
    public void removeSlot(Integer SlotNumber);
    public Integer getNextSlot();
}
