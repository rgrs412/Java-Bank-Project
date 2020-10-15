import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BankAccountTest {

    public static final double APR = 0.01;
    public static final int ID = 12345678;
    public static final String BANK_ACCOUNT_TYPE = "checking";
    BankAccount checkingAccount;

    @BeforeEach
    void setUp() {
        checkingAccount = new BankAccount(APR, ID, BANK_ACCOUNT_TYPE);
    }

    @Test
    void bank_account_is_the_correct_type() {
        assertEquals("checking", checkingAccount.getAccountType());
    }

    @Test
    void same_bank_account_type_equality() {
        BankAccount checkingAccount2 = new BankAccount(APR, ID, BANK_ACCOUNT_TYPE);
        assertFalse(checkingAccount.equals(checkingAccount2));
    }

    @Test
    void different_bank_account_type_equality() {
        BankAccount savingsAccount = new BankAccount(APR, ID, "savings");
        BankAccount cdAccount = new BankAccount(APR, ID, "cd");
        assertFalse(checkingAccount.equals(savingsAccount));
        assertFalse(checkingAccount.equals(cdAccount));
    }

    @Test
    void bank_account_has_the_right_apr_initially() {
        assertEquals(0.01, checkingAccount.getApr());
    }

    @Test
    void bank_account_has_the_right_id() {
        assertEquals(12345678, checkingAccount.getId());
    }

    @Test
    void bank_account_balance_is_0_initially() {
        assertEquals(0, checkingAccount.getBalance());
    }
}
