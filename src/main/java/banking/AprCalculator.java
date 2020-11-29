package banking;

public class AprCalculator {

    public double calculateInterest(BankAccount bankAccount) {
        double decimalApr = bankAccount.getApr() / 100;
        if (bankAccount.getAccountType().equals("cd")) {
            double currentBalance = bankAccount.getBalance();
            double currentAccruedInterest;
            for (int i = 0; i < 4; i++) {
                currentAccruedInterest = (decimalApr / 12) * currentBalance;
                currentBalance += currentAccruedInterest;
            }
            return currentBalance - bankAccount.getBalance();
        } else {
            return (decimalApr / 12) * bankAccount.getBalance();
        }
    }
}
