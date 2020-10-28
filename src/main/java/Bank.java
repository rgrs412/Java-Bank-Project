import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<Integer, BankAccount> bankAccounts;
    private BankInfo bankInfo;

    public Bank() {
        bankAccounts = new HashMap<>();
        bankInfo = new BankInfo();
    }

    public Map<Integer, BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public BankInfo getBankInfo() {
        return bankInfo;
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
