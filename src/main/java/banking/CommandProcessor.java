package banking;

public class CommandProcessor {

    protected Bank bank;
    private String[] commandArray;
    private String command;

    public CommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public String[] getCommandArray() {
        return commandArray;
    }

    public void setCommandArray(String command) {
        commandArray = command.toLowerCase().split(" ");
    }

    public void processCommand(String command) {
        setCommandArray(command);
        this.command = getCommandArray()[0];
        if (this.command.equals("create")) {
            new CreateCommandProcessor(bank).processCommand(command);
        } else if (this.command.equals("deposit")) {
            new DepositCommandProcessor(bank).processCommand(command);
        } else if (this.command.equals("withdraw")) {
            new WithdrawCommandProcessor(bank).processCommand(command);
        } else if (this.command.equals("pass")) {
            new PassCommandProcessor(bank).processCommand(command);
        } else if (this.command.equals("transfer")) {
            new TransferCommandProcessor(bank).processCommand(command);
        }
    }
}
