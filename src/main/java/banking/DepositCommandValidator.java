package banking;

public class DepositCommandValidator extends CommandValidator {

    private static final int DEPOSIT_COMMAND_ARRAY_LENGTH = 3;
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
        return getCommandArray().length == DEPOSIT_COMMAND_ARRAY_LENGTH;
    }

    public boolean isValidDeposit() {
        Double depositAmount = Double.parseDouble(amount);
        return bank.isValidDeposit(id, depositAmount);
    }

    public boolean bankAccountExistsById() {
        return bank.bankAccountExistsById(id);
    }
}
