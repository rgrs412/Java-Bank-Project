public class CommandValidator {

    public boolean isValidNumberOfCommandArguments(String command) {
        return command.split(" ").length == 4;
    }

    public boolean isNotEmpty(String command) {
        return true;
    }
}
