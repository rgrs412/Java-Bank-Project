package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositCommandValidatorTest {

    public static final String VALID_DEPOSIT_COMMAND = "deposit 12345678 1000";
    public static final String ID = "12345678";
    public static final Double APR = 0.01;
    DepositCommandValidator depositCommandValidator;
    Bank bank;
    BankAccount checkingAccount;
    BankAccount savingsAccount;
    BankAccount cdAccount;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        depositCommandValidator = new DepositCommandValidator(bank);
        checkingAccount = new CheckingAccount(ID, APR);
        savingsAccount = new SavingsAccount(ID, APR);
        cdAccount = new CdAccount(ID, APR, 1000);
    }

    @Test
    void deposit_into_account_that_does_not_exist_is_invalid() {
        assertFalse(depositCommandValidator.validate(VALID_DEPOSIT_COMMAND));
    }

    @Test
    void deposit_command_with_less_than_2_arguments_is_invalid() {
        assertFalse(depositCommandValidator.validate("deposit 12345678"));
    }

    @Test
    void deposit_command_with_2_arguments_is_valid() {
        bank.addBankAccount(checkingAccount, ID);
        assertTrue(depositCommandValidator.validate(VALID_DEPOSIT_COMMAND));
    }

    @Test
    void deposit_command_with_more_than_2_arguments_is_invalid() {
        assertFalse(depositCommandValidator.validate(VALID_DEPOSIT_COMMAND + " abc"));
    }

    @Test
    void depositing_1000_into_checking_account_is_valid() {
        bank.addBankAccount(checkingAccount, ID);
        assertTrue(depositCommandValidator.validate(VALID_DEPOSIT_COMMAND));
    }

    @Test
    void depositing_1001_into_checking_account_is_invalid() {
        bank.addBankAccount(checkingAccount, ID);
        assertFalse(depositCommandValidator.validate("deposit 12345678 1001"));
    }

    @Test
    void depositing_2500_into_savings_account_is_valid() {
        bank.addBankAccount(savingsAccount, ID);
        assertTrue(depositCommandValidator.validate("deposit 12345678 2500"));
    }

    @Test
    void depositing_2501_into_savings_account_is_invalid() {
        bank.addBankAccount(savingsAccount, ID);
        assertFalse(depositCommandValidator.validate("deposit 12345678 2501"));
    }

    @Test
    void depositing_into_cd_account_is_invalid() {
        bank.addBankAccount(cdAccount, ID);
        assertFalse(depositCommandValidator.validate(VALID_DEPOSIT_COMMAND));
    }
}
