package banking;

public class TransferCommandValidator extends CommandValidator {

    private static final int TRANSFER_COMMAND_ARRAY_LENGTH = 4;
    private String fromId;
    private String toId;
    private String amount;

    public TransferCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        setCommandArray(command);
        if (transferCommandHasValidNumberOfArguments()) {
            setArguments();
            return bankAccountExistsById();
        } else {
            return false;
        }
    }

    public void setArguments() {
        fromId = getCommandArray()[1];
        toId = getCommandArray()[2];
        amount = getCommandArray()[3];
    }

    public boolean transferCommandHasValidNumberOfArguments() {
        return getCommandArray().length == TRANSFER_COMMAND_ARRAY_LENGTH;
    }

    public boolean bankAccountExistsById() {
        return bank.bankAccountExistsById(fromId) && bank.bankAccountExistsById(toId);
    }

}
