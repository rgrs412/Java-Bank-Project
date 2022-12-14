package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CreateCommandValidatorTest {

    public static final String VALID_CREATE_CHECKING_COMMAND = "create checking 12345678 0.01";
    public static final String ID = "12345678";
    public static final Double APR = 0.01;
    CreateCommandValidator createCommandValidator;
    Bank bank;
    BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        createCommandValidator = new CreateCommandValidator(bank);
        bankAccount = new CheckingAccount(ID, APR);
    }

    @Test
    void apr_that_is_not_in_percentage_form_is_invalid() {
        assertFalse(createCommandValidator.validate("create checking 12345678 1.000"));
    }

    @Test
    void bank_account_id_that_is_not_an_8_digit_natural_number_is_invalid() {
        assertFalse(createCommandValidator.validate("create checking 1234567 0.01"));
    }

    @Test
    void duplicate_account_id_is_invalid() {
        bank.addBankAccount(bankAccount, ID);
        assertFalse(createCommandValidator.validate(VALID_CREATE_CHECKING_COMMAND));
    }
}
