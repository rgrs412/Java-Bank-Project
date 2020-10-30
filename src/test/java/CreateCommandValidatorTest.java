import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateCommandValidatorTest {

    public static final String VALID_CREATE_CHECKING_COMMAND = "create checking 12345678 0.01";
    public static final String VALID_CREATE_SAVINGS_COMMAND = "create savings 12345678 0.01";
    public static final String VALID_CREATE_CD_COMMAND = "create cd 12345678 0.01 1000";
    CreateCommandValidator createCommandValidator;

    @BeforeEach
    void setUp() {
        createCommandValidator = new CreateCommandValidator();
    }

    @Test
    void create_checking_has_less_than_4_arguments_is_invalid() {
        assertFalse(createCommandValidator.validate("create checking 12345678"));
    }

    @Test
    void create_checking_has_4_arguments_is_valid() {
        assertTrue(createCommandValidator.validate(VALID_CREATE_CHECKING_COMMAND));
    }

    @Test
    void create_checking_has_more_than_4_arguments_is_invalid() {
        assertFalse(createCommandValidator.validate(VALID_CREATE_CHECKING_COMMAND + " abc"));
    }

    @Test
    void create_savings_has_less_than_4_arguments_is_invalid() {
        assertFalse(createCommandValidator.validate("create savings 12345678"));
    }

    @Test
    void create_savings_has_4_arguments_is_valid() {
        assertTrue(createCommandValidator.validate(VALID_CREATE_SAVINGS_COMMAND));
    }

    @Test
    void create_savings_has_more_than_4_arguments_is_invalid() {
        assertFalse(createCommandValidator.validate(VALID_CREATE_SAVINGS_COMMAND + " abc"));
    }

    @Test
    void create_cd_has_less_than_5_arguments_is_invalid() {
        assertFalse(createCommandValidator.validate("create cd 12345678 0.01"));
    }

    @Test
    void create_cd_has_5_arguments_is_valid() {
        assertTrue(createCommandValidator.validate(VALID_CREATE_CD_COMMAND));
    }

    @Test
    void create_cd_has_more_than_5_arguments_is_invalid() {
        assertFalse(createCommandValidator.validate(VALID_CREATE_CD_COMMAND + " abc"));
    }
}
