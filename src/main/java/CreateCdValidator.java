public class CreateCdValidator extends CreateCommandValidator {

    private static final int NUMBER_OF_CREATE_SAVINGS_ARGUMENTS = 5;

    public CreateCdValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        setCommandArray(command);
        return createCdCommandHasValidNumberOfArguments() && isValidMinimumInitialDeposit();
    }

    public boolean createCdCommandHasValidNumberOfArguments() {
        return getCommandArray().length == NUMBER_OF_CREATE_SAVINGS_ARGUMENTS;
    }

    public boolean isValidMinimumInitialDeposit() {
        String id = getCommandArray()[2];
        Double initialDeposit = Double.parseDouble(getCommandArray()[4]);
        return bank.isValidInitialDeposit(id, initialDeposit);
    }
}
