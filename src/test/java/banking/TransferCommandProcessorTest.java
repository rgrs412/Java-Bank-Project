package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferCommandProcessorTest {
    public static final String ID = "12345678";
    public static final String ID_TWO = "87654321";
    public static final String TRANSFER_COMMAND_TEMPLATE = String.format("transfer %s %s ", ID, ID_TWO);
    public static final String VALID_CREATE_CHECKING_COMMAND = "create checking 12345678 0.1";
    public static final String VALID_CREATE_CHECKING_COMMAND_2 = "create checking 87654321 0.1";
    Bank bank;
    TransferCommandProcessor transferCommandProcessor;
    CreateCommandProcessor createCommandProcessor;
    DepositCommandProcessor depositCommandProcessor;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        transferCommandProcessor = new TransferCommandProcessor(bank);
        createCommandProcessor = new CreateCommandProcessor(bank);
        depositCommandProcessor = new DepositCommandProcessor(bank);
    }

    @Test
    void transfer_money_from_bank_account_to_bank_account() {
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND_2);
        depositCommandProcessor.processCommand("deposit 12345678 1");
        transferCommandProcessor.processCommand(TRANSFER_COMMAND_TEMPLATE + "1");
        bank.transfer(ID, ID_TWO, 1);

        assertEquals(0, bank.getBankAccounts().get(ID).getBalance());
        assertEquals(1, bank.getBankAccounts().get(ID_TWO).getBalance());
    }

    @Test
    void transfer_money_from_bank_account_to_bank_account_more_than_once() {
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND_2);
        depositCommandProcessor.processCommand("deposit 12345678 2");
        transferCommandProcessor.processCommand(TRANSFER_COMMAND_TEMPLATE + "1");
        transferCommandProcessor.processCommand(TRANSFER_COMMAND_TEMPLATE + "1");

        assertEquals(0, bank.getBankAccounts().get(ID).getBalance());
        assertEquals(2, bank.getBankAccounts().get(ID_TWO).getBalance());
    }

}
