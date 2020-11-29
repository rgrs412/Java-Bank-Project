package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WithdrawCommandValidatorTest {
    public static final String VALID_WITHDRAW_COMMAND = "withdraw 12345678 300";
    public static final String ID = "12345678";
    public static final Double APR = 0.01;
    WithdrawCommandValidator withdrawCommandValidator;
    Bank bank;
    BankAccount checkingAccount;
    BankAccount savingsAccount;
    BankAccount cdAccount;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        withdrawCommandValidator = new WithdrawCommandValidator(bank);
        checkingAccount = new CheckingAccount(ID, APR);
        savingsAccount = new SavingsAccount(ID, APR);
        cdAccount = new CdAccount(ID, APR, 1000);
    }

    @Test
    void withdraw_command_with_less_than_3_arguments_is_invalid() {
        assertFalse(withdrawCommandValidator.validate("withdraw 12345678"));
    }

    @Test
    void withdraw_command_with_3_arguments_is_valid() {
        bank.addBankAccount(checkingAccount, ID);
        assertTrue(withdrawCommandValidator.validate(VALID_WITHDRAW_COMMAND));
    }

    @Test
    void withdraw_command_with_more_than_3_arguments_is_invalid() {
        assertFalse(withdrawCommandValidator.validate(VALID_WITHDRAW_COMMAND + " abc"));
    }

    @Test
    void withdraw_from_account_that_does_not_exist_is_invalid() {
        assertFalse(withdrawCommandValidator.validate(VALID_WITHDRAW_COMMAND));
    }

    @Test
    void withdraw_400_from_checking_account_is_valid() {
        bank.addBankAccount(checkingAccount, ID);
        assertTrue(withdrawCommandValidator.validate("withdraw 12345678 400"));
    }

    @Test
    void withdraw_less_than_400_from_checking_account_is_valid() {
        bank.addBankAccount(checkingAccount, ID);
        assertTrue(withdrawCommandValidator.validate("withdraw 12345678 399"));
    }

    @Test
    void withdraw_more_than_400_from_checking_account_is_invalid() {
        bank.addBankAccount(checkingAccount, ID);
        assertFalse(withdrawCommandValidator.validate("withdraw 12345678 401"));
    }

    @Test
    void withdraw_1000_from_savings_account_is_valid() {
        bank.addBankAccount(savingsAccount, ID);
        assertTrue(withdrawCommandValidator.validate("withdraw 12345678 1000"));
    }

    @Test
    void withdraw_less_than_1000_from_savings_account_is_valid() {
        bank.addBankAccount(savingsAccount, ID);
        assertTrue(withdrawCommandValidator.validate("withdraw 12345678 999"));
    }

    @Test
    void withdraw_more_than_1000_from_savings_account_is_invalid() {
        bank.addBankAccount(savingsAccount, ID);
        assertFalse(withdrawCommandValidator.validate("withdraw 12345678 1001"));
    }

    @Test
    void withdraw_once_a_month_from_savings_account_is_valid() {
        bank.addBankAccount(savingsAccount, ID);
        bank.getBankAccounts().get(ID).withdraw(1);
        assertTrue(withdrawCommandValidator.validate("withdraw 12345678 1000"));
    }

    @Test
    void withdraw_more_than_once_a_month_from_savings_account_is_invalid() {
        bank.addBankAccount(savingsAccount, ID);
        bank.getBankAccounts().get(ID).withdraw(1);
        bank.getBankAccounts().get(ID).withdraw(1);
        assertFalse(withdrawCommandValidator.validate("withdraw 12345678 1000"));
    }
}
