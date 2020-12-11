package banking;

import java.util.ArrayList;
import java.util.List;

public class TimePasser {

    private Bank bank;
    private List<BankAccount> bankAccounts;

    public TimePasser(Bank bank) {
        this.bank = bank;
    }

    public void passMonths(int months) {
        bankAccounts = new ArrayList<>(bank.getBankAccounts().values());
        for (BankAccount bankAccount : bankAccounts) {
            bankAccount.passMonths(months, bank);
        }
    }
}
