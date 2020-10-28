import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandValidatorTest {

    public static final String VALID_CREATE_COMMAND = "Create checking 12345678 0.01";
    public static final String VALID_DEPOSIT_COMMAND = "Deposit 12345678 500";
    CommandValidator cmdValidator;
    String command;

    @BeforeEach
    void setUp() {
        cmdValidator = new CommandValidator();
    }

    @Test
    void empty_string_command_is_invalid() {
        command = "";
        assertFalse(cmdValidator.isNotEmpty(command));
    }

    @Test
    void create_is_a_valid_command() {
        assertTrue(cmdValidator.isValidCommand(VALID_CREATE_COMMAND));
    }

    @Test
    void deposit_is_a_valid_command() {
        assertTrue(cmdValidator.isValidCommand(VALID_DEPOSIT_COMMAND));
    }
}
