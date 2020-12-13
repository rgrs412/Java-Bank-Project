package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MasterControlTest {

    public static final String TYPO_CREATE_COMMAND = "creat checking 12345678 1.0";
    public static final String TYPO_DEPOSIT_COMMAND = "depositt 12345678 100";
    public static final String VALID_CREATE_CHECKING_COMMAND = "create checking 12345678 1.0";
    MasterControl masterControl;
    Bank bank;
    List<String> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>();
        bank = new Bank();
        masterControl = new MasterControl(bank, new CommandValidator(bank),
                new CommandProcessor(bank), new CommandStorage());
    }

    private void assertSingleCommand(String command, List<String> actual) {
        assertEquals(1, actual.size());
        assertEquals(command, actual.get(0));
    }

    @Test
    void typo_in_create_command_is_invalid() {
        input.add(TYPO_CREATE_COMMAND);

        List<String> actual = masterControl.start(input);

        assertSingleCommand(TYPO_CREATE_COMMAND, actual);
    }

    @Test
    void typo_in_deposit_command_is_invalid() {
        input.add(TYPO_DEPOSIT_COMMAND);

        List<String> actual = masterControl.start(input);

        assertSingleCommand(TYPO_DEPOSIT_COMMAND, actual);
    }

    @Test
    void two_typo_commands_both_invalid() {
        input.add(TYPO_CREATE_COMMAND);
        input.add(TYPO_DEPOSIT_COMMAND);

        List<String> actual = masterControl.start(input);

        assertEquals(2, actual.size());
        assertEquals(TYPO_CREATE_COMMAND, actual.get(0));
        assertEquals(TYPO_DEPOSIT_COMMAND, actual.get(1));
    }

    @Test
    void invalid_to_create_accounts_with_same_id() {
        input.add(VALID_CREATE_CHECKING_COMMAND);
        input.add(VALID_CREATE_CHECKING_COMMAND);

        List<String> actual = masterControl.start(input);

        assertEquals(2, actual.size());
        assertEquals("Checking 12345678 0.00 1.00", actual.get(0));
        assertEquals("create checking 12345678 1.0", actual.get(1));
    }

    @Test
    void sample_make_sure_this_passes_unchanged_or_you_will_fail() {
        input.add("Create savings 12345678 0.6");
        input.add("Deposit 12345678 700");
        input.add("Deposit 12345678 5000");
        input.add("creAte cHecKing 98765432 0.01");
        input.add("Deposit 98765432 300");
        input.add("Transfer 98765432 12345678 300");
        input.add("Pass 1");
        input.add("Create cd 23456789 1.2 2000");
        List<String> actual = masterControl.start(input);

        assertEquals(5, actual.size());
        assertEquals("Savings 12345678 1000.50 0.60", actual.get(0));
        assertEquals("Deposit 12345678 700", actual.get(1));
        assertEquals("Transfer 98765432 12345678 300", actual.get(2));
        assertEquals("Cd 23456789 2000.00 1.20", actual.get(3));
        assertEquals("Deposit 12345678 5000", actual.get(4));
    }
}
