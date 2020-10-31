import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateCommandValidatorTest {

    public static final String VALID_CREATE_CHECKING_COMMAND = "create checking 12345678 0.01";
    public static final String VALID_CREATE_SAVINGS_COMMAND = "create savings 12345678 0.01";
    public static final String VALID_CREATE_CD_COMMAND = "create cd 12345678 0.01 1000";
    public static final String ID = "12345678";
    CreateCommandValidator createCommandValidator;
    Bank bank;
    BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        createCommandValidator = new CreateCommandValidator(bank);
        bankAccount = new CheckingAccount(ID, 0.01);
    }

    @Test
    void bank_account_id_that_is_not_an_8_digit_natural_number_is_invalid() {
        assertFalse(createCommandValidator.validate("create checking 1234567 0.01"));
    }

    @Test
    void misspelled_create_command_is_invalid() {
        assertFalse(createCommandValidator.validate("Createsssssss cccchecking 12345678 0.01"));
    }

    @Test
    void duplicate_account_id_is_invalid() {
        bank.addBankAccount(bankAccount, ID);
        assertFalse(createCommandValidator.validate(VALID_CREATE_CHECKING_COMMAND));
    }

    @Test
    void create_checking_command_with_less_than_4_arguments_is_invalid() {
        assertFalse(createCommandValidator.validate("create checking 12345678"));
    }

    @Test
    void create_checking_command_with_4_arguments_is_valid() {
        assertTrue(createCommandValidator.validate(VALID_CREATE_CHECKING_COMMAND));
    }

    @Test
    void create_checking_command_with_more_than_4_arguments_is_invalid() {
        assertFalse(createCommandValidator.validate(VALID_CREATE_CHECKING_COMMAND + " abc"));
    }

    @Test
    void create_savings_command_with_less_than_4_arguments_is_invalid() {
        assertFalse(createCommandValidator.validate("create savings 12345678"));
    }

    @Test
    void create_savings_command_with_4_arguments_is_valid() {
        assertTrue(createCommandValidator.validate(VALID_CREATE_SAVINGS_COMMAND));
    }

    @Test
    void create_savings_command_with_more_than_4_arguments_is_invalid() {
        assertFalse(createCommandValidator.validate(VALID_CREATE_SAVINGS_COMMAND + " abc"));
    }

    @Test
    void create_cd_command_with_less_than_5_arguments_is_invalid() {
        assertFalse(createCommandValidator.validate("create cd 12345678 0.01"));
    }

    @Test
    void create_cd_command_with_5_arguments_is_valid() {
        assertTrue(createCommandValidator.validate(VALID_CREATE_CD_COMMAND));
    }

    @Test
    void create_cd_command_with_more_than_5_arguments_is_invalid() {
        assertFalse(createCommandValidator.validate(VALID_CREATE_CD_COMMAND + " abc"));
    }
}
