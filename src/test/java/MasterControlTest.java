import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MasterControlTest {

    public static final String TYPO_CREATE_COMMAND = "creat checking 12345678 1.0";
    public static final String TYPO_DEPOSIT_COMMAND = "depositt 12345678 100";
    public static final String VALID_CREATE_CHECKING_COMMAND = "create checking 12345678 1.0";
    MasterControl
            masterControl;
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

        assertSingleCommand(VALID_CREATE_CHECKING_COMMAND, actual);
    }
}
