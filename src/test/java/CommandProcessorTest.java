import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandProcessorTest {

    public static final String VALID_CREATE_CHECKING_COMMAND = "create checking 12345678 0.01";
    public static final String VALID_CREATE_SAVINGS_COMMAND = "create savings 12345678 0.01";
    public static final String VALID_CREATE_CD_COMMAND = "create cd 12345678 0.01 1000";
    public static final String VALID_DEPOSIT_COMMAND = "deposit 12345678 1000";
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

    @Test
    void create_savings_command_creates_a_savings_account() {
        commandProcessor.processCommand(VALID_CREATE_SAVINGS_COMMAND);
        assertEquals("savings", bank.getBankAccounts().get(ID).getAccountType());
    }

    @Test
    void create_savings_command_creates_a_savings_account_with_correct_id() {
        commandProcessor.processCommand(VALID_CREATE_SAVINGS_COMMAND);
        assertEquals(ID, bank.getBankAccounts().get(ID).getId());
    }

    @Test
    void create_savings_command_creates_a_savings_account_with_correct_apr() {
        commandProcessor.processCommand(VALID_CREATE_SAVINGS_COMMAND);
        assertEquals(APR, bank.getBankAccounts().get(ID).getApr());
    }

    @Test
    void create_cd_command_creates_a_cd_account() {
        commandProcessor.processCommand(VALID_CREATE_CD_COMMAND);
        assertEquals("cd", bank.getBankAccounts().get(ID).getAccountType());
    }

    @Test
    void create_cd_command_creates_a_cd_account_with_correct_id() {
        commandProcessor.processCommand(VALID_CREATE_CD_COMMAND);
        assertEquals(ID, bank.getBankAccounts().get(ID).getId());
    }

    @Test
    void create_cd_command_creates_a_cd_account_with_correct_apr() {
        commandProcessor.processCommand(VALID_CREATE_CD_COMMAND);
        assertEquals(APR, bank.getBankAccounts().get(ID).getApr());
    }

    @Test
    void create_cd_command_creates_a_cd_account_with_correct_initial_deposit() {
        commandProcessor.processCommand(VALID_CREATE_CD_COMMAND);
        assertEquals(1000, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void deposit_command_deposits_correct_amount_to_a_checking_account_that_was_just_created() {
        commandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        commandProcessor.processCommand(VALID_DEPOSIT_COMMAND);
        assertEquals(1000, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void deposit_command_deposits_correct_amount_to_a_checking_account_with_money() {
        commandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        commandProcessor.processCommand(VALID_DEPOSIT_COMMAND);
        commandProcessor.processCommand(VALID_DEPOSIT_COMMAND);
        assertEquals(2000, bank.getBankAccounts().get(ID).getBalance());
    }
}
