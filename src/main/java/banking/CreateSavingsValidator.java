package banking;

public class CreateSavingsValidator extends CreateCommandValidator {

    private static final int CREATE_SAVINGS_COMMAND_ARRAY_LENGTH = 4;

    public CreateSavingsValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        setCommandArray(command);
        return createSavingsCommandHasValidNumberOfArguments();
    }

    public boolean createSavingsCommandHasValidNumberOfArguments() {
        return getCommandArray().length == CREATE_SAVINGS_COMMAND_ARRAY_LENGTH;
    }
}
