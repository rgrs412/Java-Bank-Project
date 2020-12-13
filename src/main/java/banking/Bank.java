package banking;

import java.util.LinkedHashMap;
import java.util.Map;

public class Bank {

    private Map<String, BankAccount> bankAccounts;

    public Bank() {
        bankAccounts = new LinkedHashMap<>();
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

    public void transfer(String fromId, String toId, double amount) {
        double beforeWithdrawalAmount = bankAccounts.get(fromId).getBalance();
        withdraw(fromId, amount);
        if (bankAccounts.get(fromId).getBalance() == 0) {
            deposit(toId, beforeWithdrawalAmount);
        } else {
            deposit(toId, amount);
        }
    }

    public boolean bankAccountExistsById(String id) {
        if (bankAccounts.get(id) != null) {
            return true;
        } else {
            return false;
        }
    }


    public boolean isValidDeposit(String id, Double depositAmount) {
        return bankAccounts.get(id).isValidDeposit(depositAmount);
    }

    public boolean isValidWithdrawal(String id, Double withdrawalAmount) {
        return bankAccounts.get(id).isValidWithdrawal(withdrawalAmount);
    }

    public boolean isValidTransfer(String fromId, String toId, Double transferAmount) {
        return isValidWithdrawal(fromId, transferAmount) && isValidDeposit(toId, transferAmount);
    }
}
