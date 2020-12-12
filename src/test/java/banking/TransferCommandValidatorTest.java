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
        assertTrue(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "1"));
    }

    @Test
    void withdraw_command_with_more_than_3_arguments_is_invalid() {

        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "1 abc"));
    }
}
