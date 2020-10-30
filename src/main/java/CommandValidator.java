public class CommandValidator {
    public boolean validate(String command) {
        return isNotEmpty(command);
    }

    public boolean isNotEmpty(String command) {
        return !command.isEmpty();
    }
}
