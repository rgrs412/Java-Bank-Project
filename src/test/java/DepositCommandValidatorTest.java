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
    void deposit_command_with_less_than_3_arguments_is_invalid() {
        assertFalse(depositCommandValidator.validate("deposit 12345678"));
    }

    @Test
    void deposit_command_with_3_arguments_is_valid() {
        bank.addBankAccount(checkingAccount, ID);
        assertTrue(depositCommandValidator.validate(VALID_DEPOSIT_COMMAND));
    }

    @Test
    void deposit_command_with_more_than_3_arguments_is_invalid() {
        assertFalse(depositCommandValidator.validate(VALID_DEPOSIT_COMMAND + " abc"));
    }

    @Test
    void depositing_1000_into_checking_account_is_valid() {
        bank.addBankAccount(checkingAccount, ID);
        depositCommandValidator.setCommandArray(VALID_DEPOSIT_COMMAND);
        assertTrue(depositCommandValidator.isValidDeposit());
    }

    @Test
    void depositing_1001_into_checking_account_is_valid() {
        bank.addBankAccount(checkingAccount, ID);
        depositCommandValidator.setCommandArray("deposit 12345678 1001");
        assertFalse(depositCommandValidator.isValidDeposit());
    }

    @Test
    void depositing_2500_into_checking_account_is_valid() {
        bank.addBankAccount(savingsAccount, ID);
        depositCommandValidator.setCommandArray("deposit 12345678 2500");
        assertTrue(depositCommandValidator.isValidDeposit());
    }

    @Test
    void depositing_2501_into_checking_account_is_invalid() {
        bank.addBankAccount(savingsAccount, ID);
        depositCommandValidator.setCommandArray("deposit 12345678 2501");
        assertFalse(depositCommandValidator.isValidDeposit());
    }

    @Test
    void depositing_into_cd_account_is_invalid() {
        bank.addBankAccount(cdAccount, ID);
        depositCommandValidator.setCommandArray(VALID_DEPOSIT_COMMAND);
        assertFalse(depositCommandValidator.isValidDeposit());
    }
}