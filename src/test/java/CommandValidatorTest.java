import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandValidatorTest {

    @Test
    void create_command_has_4_arguments() {
        String command = "Create checking 12345678 0.01";
        CommandValidator cmdValidator = new CommandValidator();
        assertTrue(cmdValidator.isValidNumberOfCommandArguments(command));
    }

    @Test
    void create_command_has_less_than_4_arguments() {
        String command = "Create checking 12345678";
        CommandValidator cmdValidator = new CommandValidator();
        assertFalse(cmdValidator.isValidNumberOfCommandArguments(command));
    }
}
