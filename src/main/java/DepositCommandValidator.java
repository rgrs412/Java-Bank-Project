public class DepositCommandValidator extends CommandValidator {

    private static final int NUMBER_OF_DEPOSIT_COMMAND_ARGUMENTS = 3;
    private String id;
    private String amount;

    public DepositCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        setCommandArray(command);
        if (depositCommandHasValidNumberOfArguments()) {
            setArguments();
            return bankAccountExistsById() && isValidDeposit();
        } else {
            return false;
        }
    }

    public void setArguments() {
        id = getCommandArray()[1];
        amount = getCommandArray()[2];
    }

    public boolean depositCommandHasValidNumberOfArguments() {
        return getCommandArray().length == NUMBER_OF_DEPOSIT_COMMAND_ARGUMENTS;
    }

    public boolean isValidDeposit() {
        Double depositAmount = Double.parseDouble(amount);
        return bank.isValidDeposit(id, depositAmount);
    }

    public boolean bankAccountExistsById() {
        if (bank.bankAccountExistsById(id)) {
            return true;
        } else {
            return false;
        }
    }
}
