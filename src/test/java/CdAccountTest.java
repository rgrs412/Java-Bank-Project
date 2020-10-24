import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CdAccountTest {

    public static final double APR = 0.01;
    public static final int ID = 12345678;
    public static final double INITIAL_DEPOSIT = 1.50;
    CdAccount cdAccount;

    @BeforeEach
    void setUp() {
        cdAccount = new CdAccount(ID, APR, INITIAL_DEPOSIT);
    }

    @Test
    void cd_account_is_the_correct_bank_account_type() {
        assertEquals("cd", cdAccount.getAccountType());
    }

    @Test
    void cd_account_has_the_right_id() {
        assertEquals(12345678, cdAccount.getId());
    }

    @Test
    void cd_account_has_the_right_apr_initially() {
        assertEquals(0.01, cdAccount.getApr());
    }

    @Test
    void cd_account_balance_equals_initial_deposit_initially() {
        assertEquals(1.50, cdAccount.getBalance());
    }
}
