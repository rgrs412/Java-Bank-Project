import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandValidatorTest {

    public static final String VALID_COMMAND_1 = "Create checking 12345678 0.01";
    CommandValidator cmdValidator;
    String command;

    @BeforeEach
    void setUp() {
        cmdValidator = new CommandValidator();
    }

    @Test
    void command_is_valid_if_not_empty() {
        command = VALID_COMMAND_1;
        assertTrue(cmdValidator.isNotEmpty(command));
    }

    @Test
    void command_is_invalid_if_empty() {
        command = "";
        assertFalse(cmdValidator.isNotEmpty(command));
    }

    @Test
    void create_command_has_4_arguments() {
        command = VALID_COMMAND_1;
        assertTrue(cmdValidator.isValidNumberOfCommandArguments(command));
    }

    @Test
    void create_command_has_less_than_4_arguments() {
        command = "Create checking 12345678";
        assertFalse(cmdValidator.isValidNumberOfCommandArguments(command));
    }

    @Test
    void create_command_has_more_than_4_arguments() {
        command = VALID_COMMAND_1 + " abc";
        assertFalse(cmdValidator.isValidNumberOfCommandArguments(command));
    }
}
