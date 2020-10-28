public class CommandValidator {

    private Bank bank;

    public CommandValidator() {
        bank = new Bank();
    }

    public boolean isNotEmpty(String command) {
        return !command.isEmpty();
    }

    public boolean isValidCommand(String command) {
        String commandType = command.split(" ")[0].toLowerCase();
        return bank.getBankInfo().getBankActions().contains(commandType);
    }
}
