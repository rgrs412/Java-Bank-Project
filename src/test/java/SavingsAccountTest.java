import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingsAccountTest {

    public static final double APR = 0.01;
    public static final int ID = 12345678;
    SavingsAccount savingsAccount;

    @BeforeEach
    void setUp() {
        savingsAccount = new SavingsAccount(ID, APR);
    }

    @Test
    void savings_account_is_the_correct_bank_account_type() {
        assertEquals("savings", savingsAccount.getAccountType());
    }

    @Test
    void savings_account_has_the_right_apr_initially() {
        assertEquals(0.01, savingsAccount.getApr());
    }

    @Test
    void savings_account_has_the_right_id() {
        assertEquals(12345678, savingsAccount.getId());
    }

    @Test
    void savings_account_balance_is_0_initially() {
        assertEquals(0, savingsAccount.getBalance());
    }
}
