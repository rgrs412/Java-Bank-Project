import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandValidatorTest {

    CommandValidator cmdValidator;
    String command;

    @BeforeEach
    void setUp() {
        cmdValidator = new CommandValidator();
    }

    @Test
    void create_command_has_4_arguments() {
        command = "Create checking 12345678 0.01";
        assertTrue(cmdValidator.isValidNumberOfCommandArguments(command));
    }

    @Test
    void create_command_has_less_than_4_arguments() {
        command = "Create checking 12345678";
        assertFalse(cmdValidator.isValidNumberOfCommandArguments(command));
    }

    @Test
    void create_command_has_more_than_4_arguments() {
        command = "Create checking 12345678 0.01 abc";
        assertFalse(cmdValidator.isValidNumberOfCommandArguments(command));
    }
}
