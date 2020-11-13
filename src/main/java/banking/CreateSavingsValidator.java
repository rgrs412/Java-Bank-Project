package banking;

public class CreateSavingsValidator extends CreateCommandValidator {

    private static final int NUMBER_OF_CREATE_SAVINGS_ARGUMENTS = 4;

    public CreateSavingsValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        setCommandArray(command);
        return createSavingsCommandHasValidNumberOfArguments();
    }

    public boolean createSavingsCommandHasValidNumberOfArguments() {
        return getCommandArray().length == NUMBER_OF_CREATE_SAVINGS_ARGUMENTS;
    }
}
