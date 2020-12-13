package banking;

public class TransferCommandProcessor extends CommandProcessor {

    private String fromId;
    private String toId;
    private String amount;

    public TransferCommandProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void processCommand(String command) {
        setArguments(command);
        double transferAmount = Double.parseDouble(amount);
        bank.transfer(fromId, toId, transferAmount);
    }

    public void setArguments(String command) {
        setCommandArray(command);
        fromId = getCommandArray()[1];
        toId = getCommandArray()[2];
        amount = getCommandArray()[3];
    }
}
