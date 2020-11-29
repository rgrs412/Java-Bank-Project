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
        for (int i = 0; i < months; i++) {
            for (BankAccount bankAccount : bankAccounts) {
                if (bankAccount.balance == 0) {
                    bank.deleteBankAccount(bankAccount.getId());
                } else if (bankAccount.balance < 100) {
                    bank.withdraw(bankAccount.getId(), 25);
                }
            }
        }
    }
}
