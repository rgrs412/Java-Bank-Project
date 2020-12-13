package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandStorageTest {

    public static final String INVALID_COMMAND = "Createsssssss checking 12345678 0.01";
    public static final String INVALID_COMMAND_2 = "Creat checking 12345678 0.01";
    CommandStorage commandStorage;

    @BeforeEach
    void setUp() {
        commandStorage = new CommandStorage();
    }

    @Test
    void there_are_no_invalid_commands_initially() {
        assertTrue(commandStorage.getInvalidCommands().isEmpty());
    }

    @Test
    void add_one_command_to_invalid_commands() {
        commandStorage.addInvalidCommand(INVALID_COMMAND);
        assertEquals(1, commandStorage.getInvalidCommands().size());
        assertTrue(commandStorage.getInvalidCommands().contains(INVALID_COMMAND));
    }

    @Test
    void add_two_command_to_invalid_commands() {
        commandStorage.addInvalidCommand(INVALID_COMMAND);
        commandStorage.addInvalidCommand(INVALID_COMMAND_2);
        assertTrue(commandStorage.getInvalidCommands().contains(INVALID_COMMAND));
        assertTrue(commandStorage.getInvalidCommands().contains(INVALID_COMMAND_2));
    }
}
