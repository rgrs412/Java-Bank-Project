package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AprCalculatorTest {

    public static final String ID = "12345678";
    public static final double APR = 0.6;
    BankAccount checkingAccount;
    AprCalculator aprCalculator;
    Bank bank;

    @BeforeEach
    void setUp() {
        checkingAccount = new CheckingAccount(ID, APR);
        aprCalculator = new AprCalculator();
        bank = new Bank();
    }

    @Test
    void bank_account_interest_is_calculated_correctly() {
        bank.addBankAccount(checkingAccount, ID);
        bank.deposit(ID, 5000);
        assertEquals(2.50, aprCalculator.calculateInterest(checkingAccount));
    }
}
