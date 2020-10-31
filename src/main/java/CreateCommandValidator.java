public class CreateCommandValidator extends CommandValidator {

    private static final int NUMBER_OF_CREATE_CHECKING_ARGUMENTS = 4;
    private static final int NUMBER_OF_CREATE_SAVINGS_ARGUMENTS = 4;
    private static final int NUMBER_OF_CREATE_CD_ARGUMENTS = 5;
    private static final String COMMAND = "create";
    private static final String CHECKING = "checking";
    private static final String SAVINGS = "savings";
    private static final String CD = "cd";
    private String id;

    public CreateCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        setCommand(command);
        id = getCommandArray()[2];
        return createCommandHasAtLeast4Arguments() && isCreateCommand() &&
                createCommandHasValidNumberOfArguments() && bankAccountIdIs8DigitNaturalNumber()
                && bankAccountExistsById();
    }

    public boolean createCommandHasAtLeast4Arguments() {
        return getCommand().split(" ").length >= 4;
    }

    public boolean isCreateCommand() {
        String command = getCommandArray()[0];
        if (command.equals(COMMAND)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean createCommandHasValidNumberOfArguments() {
        String bankAccountType = getCommandArray()[1];
        if (bankAccountType.equals(CHECKING)) {
            return getCommandArray().length == NUMBER_OF_CREATE_CHECKING_ARGUMENTS;
        } else if (bankAccountType.equals(SAVINGS)) {
            return getCommandArray().length == NUMBER_OF_CREATE_SAVINGS_ARGUMENTS;
        } else if (bankAccountType.equals(CD)) {
            return getCommandArray().length == NUMBER_OF_CREATE_CD_ARGUMENTS;
        } else {
            return false;
        }
    }

    public boolean bankAccountIdIs8DigitNaturalNumber() {
        return (id.split("").length == 8) && (id.matches("\\d+"));
    }

    public boolean bankAccountExistsById() {
        if (bank.bankAccountExistsById(id)) {
            return false;
        } else {
            return true;
        }
    }
}
