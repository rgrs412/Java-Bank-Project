package banking;

import java.util.ArrayList;
import java.util.List;

public class OutputManager {
    private List<String> output;
    private Bank bank;
    private List<BankAccount> bankAccounts;
    private List<String> invalidCommands;

    public OutputManager(Bank bank, CommandStorage commandStorage) {
        this.bank = bank;
        output = new ArrayList<>();
        invalidCommands = commandStorage.getInvalidCommands();
    }

    public List<String> getOutput() {
        createOutput();
        return output;
    }

    public void createOutput() {
        bankAccounts = new ArrayList<>(bank.getBankAccounts().values());
        System.out.println(bankAccounts);
        for (BankAccount bankAccount : bankAccounts) {
            output.add(bankAccount.getAccountState());
            output.addAll(bankAccount.getTransactionHistory());
        }
        output.addAll(invalidCommands);
    }
}
