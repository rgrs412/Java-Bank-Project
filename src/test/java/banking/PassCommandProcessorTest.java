package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassCommandProcessorTest {
    public static final String VALID_CREATE_CHECKING_COMMAND = "create checking 12345678 0.90";
    public static final String VALID_CREATE_SAVINGS_COMMAND = "create savings 12345678 0.90";
    public static final String VALID_CREATE_CD_COMMAND = "create cd 12345678 0.90 1000";
    public static final String VALID_DEPOSIT_COMMAND = "deposit 12345678 25";
    public static final String VALID_DEPOSIT_COMMAND2 = "deposit 12345678 100";
    public static final String VALID_PASS_ONE_MONTH_COMMAND = "pass 1";
    public static final String VALID_PASS_TWO_MONTH_COMMAND = "pass 2";
    public static final String ID = "12345678";
    public static final Double APR = 0.90;
    Bank bank;
    CreateCommandProcessor createCommandProcessor;
    DepositCommandProcessor depositCommandProcessor;
    PassCommandProcessor passCommandProcessor;
    WithdrawCommandProcessor withdrawCommandProcessor;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        createCommandProcessor = new CreateCommandProcessor(bank);
        depositCommandProcessor = new DepositCommandProcessor(bank);
        passCommandProcessor = new PassCommandProcessor(bank);
        withdrawCommandProcessor = new WithdrawCommandProcessor(bank);
    }

    @Test
    void checking_account_with_no_money_will_be_closed_after_a_month_passes() {
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        passCommandProcessor.processCommand(VALID_PASS_ONE_MONTH_COMMAND);

        assertEquals(0, bank.getBankAccounts().size());
        assertEquals(null, bank.getBankAccounts().get(ID));
    }

    @Test
    void savings_account_with_no_money_will_be_closed_after_a_month_passes() {
        createCommandProcessor.processCommand(VALID_CREATE_SAVINGS_COMMAND);
        passCommandProcessor.processCommand(VALID_PASS_ONE_MONTH_COMMAND);

        assertEquals(0, bank.getBankAccounts().size());
        assertEquals(null, bank.getBankAccounts().get(ID));
    }

    @Test
    void cd_account_with_no_money_will_be_closed_after_a_month_passes() {
        createCommandProcessor.processCommand(VALID_CREATE_CD_COMMAND);
        withdrawCommandProcessor.processCommand("withdraw 12345678 1000");
        passCommandProcessor.processCommand(VALID_PASS_ONE_MONTH_COMMAND);

        assertEquals(null, bank.getBankAccounts().get(ID));
    }

    @Test
    void checking_account_is_not_closed_if_balance_is_more_than_0_after_a_month_passes() {
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        depositCommandProcessor.processCommand(VALID_DEPOSIT_COMMAND);
        passCommandProcessor.processCommand(VALID_PASS_ONE_MONTH_COMMAND);

        assertEquals(ID, bank.getBankAccounts().get(ID).getId());
    }

    @Test
    void savings_account_is_not_closed_if_balance_is_more_than_0_after_a_month_passes() {
        createCommandProcessor.processCommand(VALID_CREATE_SAVINGS_COMMAND);
        depositCommandProcessor.processCommand(VALID_DEPOSIT_COMMAND);
        passCommandProcessor.processCommand(VALID_PASS_ONE_MONTH_COMMAND);

        assertEquals(ID, bank.getBankAccounts().get(ID).getId());
    }

    @Test
    void cd_account_is_not_closed_if_balance_is_more_than_0_after_a_month_passes() {
        createCommandProcessor.processCommand(VALID_CREATE_CD_COMMAND);
        passCommandProcessor.processCommand(VALID_PASS_ONE_MONTH_COMMAND);

        assertEquals(ID, bank.getBankAccounts().get(ID).getId());
    }

    @Test
    void checking_account_is_closed_after_two_months_if_balance_is_25() {
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        depositCommandProcessor.processCommand(VALID_DEPOSIT_COMMAND);
        passCommandProcessor.processCommand(VALID_PASS_TWO_MONTH_COMMAND);

        assertEquals(null, bank.getBankAccounts().get(ID));
    }

    @Test
    void savings_account_is_closed_after_two_months_if_balance_is_25() {
        createCommandProcessor.processCommand(VALID_CREATE_SAVINGS_COMMAND);
        depositCommandProcessor.processCommand(VALID_DEPOSIT_COMMAND);
        passCommandProcessor.processCommand(VALID_PASS_TWO_MONTH_COMMAND);

        assertEquals(null, bank.getBankAccounts().get(ID));
    }

    @Test
    void checking_account_accrues_interest() {
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        depositCommandProcessor.processCommand(VALID_DEPOSIT_COMMAND2);
        passCommandProcessor.processCommand(VALID_PASS_ONE_MONTH_COMMAND);

        assertEquals(100.075, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void savings_account_accrues_interest() {
        createCommandProcessor.processCommand(VALID_CREATE_SAVINGS_COMMAND);
        depositCommandProcessor.processCommand(VALID_DEPOSIT_COMMAND2);
        passCommandProcessor.processCommand(VALID_PASS_ONE_MONTH_COMMAND);

        assertEquals(100.075, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void cd_account_accrues_interest_4_times_more_frequently_than_checking_account() {
        createCommandProcessor.processCommand(VALID_CREATE_CD_COMMAND);
        passCommandProcessor.processCommand(VALID_PASS_ONE_MONTH_COMMAND);

        Bank bank2 = new Bank();
        CreateCommandProcessor createCommandProcessor2 = new CreateCommandProcessor(bank2);
        createCommandProcessor2.processCommand("create checking 87654321 0.90");
        DepositCommandProcessor depositCommandProcessor2 = new DepositCommandProcessor(bank2);
        depositCommandProcessor2.processCommand("deposit 87654321 1000");
        TimePasser timePasser2 = new TimePasser(bank2);
        timePasser2.passMonths(4);

        double cdAccountBalance = bank.getBankAccounts().get(ID).getBalance();
        double checkingAccountBalance = bank2.getBankAccounts().get("87654321").getBalance();

        assertEquals(checkingAccountBalance, cdAccountBalance);
    }

    @Test
    void checking_account_balance_is_deducted_25_if_balance_is_less_than_100() {
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        depositCommandProcessor.processCommand("deposit 12345678 99");
        passCommandProcessor.processCommand(VALID_PASS_ONE_MONTH_COMMAND);

        assertEquals(74.0555, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void savings_account_balance_is_deducted_25_if_balance_is_less_than_100() {
        createCommandProcessor.processCommand(VALID_CREATE_SAVINGS_COMMAND);
        depositCommandProcessor.processCommand("deposit 12345678 99");
        passCommandProcessor.processCommand(VALID_PASS_ONE_MONTH_COMMAND);

        assertEquals(74.0555, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void checking_account_cannot_be_deducted_below_0() {
        createCommandProcessor.processCommand(VALID_CREATE_CHECKING_COMMAND);
        depositCommandProcessor.processCommand("deposit 12345678 20");
        passCommandProcessor.processCommand(VALID_PASS_ONE_MONTH_COMMAND);

        assertEquals(0, bank.getBankAccounts().get(ID).getBalance());
    }

    @Test
    void savings_account_cannot_be_deducted_below_0() {
        createCommandProcessor.processCommand(VALID_CREATE_SAVINGS_COMMAND);
        depositCommandProcessor.processCommand("deposit 12345678 20");
        passCommandProcessor.processCommand(VALID_PASS_ONE_MONTH_COMMAND);

        assertEquals(0, bank.getBankAccounts().get(ID).getBalance());
    }
}
