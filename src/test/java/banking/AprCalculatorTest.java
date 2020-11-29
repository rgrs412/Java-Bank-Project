package banking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AprCalculatorTest {

    public static final double APR = 0.6;
    public static final double BALANCE = 5000;

    @Test
    void bank_account_interest_is_calculated_correctly() {
        AprCalculator aprCalculator = new AprCalculator();
        assertEquals(2.50, aprCalculator.calculateInterest(BALANCE, APR));
    }
}
