import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankTest {

    public static final int ID = 12345678;
    public static final double APR = 0.01;
    public static final String BANK_ACCOUNT_TYPE = "checking";
    public static final double DEPOSIT = 5.25;
    public static final double WITHDRAWAL = 1.25;
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }

    @Test
    void bank_has_no_bank_accounts_initially() {
        assertTrue(bank.getBankAccounts().isEmpty());
    }

    @Test
    void add_bank_account_to_bank() {
        bank.addBankAccount(APR, ID, BANK_ACCOUNT_TYPE);
        assertEquals(ID, bank.getBankAccounts().get(ID).getId());
    }

    @Test
    void delete_bank_account_to_bank() {
        bank.addBankAccount(APR, ID, BANK_ACCOUNT_TYPE);
        bank.deleteBankAccount(ID);
        assertTrue(bank.getBankAccounts().isEmpty());
    }

    @Test
    void deposit_money_to_bank_account_one_time() {
        bank.addBankAccount(APR, ID, BANK_ACCOUNT_TYPE);
        bank.deposit(ID, DEPOSIT);
        assertEquals(5.25, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void deposit_money_to_bank_account_more_than_one_time() {
        bank.addBankAccount(APR, ID, BANK_ACCOUNT_TYPE);
        bank.deposit(ID, DEPOSIT);
        bank.deposit(ID, DEPOSIT);
        assertEquals(10.50, bank.getBankAccounts().get(ID).getBalance());
    }


    @Test
    void withdraw_money_from_bank_account_one_time() {
        bank.addBankAccount(APR, ID, BANK_ACCOUNT_TYPE);
        bank.deposit(ID, DEPOSIT);
        bank.withdraw(ID, WITHDRAWAL);
        assertEquals(4.00, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void withdraw_money_from_bank_account_more_than_one_time() {
        bank.addBankAccount(APR, ID, BANK_ACCOUNT_TYPE);
        bank.deposit(ID, DEPOSIT);
        bank.withdraw(ID, WITHDRAWAL);
        bank.withdraw(ID, WITHDRAWAL);
        assertEquals(2.75, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void bank_account_balance_cannot_go_below_0() {
        bank.addBankAccount(APR, ID, BANK_ACCOUNT_TYPE);
        bank.withdraw(ID, WITHDRAWAL);
        assertEquals(0, bank.getBankAccounts().get(ID).getBalance());
    }
}
