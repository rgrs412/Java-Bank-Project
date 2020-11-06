import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandProcessorTest {

    public static final String VALID_CREATE_CHECKING_COMMAND = "create checking 12345678 0.01";
    public static final String ID = "12345678";
    public static final Double APR = 0.01;
    Bank bank;
    CommandProcessor commandProcessor;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
    }

    @Test
    void create_checking_command_creates_a_checking_account() {
        commandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        assertEquals("checking", bank.getBankAccounts().get(ID).getAccountType());
    }

    @Test
    void create_checking_command_creates_a_checking_account_with_correct_id() {
        commandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        assertEquals(ID, bank.getBankAccounts().get(ID).getId());
    }

    @Test
    void create_checking_command_creates_a_checking_account_with_correct_apr() {
        commandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        assertEquals(APR, bank.getBankAccounts().get(ID).getApr());
    }
}
