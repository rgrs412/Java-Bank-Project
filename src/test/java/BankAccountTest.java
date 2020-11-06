import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BankAccountTest {

    public static final double APR = 0.01;
    public static final String ID = "12345678";
    BankAccount checkingAccount;

    @BeforeEach
    void setUp() {
        checkingAccount = new CheckingAccount(ID, APR);
    }

    @Test
    void bank_account_is_the_correct_type() {
        assertEquals("checking", checkingAccount.getAccountType());
    }

    @Test
    void same_bank_account_type_equality() {
        BankAccount checkingAccount2 = new CheckingAccount(ID, APR);
        assertFalse(checkingAccount.equals(checkingAccount2));
    }

    @Test
    void different_bank_account_type_equality() {
        BankAccount savingsAccount = new SavingsAccount(ID, APR);
        BankAccount cdAccount = new CdAccount(ID, APR, 100);
        assertFalse(checkingAccount.equals(savingsAccount));
        assertFalse(checkingAccount.equals(cdAccount));
    }

    @Test
    void bank_account_has_the_right_apr_initially() {
        assertEquals(0.01, checkingAccount.getApr());
    }

    @Test
    void bank_account_has_the_right_id() {
        assertEquals("12345678", checkingAccount.getId());
    }

    @Test
    void bank_account_balance_is_0_initially() {
        assertEquals(0, checkingAccount.getBalance());
    }
}
