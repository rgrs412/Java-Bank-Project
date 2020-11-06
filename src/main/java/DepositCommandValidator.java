public class DepositCommandValidator extends CommandValidator {

    private static final int NUMBER_OF_DEPOSIT_COMMAND_ARGUMENTS = 3;

    public DepositCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        setCommandArray(command);
        return depositCommandHasValidNumberOfArguments() && isValidDeposit();
    }

    public boolean depositCommandHasValidNumberOfArguments() {
        return getCommandArray().length == NUMBER_OF_DEPOSIT_COMMAND_ARGUMENTS;
    }

    public boolean isValidDeposit() {
        String id = getCommandArray()[1];
        Double depositAmount = Double.parseDouble(getCommandArray()[2]);
        return bank.isValidDeposit(id, depositAmount);
    }
}
