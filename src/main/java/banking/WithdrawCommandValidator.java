package banking;

public class WithdrawCommandValidator extends CommandValidator {

    private static final int NUMBER_OF_WITHDRAW_COMMAND_ARGUMENTS = 3;
    private String id;
    private String amount;

    public WithdrawCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        setCommandArray(command);
        if (withdrawCommandHasValidNumberOfArguments()) {
            setArguments();
            return bankAccountExistsById() && isValidWithdrawal();
        } else {
            return false;
        }
    }

    private void setArguments() {
        id = getCommandArray()[1];
        amount = getCommandArray()[2];
    }

    public boolean withdrawCommandHasValidNumberOfArguments() {
        return getCommandArray().length == NUMBER_OF_WITHDRAW_COMMAND_ARGUMENTS;
    }

    public boolean bankAccountExistsById() {
        if (bank.bankAccountExistsById(id)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidWithdrawal() {
        Double withdrawalAmount = Double.parseDouble(amount);
        return bank.isValidWithdrawal(id, withdrawalAmount);
    }
}
