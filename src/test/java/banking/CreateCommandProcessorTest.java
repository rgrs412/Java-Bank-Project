package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCommandProcessorTest {
    public static final String VALID_CREATE_CHECKING_COMMAND = "create checking 12345678 0.01";
    public static final String VALID_CREATE_SAVINGS_COMMAND = "create savings 12345678 0.01";
    public static final String VALID_CREATE_CD_COMMAND = "create cd 12345678 0.01 1000";
    public static final String ID = "12345678";
    public static final Double APR = 0.01;
    Bank bank;
    CreateCommandProcessor createCommandProcessor;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        createCommandProcessor = new CreateCommandProcessor(bank);
    }

    @Test
    void create_checking_command_creates_a_checking_account() {
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        assertEquals("checking", bank.getBankAccounts().get(ID).getAccountType());
    }

    @Test
    void create_checking_command_creates_a_checking_account_with_correct_id() {
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        assertEquals(ID, bank.getBankAccounts().get(ID).getId());
    }

    @Test
    void create_checking_command_creates_a_checking_account_with_correct_apr() {
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        assertEquals(APR, bank.getBankAccounts().get(ID).getApr());
    }

    @Test
    void create_savings_command_creates_a_savings_account() {
        createCommandProcessor.processCommand(VALID_CREATE_SAVINGS_COMMAND);
        assertEquals("savings", bank.getBankAccounts().get(ID).getAccountType());
    }

    @Test
    void create_savings_command_creates_a_savings_account_with_correct_id() {
        createCommandProcessor.processCommand(VALID_CREATE_SAVINGS_COMMAND);
        assertEquals(ID, bank.getBankAccounts().get(ID).getId());
    }

    @Test
    void create_savings_command_creates_a_savings_account_with_correct_apr() {
        createCommandProcessor.processCommand(VALID_CREATE_SAVINGS_COMMAND);
        assertEquals(APR, bank.getBankAccounts().get(ID).getApr());
    }

    @Test
    void create_cd_command_creates_a_cd_account() {
        createCommandProcessor.processCommand(VALID_CREATE_CD_COMMAND);
        assertEquals("cd", bank.getBankAccounts().get(ID).getAccountType());
    }

    @Test
    void create_cd_command_creates_a_cd_account_with_correct_id() {
        createCommandProcessor.processCommand(VALID_CREATE_CD_COMMAND);
        assertEquals(ID, bank.getBankAccounts().get(ID).getId());
    }

    @Test
    void create_cd_command_creates_a_cd_account_with_correct_apr() {
        createCommandProcessor.processCommand(VALID_CREATE_CD_COMMAND);
        assertEquals(APR, bank.getBankAccounts().get(ID).getApr());
    }

    @Test
    void create_cd_command_creates_a_cd_account_with_correct_initial_deposit() {
        createCommandProcessor.processCommand(VALID_CREATE_CD_COMMAND);
        assertEquals(1000, bank.getBankAccounts().get(ID).getBalance());
    }
}
