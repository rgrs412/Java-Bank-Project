package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AprCalculatorTest {

    public static final String ID = "12345678";
    public static final double APR = 0.6;
    public static final double DEPOSIT = 5000;
    BankAccount checkingAccount;
    BankAccount savingsAccount;
    BankAccount cdAccount;
    AprCalculator aprCalculator;
    Bank bank;

    @BeforeEach
    void setUp() {
        checkingAccount = new CheckingAccount(ID, APR);
        savingsAccount = new SavingsAccount(ID, APR);
        cdAccount = new CdAccount(ID, APR, DEPOSIT);
        aprCalculator = new AprCalculator();
        bank = new Bank();
    }

    @Test
    void checking_account_interest_is_calculated_correctly() {
        checkingAccount.deposit(DEPOSIT);
        assertEquals(2.50, aprCalculator.calculateInterest(checkingAccount));
    }

    @Test
    void savings_account_interest_is_calculated_correctly() {
        savingsAccount.deposit(DEPOSIT);
        assertEquals(2.50, aprCalculator.calculateInterest(savingsAccount));
    }

    @Test
    void cd_account_interest_is_calculated_4_times_more_frequently_than_checking_account() {
        checkingAccount.deposit(DEPOSIT);
        checkingAccount.passMonths(4, bank);
        double accruedInterest = checkingAccount.getBalance() - DEPOSIT;
        assertEquals(accruedInterest, aprCalculator.calculateInterest(cdAccount));
    }
}
