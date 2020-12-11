package banking;

public class PassCommandValidator extends CommandValidator {
    private static final int PASS_COMMAND_ARRAY_LENGTH = 2;
    private String month;

    public PassCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        setCommandArray(command);
        return passCommandHasValidNumberOfArguments();
    }

    private boolean passCommandHasValidNumberOfArguments() {
        return getCommandArray().length == PASS_COMMAND_ARRAY_LENGTH;
    }
}
