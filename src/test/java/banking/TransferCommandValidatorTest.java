package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferCommandValidatorTest {

    public static final String ID = "12345678";
    public static final String ID_TWO = "87654321";
    public static final String TRANSFER_COMMAND_TEMPLATE = String.format("transfer %s %s ", ID, ID_TWO);
    public static final Double APR = 0.01;
    TransferCommandValidator transferCommandValidator;
    Bank bank;
    BankAccount checkingAccount;
    BankAccount checkingAccount2;
    BankAccount savingsAccount;
    BankAccount savingsAccount2;
    BankAccount cdAccount;
    BankAccount cdAccount2;
    TimePasser timePasser;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        transferCommandValidator = new TransferCommandValidator(bank);
        checkingAccount = new CheckingAccount(ID, APR);
        checkingAccount2 = new CheckingAccount(ID_TWO, APR);
        savingsAccount = new SavingsAccount(ID, APR);
        savingsAccount2 = new SavingsAccount(ID_TWO, APR);
        cdAccount = new CdAccount(ID, APR, 1000);
        cdAccount2 = new CdAccount(ID, APR, 1000);
        timePasser = new TimePasser(bank);
    }

    @Test
    void transfer_command_with_less_than_3_arguments_is_invalid() {
        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE));
    }

    @Test
    void transfer_command_with_3_arguments_is_valid() {
        bank.addBankAccount(checkingAccount, ID);
        bank.addBankAccount(checkingAccount2, ID_TWO);

        assertTrue(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "1"));
    }

    @Test
    void transfer_command_with_more_than_3_arguments_is_invalid() {
        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "1 abc"));
    }

    @Test
    void transfer_from_bank_account_that_does_not_exist_is_invalid() {
        bank.addBankAccount(checkingAccount2, ID_TWO);

        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "1"));
    }

    @Test
    void transfer_to_bank_account_that_does_not_exist_is_invalid() {
        bank.addBankAccount(checkingAccount, ID);

        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "1"));
    }

    @Test
    void transfer_400_from_checking_account_into_checking_account_is_valid() {
        bank.addBankAccount(checkingAccount, ID);
        bank.addBankAccount(checkingAccount2, ID_TWO);

        assertTrue(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "400"));
    }

    @Test
    void transfer_401_from_checking_account_into_checking_account_is_invalid() {
        bank.addBankAccount(checkingAccount, ID);
        bank.addBankAccount(checkingAccount2, ID_TWO);

        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "401"));
    }

    @Test
    void transfer_400_from_checking_account_into_savings_account_is_valid() {
        bank.addBankAccount(checkingAccount, ID);
        bank.addBankAccount(savingsAccount2, ID_TWO);

        assertTrue(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "400"));
    }

    @Test
    void transfer_401_from_checking_account_into_savings_account_is_invalid() {
        bank.addBankAccount(checkingAccount, ID);
        bank.addBankAccount(savingsAccount2, ID_TWO);

        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "401"));
    }

    @Test
    void transfer_from_checking_account_into_cd_account_is_invalid() {
        bank.addBankAccount(checkingAccount, ID);
        bank.addBankAccount(cdAccount2, ID_TWO);

        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "400"));
    }

    @Test
    void transfer_1000_from_savings_account_into_savings_account_once_a_month_is_valid() {
        bank.addBankAccount(savingsAccount, ID);
        bank.addBankAccount(savingsAccount2, ID_TWO);

        assertTrue(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "1000"));
    }

    @Test
    void transfer_1001_from_savings_account_into_savings_account_once_a_month_is_invalid() {
        bank.addBankAccount(savingsAccount, ID);
        bank.addBankAccount(savingsAccount2, ID_TWO);

        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "1001"));
    }

    @Test
    void transfer_from_savings_account_into_savings_account_more_than_once_a_month_is_invalid() {
        bank.addBankAccount(savingsAccount, ID);
        bank.addBankAccount(savingsAccount2, ID_TWO);
        bank.transfer(ID, ID_TWO, 1000);

        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "1000"));
    }

    @Test
    void transfer_1000_from_savings_account_into_checking_account_once_a_month_is_valid() {
        bank.addBankAccount(savingsAccount, ID);
        bank.addBankAccount(checkingAccount2, ID_TWO);

        assertTrue(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "1000"));
    }

    @Test
    void transfer_1001_from_savings_account_into_checking_account_once_a_month_is_invalid() {
        bank.addBankAccount(savingsAccount, ID);
        bank.addBankAccount(checkingAccount2, ID_TWO);

        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "1001"));
    }

    @Test
    void transfer_from_savings_account_into_checking_account_more_than_once_a_month_is_invalid() {
        bank.addBankAccount(savingsAccount, ID);
        bank.addBankAccount(checkingAccount2, ID_TWO);
        bank.transfer(ID, ID_TWO, 1000);

        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "1000"));
    }

    @Test
    void transfer_from_savings_account_into_cd_account_is_invalid() {
        bank.addBankAccount(savingsAccount, ID);
        bank.addBankAccount(cdAccount2, ID_TWO);

        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + "1000"));
    }

    @Test
    void transfer_full_account_balance_from_cd_account_to_checking_account_before_12_months_has_passed_is_invalid() {
        bank.addBankAccount(cdAccount, ID);
        bank.addBankAccount(checkingAccount2, ID_TWO);
        bank.deposit(ID_TWO, 100);
        timePasser.passMonths(12);
        String cdAccountBalance = String.valueOf(cdAccount.getBalance());

        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + cdAccountBalance));
    }

    @Test
    void transfer_full_account_balance_from_cd_account_to_checking_account_after_12_months_has_passed_is_invalid() {
        bank.addBankAccount(cdAccount, ID);
        bank.addBankAccount(checkingAccount2, ID_TWO);
        bank.deposit(ID_TWO, 100);
        timePasser.passMonths(12);
        String cdAccountBalance = String.valueOf(cdAccount.getBalance());

        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + cdAccountBalance));
    }

    @Test
    void transfer_full_account_balance_from_cd_account_to_savings_account_before_12_months_has_passed_is_invalid() {
        bank.addBankAccount(cdAccount, ID);
        bank.addBankAccount(savingsAccount2, ID_TWO);
        bank.deposit(ID_TWO, 100);
        timePasser.passMonths(11);
        String cdAccountBalance = String.valueOf(cdAccount.getBalance());

        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + cdAccountBalance));
    }

    @Test
    void transfer_full_account_balance_from_cd_account_to_savings_account_after_12_months_has_passed_is_valid() {
        bank.addBankAccount(cdAccount, ID);
        bank.addBankAccount(savingsAccount2, ID_TWO);
        bank.deposit(ID_TWO, 100);
        timePasser.passMonths(12);
        String cdAccountBalance = String.valueOf(cdAccount.getBalance());

        assertTrue(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + cdAccountBalance));
    }

    @Test
    void transfer_full_account_balance_from_cd_account_to_cd_account_before_12_months_has_passed_is_invalid() {
        bank.addBankAccount(cdAccount, ID);
        bank.addBankAccount(cdAccount2, ID_TWO);
        timePasser.passMonths(11);
        String cdAccountBalance = String.valueOf(cdAccount.getBalance());

        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + cdAccountBalance));
    }

    @Test
    void transfer_full_account_balance_from_cd_account_to_cd_account_after_12_months_has_passed_is_invalid() {
        bank.addBankAccount(cdAccount, ID);
        bank.addBankAccount(cdAccount2, ID_TWO);
        timePasser.passMonths(12);
        String cdAccountBalance = String.valueOf(cdAccount.getBalance());

        assertFalse(transferCommandValidator.validate(TRANSFER_COMMAND_TEMPLATE + cdAccountBalance));
    }
}
