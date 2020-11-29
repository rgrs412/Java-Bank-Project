package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimePasserTest {

    public static final String ID = "12345678";
    public static final double APR = 0.90;
    public static final double CD_ACCOUNT_INITIAL_DEPOSIT = 1000;

    Bank bank;
    TimePasser timerPasser;
    BankAccount checkingAccount;
    BankAccount savingsAccount;
    BankAccount cdAccount;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        timerPasser = new TimePasser(bank);
        checkingAccount = new CheckingAccount(ID, APR);
        savingsAccount = new SavingsAccount(ID, APR);
        cdAccount = new CdAccount(ID, APR, CD_ACCOUNT_INITIAL_DEPOSIT);
    }

    @Test
    void checking_account_is_closed_if_balance_is_0() {
        bank.addBankAccount(checkingAccount, ID);
        timerPasser.passMonths(1);
        assertEquals(null, bank.getBankAccounts().get(ID));
    }

    @Test
    void savings_account_is_closed_if_balance_is_0() {
        bank.addBankAccount(savingsAccount, ID);
        timerPasser.passMonths(1);
        assertEquals(null, bank.getBankAccounts().get(ID));
    }

    @Test
    void cd_account_is_closed_if_balance_is_0() {
        bank.addBankAccount(cdAccount, ID);
        cdAccount.withdraw(1000);
        timerPasser.passMonths(1);
        assertEquals(null, bank.getBankAccounts().get(ID));
    }

    @Test
    void checking_account_is_not_closed_if_balance_is_more_than_0() {
        bank.addBankAccount(checkingAccount, ID);
        bank.deposit(ID, 1);
        timerPasser.passMonths(1);
        assertEquals(ID, bank.getBankAccounts().get(ID).getId());
    }

    @Test
    void savings_account_is_not_closed_if_balance_is_more_than_0() {
        bank.addBankAccount(savingsAccount, ID);
        bank.deposit(ID, 1);
        timerPasser.passMonths(1);
        assertEquals(ID, bank.getBankAccounts().get(ID).getId());
    }

    @Test
    void cd_account_is_not_closed_if_balance_is_more_than_0() {
        bank.addBankAccount(cdAccount, ID);
        timerPasser.passMonths(1);
        assertEquals(ID, bank.getBankAccounts().get(ID).getId());
    }

    @Test
    void checking_account_is_closed_after_two_months_if_balance_is_25() {
        bank.addBankAccount(checkingAccount, ID);
        bank.deposit(ID, 25);
        timerPasser.passMonths(2);
        assertEquals(null, bank.getBankAccounts().get(ID));
    }

    @Test
    void savings_account_is_closed_after_two_months_if_balance_is_25() {
        bank.addBankAccount(savingsAccount, ID);
        bank.deposit(ID, 25);
        timerPasser.passMonths(2);
        assertEquals(null, bank.getBankAccounts().get(ID));
    }

    @Test
    void checking_account_accrues_interest() {
        bank.addBankAccount(checkingAccount, ID);
        bank.deposit(ID, 100);
        timerPasser.passMonths(1);
        assertEquals(100.075, checkingAccount.getBalance());
    }

    @Test
    void savings_account_accrues_interest() {
        bank.addBankAccount(savingsAccount, ID);
        bank.deposit(ID, 100);
        timerPasser.passMonths(1);
        assertEquals(100.075, savingsAccount.getBalance());
    }

    @Test
    void cd_account_accrues_interest_4_times_more_frequently_than_checking_account() {
        bank.addBankAccount(cdAccount, ID);
        timerPasser.passMonths(1);

        Bank bank2 = new Bank();
        bank2.addBankAccount(checkingAccount, ID);
        bank2.deposit(ID, CD_ACCOUNT_INITIAL_DEPOSIT);
        TimePasser timePasser2 = new TimePasser(bank2);
        timePasser2.passMonths(4);

        assertEquals(checkingAccount.getBalance(), cdAccount.getBalance());
    }

    @Test
    void checking_account_balance_is_deducted_25_if_balance_is_less_than_100() {
        bank.addBankAccount(checkingAccount, ID);
        bank.deposit(ID, 99);
        timerPasser.passMonths(1);
        assertEquals(74.0555, checkingAccount.getBalance());
    }

    @Test
    void savings_account_balance_is_deducted_25_if_balance_is_less_than_100() {
        bank.addBankAccount(savingsAccount, ID);
        bank.deposit(ID, 99);
        timerPasser.passMonths(1);
        assertEquals(74.0555, savingsAccount.getBalance());
    }

    @Test
    void checking_account_cannot_be_deducted_below_0() {
        bank.addBankAccount(checkingAccount, ID);
        bank.deposit(ID, 20);
        timerPasser.passMonths(1);
        assertEquals(0, checkingAccount.getBalance());
    }

    @Test
    void savings_account_cannot_be_deducted_below_0() {
        bank.addBankAccount(savingsAccount, ID);
        bank.deposit(ID, 20);
        timerPasser.passMonths(1);
        assertEquals(0, savingsAccount.getBalance());
    }
}
