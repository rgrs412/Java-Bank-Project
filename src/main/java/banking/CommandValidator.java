package banking;

public class CommandValidator {

    protected Bank bank;
    private String[] commandArray;
    private String command;

    public CommandValidator(Bank bank) {
        this.bank = bank;
    }

    public String[] getCommandArray() {
        return commandArray;
    }

    public void setCommandArray(String command) {
        commandArray = command.toLowerCase().split(" ");
    }

    public boolean validate(String command) {
        setCommandArray(command);
        this.command = getCommandArray()[0];
        if (this.command.equals("create")) {
            return new CreateCommandValidator(bank).validate(command);
        } else if (this.command.equals("deposit")) {
            return new DepositCommandValidator(bank).validate(command);
        } else if (this.command.equals("withdraw")) {
            return new WithdrawCommandValidator(bank).validate(command);
        } else if (this.command.equals("pass")) {
            return new PassCommandValidator(bank).validate(command);
        } else if (this.command.equals("transfer")) {
            return new TransferCommandValidator(bank).validate(command);
        } else {
            return false;
        }
    }
}
