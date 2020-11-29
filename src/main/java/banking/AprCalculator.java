package banking;

public class AprCalculator {

    public double calculateInterest(double balance, double apr) {
        double decimalApr = apr / 100;
        return (decimalApr / 12) * balance;
    }
}
