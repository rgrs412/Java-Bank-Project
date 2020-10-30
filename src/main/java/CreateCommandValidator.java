public class CreateCommandValidator extends CommandValidator {
    @Override
    public boolean validate(String command) {
        return isNotEmpty(command) && createCheckingHas4Arguments(command);
    }

    public boolean createCheckingHas4Arguments(String command) {
        return command.split(" ").length == 4;
    }

}
