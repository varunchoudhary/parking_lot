package com.gojek.solution.command;

import com.gojek.solution.exceptions.InvalidCommandException;
import com.gojek.solution.model.Command;
import com.gojek.solution.model.ParkingLot;
import com.gojek.solution.service.ParkingLotService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CommandFactoryTest {
    private CommandFactory factory;

    @Before
    public void setUp() throws Exception {
        final ParkingLotService parkingLotService = new ParkingLotService();
        factory = new CommandFactory(parkingLotService);
    }
    @Test
    public void testFetchingExecutorForValidCommand() {
        CommandExecutor commandExecutor = factory.getCommandExecuter(new Command("create_parking_lot"));
        assertNotNull(commandExecutor);
        assertTrue(commandExecutor instanceof CreateParkingLotCommandExecutor);

        commandExecutor = factory.getCommandExecuter(new Command("park"));
        assertNotNull(commandExecutor);
        assertTrue(commandExecutor instanceof ParkCommandExecutor);

        commandExecutor = factory.getCommandExecuter(new Command("leave"));
        assertNotNull(commandExecutor);
        assertTrue(commandExecutor instanceof LeaveCommandExecutor);

        commandExecutor = factory.getCommandExecuter(new Command("status"));
        assertNotNull(commandExecutor);
        assertTrue(commandExecutor instanceof StatusCommandExecutor);

        commandExecutor = factory.getCommandExecuter(new Command("registration_numbers_for_cars_with_colour"));
        assertNotNull(commandExecutor);
        assertTrue(commandExecutor instanceof ColorToRegNumberCommandExecutor);

        commandExecutor = factory.getCommandExecuter(new Command("slot_numbers_for_cars_with_colour"));
        assertNotNull(commandExecutor);
        assertTrue(commandExecutor instanceof ColorToSlotNumberCommandExecutor);

        commandExecutor = factory.getCommandExecuter(new Command("slot_number_for_registration_number"));
        assertNotNull(commandExecutor);
        assertTrue(commandExecutor instanceof SlotForRegNumberCommandExecutor);

        commandExecutor = factory.getCommandExecuter(new Command("exit"));
        assertNotNull(commandExecutor);
        assertTrue(commandExecutor instanceof ExitCommandExecutor);
    }

    @Test(expected = InvalidCommandException.class)
    public void testFetchingExecutorForInvalidCommand() {
        factory.getCommandExecuter(new Command("some-random-command random-param1 random-param2"));
    }
}