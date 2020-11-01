import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositCommandTest {

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
    void depositing_1000_into_checking_account_is_valid() {
        bank.addBankAccount(checkingAccount, ID);
        depositCommandValidator.setCommandArray(VALID_DEPOSIT_COMMAND);
        assertTrue(depositCommandValidator.isValidDeposit());
    }


}
