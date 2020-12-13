package banking;

public class CreateCheckingValidator extends CreateCommandValidator {

    private static final int CREATE_CHECKING_COMMAND_ARRAY_LENGTH = 4;

    public CreateCheckingValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        setCommandArray(command);
        return createCheckingCommandHasValidNumberOfArguments();
    }

    public boolean createCheckingCommandHasValidNumberOfArguments() {
        return getCommandArray().length == CREATE_CHECKING_COMMAND_ARRAY_LENGTH;
    }
}
