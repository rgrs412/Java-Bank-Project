package banking;

public class DepositCommandProcessor extends CommandProcessor {

    private String id;
    private Double amount;

    public DepositCommandProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void processCommand(String command) {
        setArguments(command);
        bank.deposit(id, amount);
    }

    public void setArguments(String command) {
        setCommandArray(command);
        id = getCommandArray()[1];
        amount = Double.parseDouble(getCommandArray()[2]);
    }
}
