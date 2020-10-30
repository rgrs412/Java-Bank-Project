public abstract class CommandValidator {

    protected Bank bank;
    private String command;

    public CommandValidator() {
        bank = new Bank();
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    abstract boolean validate(String command);
}
