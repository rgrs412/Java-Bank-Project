package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputManagerTest {

    public static final String VALID_CREATE_CHECKING_COMMAND = "create checking 12345678 0.1";
    public static final String VALID_DEPOSIT_COMMAND = "deposit 12345678 100";

    List<String> output;
    Bank bank;
    OutputManager outputManager;
    CommandStorage commandStorage;
    CreateCommandProcessor createCommandProcessor;
    DepositCommandProcessor depositCommandProcessor;

    @BeforeEach
    void setUp() {
        output = new ArrayList<>();
        bank = new Bank();
        commandStorage = new CommandStorage();
        outputManager = new OutputManager(bank, commandStorage);
        createCommandProcessor = new CreateCommandProcessor(bank);
        depositCommandProcessor = new DepositCommandProcessor(bank);
    }

    @Test
    void output_list_is_initially_empty() {
        assertEquals(0, outputManager.getOutput().size());
    }

    @Test
    void output_is_correct_with_one_valid_create_command() {
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        output = outputManager.getOutput();

        assertEquals(1, output.size());
        assertEquals("Checking 12345678 0.00 0.10", output.get(0));
    }

    @Test
    void output_is_correct_with_two_valid_create_command() {
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        createCommandProcessor.processCommand("create checking 87654321 0.1");
        output = outputManager.getOutput();

        assertEquals(2, output.size());
        assertEquals("Checking 12345678 0.00 0.10", output.get(0));
        assertEquals("Checking 87654321 0.00 0.10", output.get(1));
    }

    @Test
    void output_is_correct_with_one_valid_create_command_and_one_invalid_command() {
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        commandStorage.addInvalidCommand("invalid_create checking 12345678 0.01");
        output = outputManager.getOutput();

        assertEquals(2, output.size());
        assertEquals("Checking 12345678 0.00 0.10", output.get(0));
        assertEquals("invalid_create checking 12345678 0.01", output.get(1));
    }

    @Test
    void output_is_correct_with_two_invalid_command() {
        commandStorage.addInvalidCommand("invalid_create checking 12345678 0.01");
        commandStorage.addInvalidCommand("invalid_create savings 12345678 0.01");
        output = outputManager.getOutput();

        assertEquals(2, output.size());
        assertEquals("invalid_create checking 12345678 0.01", output.get(0));
        assertEquals("invalid_create savings 12345678 0.01", output.get(1));
    }

    @Test
    void output_is_correct_with_one_valid_deposit_command() {
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        depositCommandProcessor.processCommand(VALID_DEPOSIT_COMMAND);
        output = outputManager.getOutput();

        assertEquals(2, output.size());
        assertEquals("Checking 12345678 100.00 0.10", output.get(0));
        assertEquals(VALID_DEPOSIT_COMMAND, output.get(1));
    }

    @Test
    void output_is_correct_with_two_valid_deposit_command() {
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        depositCommandProcessor.processCommand(VALID_DEPOSIT_COMMAND);
        depositCommandProcessor.processCommand("deposit 12345678 50");
        output = outputManager.getOutput();

        assertEquals(3, output.size());
        assertEquals("Checking 12345678 150.00 0.10", output.get(0));
        assertEquals(VALID_DEPOSIT_COMMAND, output.get(1));
        assertEquals("deposit 12345678 50", output.get(2));
    }
}
