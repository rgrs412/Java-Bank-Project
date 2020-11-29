package banking;

public class WithdrawCommandValidator extends CommandValidator {

    private static final int NUMBER_OF_WITHDRAW_COMMAND_ARGUMENTS = 3;

    public WithdrawCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        setCommandArray(command);
        if (withdrawCommandHasValidNumberOfArguments()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean withdrawCommandHasValidNumberOfArguments() {
        return getCommandArray().length == NUMBER_OF_WITHDRAW_COMMAND_ARGUMENTS;
    }
}
