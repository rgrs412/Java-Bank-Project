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
    void command_is_not_empty_string() {
        command = VALID_CREATE_COMMAND;
        assertTrue(cmdValidator.isNotEmpty(command));
    }

    @Test
    void command_is_invalid_if_it_is_empty_string() {
        command = "";
        assertFalse(cmdValidator.isNotEmpty(command));
    }

    @Test
    void create_is_a_valid_command() {
        command = VALID_CREATE_COMMAND;
        assertTrue(cmdValidator.isValidCommand(command));
    }

    @Test
    void deposit_is_a_valid_command() {
        command = VALID_DEPOSIT_COMMAND;
        assertTrue(cmdValidator.isValidCommand(command));
    }

    @Test
    void create_command_has_4_arguments() {
        command = VALID_CREATE_COMMAND;
        assertTrue(cmdValidator.isValidNumberOfCommandArguments(command));
    }

    @Test
    void create_command_has_less_than_4_arguments() {
        command = "Create checking 12345678";
        assertFalse(cmdValidator.isValidNumberOfCommandArguments(command));
    }

    @Test
    void create_command_has_more_than_4_arguments() {
        command = VALID_CREATE_COMMAND + " abc";
        assertFalse(cmdValidator.isValidNumberOfCommandArguments(command));
    }
}
