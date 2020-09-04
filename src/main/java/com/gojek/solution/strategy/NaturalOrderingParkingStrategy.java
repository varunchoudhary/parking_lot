package com.gojek.solution.strategy;

import com.gojek.solution.exceptions.NoFreeSlotAvailableException;

import java.util.TreeSet;

public class NaturalOrderingParkingStrategy implements ParkingStrategy{
    TreeSet<Integer> slotTreeSet;

    public NaturalOrderingParkingStrategy() {
        this.slotTreeSet = new TreeSet<Integer>();
    }
    @Override
    public void addSlot(Integer slotNumber){
        this.slotTreeSet.add(slotNumber);
    }

    @Override
    public void removeSlot(Integer slotNUmber){
        this.slotTreeSet.remove(slotNUmber);
    }

    @Override
    public Integer getNextSlot() throws NoFreeSlotAvailableException {
        if(slotTreeSet.isEmpty())
            throw new NoFreeSlotAvailableException();
        return this.slotTreeSet.first();
    }
}
