import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandValidatorTest {

    public static final String VALID_CREATE_COMMAND = "create checking 12345678 0.01";
    CommandValidator commandValidator;

    @BeforeEach
    void setUp() {
        commandValidator = new CommandValidator();
    }

    @Test
    void valid_command() {
        assertTrue(commandValidator.validate(VALID_CREATE_COMMAND));
    }

    @Test
    void empty_string_command_is_invalid() {
        assertFalse(commandValidator.validate(""));
    }
}
