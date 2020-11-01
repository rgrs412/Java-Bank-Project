public abstract class CommandValidator {

    protected Bank bank;
    private String[] commandArray;

    public CommandValidator(Bank bank) {
        this.bank = bank;
    }

    public String[] getCommandArray() {
        return commandArray;
    }

    public void setCommandArray(String command) {
        commandArray = command.toLowerCase().split(" ");
    }

    abstract boolean validate(String command);
}
