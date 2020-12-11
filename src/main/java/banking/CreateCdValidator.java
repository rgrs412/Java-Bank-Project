package banking;

public class CreateCdValidator extends CreateCommandValidator {

    private static final int CREATE_CD_COMMAND_ARRAY_LENGTH = 5;

    public CreateCdValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        setCommandArray(command);
        return createCdCommandHasValidNumberOfArguments() && isValidMinimumInitialDeposit();
    }

    public boolean createCdCommandHasValidNumberOfArguments() {
        return getCommandArray().length == CREATE_CD_COMMAND_ARRAY_LENGTH;
    }

    public boolean isValidMinimumInitialDeposit() {
        String id = getCommandArray()[2];
        Double initialDeposit = Double.parseDouble(getCommandArray()[4]);
        return bank.isValidInitialDeposit(id, initialDeposit);
    }
}
