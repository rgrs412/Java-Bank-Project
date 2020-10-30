public class CreateCommandValidator extends CommandValidator {

    private static final int NUMBER_OF_CREATE_CHECKING_ARGUMENTS = 4;
    private static final int NUMBER_OF_CREATE_SAVINGS_ARGUMENTS = 4;
    private static final int NUMBER_OF_CREATE_CD_ARGUMENTS = 5;

    public CreateCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        setCommand(command);
        return createCommandHasAtLeast4Arguments() && createCommandHasValidNumberOfArguments()
                && bankAccountExistsById();
    }

    public boolean createCommandHasAtLeast4Arguments() {
        return getCommand().split(" ").length >= 4;
    }

    public boolean createCommandHasValidNumberOfArguments() {
        String bankAccountType = getCommandArray()[1];
        if (bankAccountType.equals("checking")) {
            return getCommandArray().length == NUMBER_OF_CREATE_CHECKING_ARGUMENTS;
        } else if (bankAccountType.equals("savings")) {
            return getCommandArray().length == NUMBER_OF_CREATE_SAVINGS_ARGUMENTS;
        } else {
            return getCommandArray().length == NUMBER_OF_CREATE_CD_ARGUMENTS;
        }
    }

    public boolean bankAccountExistsById() {
        String id = getCommandArray()[2];
        if (bank.bankAccountExistsById(id)) {
            return false;
        } else {
            return true;
        }
    }
}
