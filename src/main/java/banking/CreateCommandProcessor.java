package banking;

public class CreateCommandProcessor extends CommandProcessor {

    private String bankAccountType;
    private String id;
    private double apr;

    public CreateCommandProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void processCommand(String command) {
        setArguments(command);
        if (bankAccountType.equals("checking")) {
            bank.addBankAccount(new CheckingAccount(id, apr), id);
        } else if (bankAccountType.equals("savings")) {
            bank.addBankAccount(new SavingsAccount(id, apr), id);
        } else if (bankAccountType.equals("cd")) {
            Double initialDeposit = Double.parseDouble(getCommandArray()[4]);
            bank.addBankAccount(new CdAccount(id, apr, initialDeposit), id);
        }
    }

    public void setArguments(String command) {
        setCommandArray(command);
        bankAccountType = getCommandArray()[1];
        id = getCommandArray()[2];
        apr = Double.parseDouble(getCommandArray()[3]);
    }
}
