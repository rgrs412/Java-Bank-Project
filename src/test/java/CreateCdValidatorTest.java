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
    void create_cd_command_with_less_than_5_arguments_is_invalid() {
        assertFalse(createCdValidator.validate("create cd 12345678 0.01"));
    }

    @Test
    void create_cd_command_with_5_arguments_is_valid() {
        assertTrue(createCdValidator.validate(VALID_CREATE_CD_COMMAND));
    }

    @Test
    void create_cd_command_with_more_than_5_arguments_is_invalid() {
        assertFalse(createCdValidator.validate(VALID_CREATE_CD_COMMAND + " abc"));
    }
}
