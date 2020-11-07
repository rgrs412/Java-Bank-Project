import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandStorageTest {

    public static final String INVALID_COMMAND = "Createsssssss checking 12345678 0.01";
    CommandStorage commandStorage;

    @BeforeEach
    void setUp() {
        commandStorage = new CommandStorage();
    }

    @Test
    void there_are_no_invalid_commands_initially() {
        assertTrue(commandStorage.getInvalidCommands().isEmpty());
    }
}
