import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<String, BankAccount> bankAccounts;

    public Bank() {
        bankAccounts = new HashMap<>();
    }

    public Map<String, BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void addBankAccount(BankAccount bankAccount, String id) {
        bankAccounts.put(id, bankAccount);
    }

    public void deleteBankAccount(String id) {
        bankAccounts.remove(id);
    }

    public void deposit(String id, double deposit) {
        bankAccounts.get(id).deposit(deposit);
    }

    public void withdraw(String id, double withdrawal) {
        bankAccounts.get(id).withdraw(withdrawal);
    }

    public boolean bankAccountExistsById(String id) {
        if (bankAccounts.get(id) != null) {
            return true;
        } else {
            return false;
        }
    }
}
