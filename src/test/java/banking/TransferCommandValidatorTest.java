package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferCommandValidatorTest {

    public static final String ID = "12345678";
    public static final String ID_TWO = "87654321";
    public static final String TRANSFER_COMMAND_TEMPLATE = String.format("transfer %s %s ", ID, ID_TWO);
    public static final Double APR = 0.01;
    TransferCommandValidator transferCommandValidator;
    Bank bank;
    BankAccount checkingAccount;
    BankAccount savingsAccount;
    BankAccount cdAccount;
    TimePasser timePasser;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        transferCommandValidator = new TransferCommandValidator(bank);
        checkingAccount = new CheckingAccount(ID, APR);
        savingsAccount = new SavingsAccount(ID, APR);
        cdAccount = new CdAccount(ID, APR, 1000);
        timePasser = new TimePasser(bank);
    }

    @Test
    void transfer_command_with_less_than_3_arguments_is_invalid() {
        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE));
    }

    @Test
    void transfer_command_with_3_arguments_is_valid() {
        bank.addBankAccount(checkingAccount, ID);
        CheckingAccount checkingAccount2 = new CheckingAccount(ID_TWO, APR);
        bank.addBankAccount(checkingAccount2, ID_TWO);

        assertTrue(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "1"));
    }

    @Test
    void transfer_command_with_more_than_3_arguments_is_invalid() {
        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "1 abc"));
    }

    @Test
    void transfer_from_bank_account_that_does_not_exist_is_invalid() {
        CheckingAccount checkingAccount2 = new CheckingAccount(ID_TWO, APR);
        bank.addBankAccount(checkingAccount2, ID_TWO);

        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "1"));
    }

    @Test
    void transfer_to_bank_account_that_does_not_exist_is_invalid() {
        bank.addBankAccount(checkingAccount, ID);

        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "1"));
    }
}
