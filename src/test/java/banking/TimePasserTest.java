package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimePasserTest {

    public static final String ID = "12345678";
    public static final Double APR = 0.01;

    Bank bank;
    TimePasser timerPasser;
    BankAccount checkingAccount;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        timerPasser = new TimePasser(bank);
        checkingAccount = new CheckingAccount(ID, APR);
    }

    @Test
    void account_is_closed_if_balance_is_0() {
        bank.addBankAccount(checkingAccount, ID);
        timerPasser.passMonths(1);
        assertEquals(null, bank.getBankAccounts().get(ID));
    }

    @Test
    void account_is_not_closed_if_balance_is_more_than_0() {
        bank.addBankAccount(checkingAccount, ID);
        bank.deposit(ID, 1);
        timerPasser.passMonths(1);
        assertEquals(ID, bank.getBankAccounts().get(ID).getId());
    }

    @Test
    void account_is_deducted_25_if_balance_is_less_than_100() {
        bank.addBankAccount(checkingAccount, ID);
        bank.deposit(ID, 50);
        timerPasser.passMonths(1);
        assertEquals(25, bank.getBankAccounts().get(ID).getBalance());
    }
}
