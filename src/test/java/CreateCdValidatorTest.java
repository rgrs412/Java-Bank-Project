import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateCdValidatorTest {

    public static final String VALID_CREATE_CD_COMMAND = "create cd 12345678 0.01 1000";
    public static final String ID = "12345678";
    public static final Double APR = 0.01;
    CreateCdValidator createCdValidator;
    Bank bank;
    BankAccount cdAccount;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        createCdValidator = new CreateCdValidator(bank);
        cdAccount = new CdAccount(ID, APR, 1000);
    }

    @Test
    void create_cd_command_with_999_initial_balance_is_invalid() {
        bank.addBankAccount(cdAccount, ID);
        createCdValidator.setCommandArray("create cd 12345678 0.01 999");
        assertFalse(createCdValidator.isValidMinimumInitialDeposit());
    }

    @Test
    void create_cd_command_with_1000_initial_balance_is_valid() {
        bank.addBankAccount(cdAccount, ID);
        createCdValidator.setCommandArray(VALID_CREATE_CD_COMMAND);
        assertTrue(createCdValidator.isValidMinimumInitialDeposit());
    }

    @Test
    void create_cd_command_with_less_than_5_arguments_is_invalid() {
        createCdValidator.setCommandArray("create cd 12345678 0.01");
        assertFalse(createCdValidator.createCdCommandHasValidNumberOfArguments());
    }

    @Test
    void create_cd_command_with_5_arguments_is_valid() {
        createCdValidator.setCommandArray(VALID_CREATE_CD_COMMAND);
        assertTrue(createCdValidator.createCdCommandHasValidNumberOfArguments());
    }

    @Test
    void create_cd_command_with_more_than_5_arguments_is_invalid() {
        createCdValidator.setCommandArray(VALID_CREATE_CD_COMMAND + " abc");
        assertFalse(createCdValidator.createCdCommandHasValidNumberOfArguments());
    }
}
