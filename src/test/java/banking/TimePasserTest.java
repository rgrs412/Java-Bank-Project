package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimePasserTest {

    public static final String ID = "12345678";
    public static final Double APR = 0.90;

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
    void account_accrues_interest() {
        bank.addBankAccount(checkingAccount, ID);
        bank.deposit(ID, 100);
        timerPasser.passMonths(1);
        assertEquals(100.075, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void account_balance_is_deducted_25_if_balance_is_less_than_100() {
        bank.addBankAccount(checkingAccount, ID);
        bank.deposit(ID, 99);
        timerPasser.passMonths(1);
        assertEquals(74.0555, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void account_cannot_be_deducted_below_0() {
        bank.addBankAccount(checkingAccount, ID);
        bank.deposit(ID, 20);
        timerPasser.passMonths(1);
        assertEquals(0, bank.getBankAccounts().get(ID).getBalance());
    }
}
