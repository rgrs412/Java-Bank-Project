import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandValidatorTest {

    @Test
    void create_command_has_4_arguments() {
        String command = "Create checking 12345678 0.01";
        CommandValidator cmdValidator = new CommandValidator();
        assertTrue(cmdValidator.isValidNumberOfCommandArguments(command));
    }
}
