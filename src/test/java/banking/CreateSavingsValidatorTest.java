package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateSavingsValidatorTest {

    public static final String VALID_CREATE_SAVINGS_COMMAND = "create savings 12345678 0.01";
    public static final String ID = "12345678";
    public static final Double APR = 0.01;
    CreateSavingsValidator createSavingsValidator;
    Bank bank;
    BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        createSavingsValidator = new CreateSavingsValidator(bank);
        bankAccount = new SavingsAccount(ID, APR);
    }

    @Test
    void create_savings_command_with_less_than_3_arguments_is_invalid() {
        assertFalse(createSavingsValidator.validate("create savings 12345678"));
    }

    @Test
    void create_savings_command_with_3_arguments_is_valid() {
        assertTrue(createSavingsValidator.validate(VALID_CREATE_SAVINGS_COMMAND));
    }

    @Test
    void create_savings_command_with_more_than_3_arguments_is_invalid() {
        assertFalse(createSavingsValidator.validate(VALID_CREATE_SAVINGS_COMMAND + " abc"));
    }
}
