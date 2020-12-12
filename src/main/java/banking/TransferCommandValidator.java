package banking;

public class TransferCommandValidator extends CommandValidator {

    private static final int TRANSFER_COMMAND_ARRAY_LENGTH = 4;

    public TransferCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        setCommandArray(command);
        return transferCommandHasValidNumberOfArguments();


    }

    public boolean transferCommandHasValidNumberOfArguments() {
        return getCommandArray().length == TRANSFER_COMMAND_ARRAY_LENGTH;
    }
}
