package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandProcessorTest {

    public static final String INVALID_CREATE_CHECKING_COMMAND = "invalid_create checking 12345678 0.01";
    public static final String ID = "12345678";
    Bank bank;
    CommandProcessor commandProcessor;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
    }

    @Test
    void invalid_command_is_not_processed() {
        commandProcessor.processCommand(INVALID_CREATE_CHECKING_COMMAND);
        assertEquals(null, bank.getBankAccounts().get(ID));
    }
}
