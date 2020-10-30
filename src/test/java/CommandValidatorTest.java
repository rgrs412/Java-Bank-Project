import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandValidatorTest {

    public static final String VALID_CREATE_COMMAND = "create checking 12345678 0.01";
    CommandValidator commandValidator;
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandValidator = new CreateCommandValidator(bank);
    }

    @Test
    void valid_command() {
        assertTrue(commandValidator.validate(VALID_CREATE_COMMAND));
    }

    @Test
    void mixed_letter_case_command_is_valid() {
        assertTrue(commandValidator.validate("CrEaTe CheCKing 12345678 0.01"));
    }

    @Test
    void the_command_that_needs_to_be_validated_is_initially_null() {
        assertEquals(null, commandValidator.getCommand());
    }
}
