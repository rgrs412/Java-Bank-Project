package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandValidatorTest {

    public static final String VALID_CREATE_COMMAND = "create checking 12345678 0.01";
    public static final String VALID_DEPOSIT_COMMAND = "deposit 12345678 1000";
    public static final String VALID_WITHDRAW_COMMAND = "withdraw 12345678 300";
    public static final String VALID_PASS_COMMAND = "pass 1";
    public static final String ID = "12345678";
    public static final Double APR = 0.01;
    CommandValidator commandValidator;
    Bank bank;
    BankAccount checkingAccount;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
        checkingAccount = new CheckingAccount(ID, APR);
    }

    @Test
    void valid_create_command_is_valid() {
        assertTrue(commandValidator.validate(VALID_CREATE_COMMAND));
    }

    @Test
    void valid_deposit_command_is_valid() {
        bank.addBankAccount(checkingAccount, ID);
        assertTrue(commandValidator.validate(VALID_DEPOSIT_COMMAND));
    }

    @Test
    void valid_withdraw_command_is_valid() {
        bank.addBankAccount(checkingAccount, ID);
        assertTrue(commandValidator.validate(VALID_WITHDRAW_COMMAND));
    }

    @Test
    void valid_pass_command_is_valid() {
        assertTrue(commandValidator.validate(VALID_PASS_COMMAND));
    }

    @Test
    void mixed_letter_case_command_is_valid() {
        assertTrue(commandValidator.validate("CrEaTe CheCKing 12345678 0.01"));
    }

    @Test
    void the_command_that_needs_to_be_validated_is_initially_null() {
        assertEquals(null, commandValidator.getCommandArray());
    }
}
