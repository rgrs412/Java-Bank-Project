package banking;

public class PassCommandProcessor extends CommandProcessor {
    private String months;
    private TimePasser timePasser;

    public PassCommandProcessor(Bank bank) {
        super(bank);
        timePasser = new TimePasser(bank);
    }

    @Override
    public void processCommand(String command) {
        setArguments(command);
        int intMonths = Integer.parseInt(months);
        timePasser.passMonths(intMonths);
    }

    public void setArguments(String command) {
        setCommandArray(command);
        months = getCommandArray()[1];
    }
}
