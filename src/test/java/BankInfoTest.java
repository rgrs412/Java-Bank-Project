import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankInfoTest {

    BankInfo bankInfo;

    @BeforeEach
    void setUp() {
        bankInfo = new BankInfo();
    }

    @Test
    void create_is_a_bank_action() {
        assertTrue(bankInfo.getBankActions().contains("create"));
    }

    @Test
    void deposit_is_a_bank_action() {
        assertTrue(bankInfo.getBankActions().contains("deposit"));
    }
}
