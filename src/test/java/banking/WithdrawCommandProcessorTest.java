package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WithdrawCommandProcessorTest {
    public static final String VALID_CREATE_CHECKING_COMMAND = "create checking 12345678 0.01";
    public static final String VALID_CREATE_SAVINGS_COMMAND = "create savings 12345678 0.01";
    public static final String VALID_CREATE_CD_COMMAND = "create cd 12345678 0.01 1000";
    public static final String VALID_DEPOSIT_COMMAND = "deposit 12345678 1000";
    public static final String WITHDRAW_COMMAND_TEMPLATE = "withdraw 12345678 ";
    public static final String ID = "12345678";
    public static final Double APR = 0.01;
    Bank bank;
    CreateCommandProcessor createCommandProcessor;
    DepositCommandProcessor depositCommandProcessor;
    WithdrawCommandProcessor withdrawCommandProcessor;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        createCommandProcessor = new CreateCommandProcessor(bank);
        depositCommandProcessor = new DepositCommandProcessor(bank);
        withdrawCommandProcessor = new WithdrawCommandProcessor(bank);
    }

    @Test
    void withdraw_command_withdraws_correct_amount_from_a_checking_account_that_has_no_money() {
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        withdrawCommandProcessor.processCommand(WITHDRAW_COMMAND_TEMPLATE + "1");

        assertEquals(0, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void withdraw_command_withdraws_correct_amount_from_a_checking_account_that_already_has_money() {
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        depositCommandProcessor.processCommand(VALID_DEPOSIT_COMMAND);
        withdrawCommandProcessor.processCommand(WITHDRAW_COMMAND_TEMPLATE + "1");

        assertEquals(999, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void withdraw_command_withdraws_correct_amount_from_a_savings_account_that_has_no_money() {
        createCommandProcessor.processCommand(VALID_CREATE_SAVINGS_COMMAND);
        withdrawCommandProcessor.processCommand(WITHDRAW_COMMAND_TEMPLATE + "1");

        assertEquals(0, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void withdraw_command_withdraws_correct_amount_from_a_savings_account_that_already_has_money() {
        createCommandProcessor.processCommand(VALID_CREATE_SAVINGS_COMMAND);
        depositCommandProcessor.processCommand(VALID_DEPOSIT_COMMAND);
        withdrawCommandProcessor.processCommand(WITHDRAW_COMMAND_TEMPLATE + "1");

        assertEquals(999, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void withdraw_command_withdraws_correct_amount_from_a_cd_account() {
        createCommandProcessor.processCommand(VALID_CREATE_CD_COMMAND);
        withdrawCommandProcessor.processCommand(WITHDRAW_COMMAND_TEMPLATE + "1000");

        assertEquals(0, bank.getBankAccounts().get(ID).getBalance());
    }
}
