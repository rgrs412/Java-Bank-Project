package banking;

public class CreateCheckingValidator extends CreateCommandValidator {

    private static final int NUMBER_OF_CREATE_CHECKING_ARGUMENTS = 4;

    public CreateCheckingValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        setCommandArray(command);
        return createCheckingCommandHasValidNumberOfArguments();
    }

    public boolean createCheckingCommandHasValidNumberOfArguments() {
        return getCommandArray().length == NUMBER_OF_CREATE_CHECKING_ARGUMENTS;
    }
}
