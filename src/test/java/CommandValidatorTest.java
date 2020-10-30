import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandValidatorTest {

    public static final String VALID_CREATE_COMMAND = "create checking 12345678 0.01";
    CommandValidator commandValidator;

    @BeforeEach
    void setUp() {
        commandValidator = new CreateCommandValidator();
    }

    @Test
    void valid_command() {
        assertTrue(commandValidator.validate(VALID_CREATE_COMMAND));
    }

    @Test
    void command_is_initially_null() {
        assertEquals(null, commandValidator.getCommand());
    }
}
