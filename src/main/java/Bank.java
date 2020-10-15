import java.util.HashMap;
import java.util.Map;

public class Bank implements Manager {

    private Map<Integer, BankAccount> bankAccounts;

    public Bank() {
        bankAccounts = new HashMap<>();
    }

    public Map<Integer, BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void addBankAccount(double apr, int id, String accountType) {
        bankAccounts.put(id, new BankAccount(apr, id, accountType));
    }

    public void deleteBankAccount(int id) {
        bankAccounts.remove(id);
    }

    @Override
    public void deposit(int id, double deposit) {
        bankAccounts.get(id).deposit(deposit);
    }

    @Override
    public void withdraw(int id, double withdrawal) {
        bankAccounts.get(id).withdraw(withdrawal);
    }
}
