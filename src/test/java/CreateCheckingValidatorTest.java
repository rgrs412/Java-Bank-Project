import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateCheckingValidatorTest {

    public static final String VALID_CREATE_CHECKING_COMMAND = "create checking 12345678 0.01";
    public static final String ID = "12345678";
    public static final Double APR = 0.01;
    CreateCheckingValidator createCheckingValidator;
    Bank bank;
    BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        createCheckingValidator = new CreateCheckingValidator(bank);
        bankAccount = new CheckingAccount(ID, APR);
    }

    @Test
    void create_checking_command_with_less_than_4_arguments_is_invalid() {
        assertFalse(createCheckingValidator.validate("create checking 12345678"));
    }

    @Test
    void create_checking_command_with_4_arguments_is_valid() {
        assertTrue(createCheckingValidator.validate(VALID_CREATE_CHECKING_COMMAND));
    }

    @Test
    void create_checking_command_with_more_than_4_arguments_is_invalid() {
        assertFalse(createCheckingValidator.validate(VALID_CREATE_CHECKING_COMMAND + " abc"));
    }
}
