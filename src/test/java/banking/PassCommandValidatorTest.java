package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassCommandValidatorTest {
    public static final String VALID_PASS_COMMAND = "pass 1";
    PassCommandValidator passCommandValidator;
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        passCommandValidator = new PassCommandValidator(bank);
    }

    @Test
    void pass_command_with_less_than_1_arguments_is_invalid() {
        assertFalse(passCommandValidator.validate("withdraw"));
    }

    @Test
    void pass_command_with_2_arguments_is_valid() {
        assertTrue(passCommandValidator.validate(VALID_PASS_COMMAND));
    }

    @Test
    void withdraw_command_with_more_than_2_arguments_is_invalid() {
        assertFalse(passCommandValidator.validate(VALID_PASS_COMMAND + " abc"));
    }

}
