package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositCommandProcessorTest {
    public static final String VALID_CREATE_CHECKING_COMMAND = "create checking 12345678 0.01";
    public static final String VALID_CREATE_SAVINGS_COMMAND = "create savings 12345678 0.01";
    public static final String VALID_DEPOSIT_COMMAND = "deposit 12345678 1000";
    public static final String ID = "12345678";
    public static final Double APR = 0.01;
    Bank bank;
    CreateCommandProcessor createCommandProcessor;
    DepositCommandProcessor depositCommandProcessor;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        depositCommandProcessor = new DepositCommandProcessor(bank);
        createCommandProcessor = new CreateCommandProcessor(bank);
    }

    @Test
    void deposit_command_deposits_correct_amount_to_a_checking_account_that_was_just_created() {
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        depositCommandProcessor.processCommand(VALID_DEPOSIT_COMMAND);
        assertEquals(1000, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void deposit_command_deposits_correct_amount_to_a_checking_account_that_already_has_money() {
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        depositCommandProcessor.processCommand(VALID_DEPOSIT_COMMAND);
        depositCommandProcessor.processCommand(VALID_DEPOSIT_COMMAND);
        assertEquals(2000, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void deposit_command_deposits_correct_amount_to_a_savings_account_that_was_just_created() {
        createCommandProcessor.processCommand(VALID_CREATE_SAVINGS_COMMAND);
        depositCommandProcessor.processCommand(VALID_DEPOSIT_COMMAND);
        assertEquals(1000, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void deposit_command_deposits_correct_amount_to_a_savings_account_that_already_has_money() {
        createCommandProcessor.processCommand(VALID_CREATE_SAVINGS_COMMAND);
        depositCommandProcessor.processCommand(VALID_DEPOSIT_COMMAND);
        depositCommandProcessor.processCommand(VALID_DEPOSIT_COMMAND);
        assertEquals(2000, bank.getBankAccounts().get(ID).getBalance());
    }
}
