package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankTest {

    public static final String ID = "12345678";
    public static final String ID_TWO = "87654321";
    public static final double APR = 0.01;
    public static final double DEPOSIT = 5.25;
    public static final double WITHDRAWAL = 1.25;
    Bank bank;
    BankAccount bankAccount;
    BankAccount bankAccount2;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        bankAccount = new CheckingAccount(ID, APR);
        bankAccount2 = new CheckingAccount(ID_TWO, APR);
    }

    @Test
    void bank_has_no_bank_accounts_initially() {
        assertTrue(bank.getBankAccounts().isEmpty());
    }

    @Test
    void add_bank_account_to_bank() {
        bank.addBankAccount(bankAccount, ID);
        assertEquals(ID, bank.getBankAccounts().get(ID).getId());
    }

    @Test
    void delete_bank_account_from_bank() {
        bank.addBankAccount(bankAccount, ID);
        bank.deleteBankAccount(ID);
        assertTrue(bank.getBankAccounts().isEmpty());
    }

    @Test
    void deposit_money_to_bank_account_one_time() {
        bank.addBankAccount(bankAccount, ID);
        bank.deposit(ID, DEPOSIT);
        assertEquals(5.25, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void deposit_money_to_bank_account_more_than_one_time() {
        bank.addBankAccount(bankAccount, ID);
        bank.deposit(ID, DEPOSIT);
        bank.deposit(ID, DEPOSIT);
        assertEquals(10.50, bank.getBankAccounts().get(ID).getBalance());
    }


    @Test
    void withdraw_money_from_bank_account_one_time() {
        bank.addBankAccount(bankAccount, ID);
        bank.deposit(ID, DEPOSIT);
        bank.withdraw(ID, WITHDRAWAL);
        assertEquals(4.00, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void withdraw_money_from_bank_account_more_than_one_time() {
        bank.addBankAccount(bankAccount, ID);
        bank.deposit(ID, DEPOSIT);
        bank.withdraw(ID, WITHDRAWAL);
        bank.withdraw(ID, WITHDRAWAL);
        assertEquals(2.75, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void transfer_money_from_bank_account_to_bank_account() {
        bank.addBankAccount(bankAccount, ID);
        bank.addBankAccount(bankAccount2, ID_TWO);
        bank.deposit(ID, 1);
        bank.transfer(ID, ID_TWO, 1);

        assertEquals(0, bank.getBankAccounts().get(ID).getBalance());
        assertEquals(1, bank.getBankAccounts().get(ID_TWO).getBalance());
    }

    @Test
    void transfer_money_from_bank_account_to_bank_account_more_than_once() {
        bank.addBankAccount(bankAccount, ID);
        bank.addBankAccount(bankAccount2, ID_TWO);
        bank.deposit(ID, 2);
        bank.transfer(ID, ID_TWO, 1);
        bank.transfer(ID, ID_TWO, 1);

        assertEquals(0, bank.getBankAccounts().get(ID).getBalance());
        assertEquals(2, bank.getBankAccounts().get(ID_TWO).getBalance());
    }

    @Test
    void bank_account_balance_cannot_go_below_0() {
        bank.addBankAccount(bankAccount, ID);
        bank.withdraw(ID, WITHDRAWAL);
        assertEquals(0, bank.getBankAccounts().get(ID).getBalance());
    }
}
