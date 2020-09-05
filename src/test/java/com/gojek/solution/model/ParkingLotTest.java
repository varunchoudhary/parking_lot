package com.gojek.solution.model;

import com.gojek.solution.exceptions.InvalidSlotException;
import com.gojek.solution.exceptions.ParkingLotException;
import com.gojek.solution.exceptions.SlotAlreadyOccupiedException;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class ParkingLotTest {

    @Test(expected = ParkingLotException.class)
    public void testNegativeCapacity() {
        new ParkingLot(-1);
    }

    @Test(expected = ParkingLotException.class)
    public void testZeroCapacity(){new ParkingLot(0);}

    @Test
    public void testValidCapacity(){new ParkingLot(100);}

    @Test(expected = ParkingLotException.class)
    public void testMoreThanMaxCapacity(){new ParkingLot(1000987);}

    @Test
    public void testParkingCar(){
        final Car testCar = new Car("KA-01-HH-1234","White");
        final ParkingLot parkingLot = new ParkingLot(100);
        final Slot slot = parkingLot.park(testCar,1);
        assertEquals(testCar,slot.getParkedCar());
    }

    @Test(expected = SlotAlreadyOccupiedException.class)
    public void testParkingOnAlreadyOccupiedSlot(){
        final Car testCar1 = new Car("KA-01-HH-1234","White");
        final Car testCar2 = new Car("KA-01-HH-1235","White");
        final ParkingLot parkingLot = new ParkingLot(100);
        final Slot slot1 = parkingLot.park(testCar1,1);
        final Slot slot2 = parkingLot.park(testCar2,1);
    }

    @Test(expected = InvalidSlotException.class)
    public void testParkingAtSlotHigherThanCapacity(){
        final Car testCar = new Car("KA-01-HH-1234", "White");
        final ParkingLot parkingLot = new ParkingLot(100);
        final Slot slot = parkingLot.park(testCar,1000);
    }
    @Test(expected = InvalidSlotException.class)
    public void testParkingAtSlotInvalidSlot(){
        final Car testCar = new Car("KA-01-HH-1234", "White");
        final ParkingLot parkingLot = new ParkingLot(100);
        final Slot slot = parkingLot.park(testCar,0);
    }

    @Test
    public void testToMakeSlotFree(){
        final Car testCar = new Car("KA-01-HH-1234","White");
        final ParkingLot parkingLot = new ParkingLot(100);
        final Slot slot = parkingLot.park(testCar,1);
        assertFalse(slot.isSlotFree());
        final Slot freedSlot = parkingLot.makeSlotFree(slot.getSlotNumber());
        assertTrue(freedSlot.isSlotFree());
    }

    @Test(expected = InvalidSlotException.class)
    public void testMakingSlotHigherThanCapacityFree() {
        final ParkingLot parkingLot = new ParkingLot(100);
        parkingLot.makeSlotFree(110);
    }

    @Test(expected = InvalidSlotException.class)
    public void testMakingInvalidSlotFree() {
        final ParkingLot parkingLot = new ParkingLot(100);
        parkingLot.makeSlotFree(-1);
    }
}