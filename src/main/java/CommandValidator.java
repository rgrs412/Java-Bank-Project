public abstract class CommandValidator {
    abstract boolean validate(String command);

    public boolean isNotEmpty(String command) {
        return !command.isEmpty();
    }
}
