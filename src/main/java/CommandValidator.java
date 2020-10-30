public abstract class CommandValidator {

    protected Bank bank;
    private String command;

    public CommandValidator(Bank bank) {
        this.bank = bank;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command.toLowerCase();
    }

    public String[] getCommandArray() {
        return command.split(" ");
    }

    abstract boolean validate(String command);
}
