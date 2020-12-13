package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckingAccountTest {

    public static final double APR = 0.01;
    public static final String ID = "12345678";
    CheckingAccount checkingAccount;

    @BeforeEach
    void setUp() {
        checkingAccount = new CheckingAccount(ID, APR);
    }

    @Test
    void checking_account_is_the_correct_bank_account_type() {
        assertEquals("checking", checkingAccount.getAccountType());
    }

    @Test
    void checking_account_has_the_right_apr_initially() {
        assertEquals(0.01, checkingAccount.getApr());
    }

    @Test
    void checking_account_has_the_right_id() {
        assertEquals("12345678", checkingAccount.getId());
    }

    @Test
    void checking_account_balance_is_0_initially() {
        assertEquals(0, checkingAccount.getBalance());
    }
}
