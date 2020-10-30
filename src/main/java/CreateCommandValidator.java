public class CreateCommandValidator extends CommandValidator {

    private static final int NUMBER_OF_CREATE_CHECKING_ARGUMENTS = 4;
    private static final int NUMBER_OF_CREATE_SAVINGS_ARGUMENTS = 4;

    @Override
    public boolean validate(String command) {
        setCommand(command);
        return createCommandHasAtLeast4Arguments() && createCommandHasValidNumberOfArguments();
    }

    public boolean createCommandHasAtLeast4Arguments() {
        return getCommand().split(" ").length >= 4;
    }

    public boolean createCommandHasValidNumberOfArguments() {
        String[] commandArray = getCommand().split(" ");
        String bankAccountType = commandArray[1];
        if (bankAccountType.equals("checking")) {
            return commandArray.length == NUMBER_OF_CREATE_CHECKING_ARGUMENTS;
        } else {
            return commandArray.length == NUMBER_OF_CREATE_SAVINGS_ARGUMENTS;
        }
    }
}
