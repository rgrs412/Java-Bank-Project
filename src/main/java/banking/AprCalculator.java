package banking;

public class AprCalculator {

    public double calculateInterest(BankAccount bankAccount) {
        double decimalApr = bankAccount.getApr() / 100;
        return (decimalApr / 12) * bankAccount.getBalance();
    }
}
