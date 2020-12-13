package banking;

public class WithdrawCommandProcessor extends CommandProcessor {
    private String id;
    private Double amount;

    public WithdrawCommandProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void processCommand(String command) {
        setArguments(command);
        bank.getBankAccounts().get(id).getTransactionHistory().add(command);
        bank.withdraw(id, amount);
    }

    public void setArguments(String command) {
        setCommandArray(command);
        id = getCommandArray()[1];
        amount = Double.parseDouble(getCommandArray()[2]);
    }
}
