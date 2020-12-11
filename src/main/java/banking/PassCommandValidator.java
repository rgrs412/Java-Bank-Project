package banking;

public class PassCommandValidator extends CommandValidator {
    private static final int PASS_COMMAND_ARRAY_LENGTH = 2;
    private String months;

    public PassCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        setCommandArray(command);
        if (passCommandHasValidNumberOfArguments()) {
            setArguments();
            return isValidMonth();
        } else {
            return false;
        }
    }

    public void setArguments() {
        months = getCommandArray()[1];
    }

    public boolean passCommandHasValidNumberOfArguments() {
        return getCommandArray().length == PASS_COMMAND_ARRAY_LENGTH;
    }

    public boolean isValidMonth() {
        return months.matches("[1-9]\\d*");
    }

}
