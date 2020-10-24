import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<Integer, BankAccount> bankAccounts;

    public Bank() {
        bankAccounts = new HashMap<>();
    }

    public Map<Integer, BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void addBankAccount(BankAccount bankAccount, int id) {
        bankAccounts.put(id, bankAccount);
    }

    public void deleteBankAccount(int id) {
        bankAccounts.remove(id);
    }

    public void deposit(int id, double deposit) {
        bankAccounts.get(id).deposit(deposit);
    }

    public void withdraw(int id, double withdrawal) {
        bankAccounts.get(id).withdraw(withdrawal);
    }
}
