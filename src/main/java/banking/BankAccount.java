package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount {

    protected double balance;
    protected String accountType;
    protected double maxDeposit;
    protected double maxWithdrawalAmount;
    protected int withdrawalsThisMonth;
    protected int monthsPassed;
    private double apr;
    private String id;
    private AprCalculator aprCalculator;
    private List<String> transactionHistory;

    public BankAccount(String id, double apr) {
        this.apr = apr;
        this.id = id;
        monthsPassed = 0;
        aprCalculator = new AprCalculator();
        transactionHistory = new ArrayList<>();
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAccountState() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);

        String truncatedBalance = decimalFormat.format(balance);
        String truncatedApr = decimalFormat.format(apr);
        String capitalizedAccountType = accountType.substring(0, 1).toUpperCase() + accountType.substring(1);
        return String.format("%s %s %s %s", capitalizedAccountType, id, truncatedBalance, truncatedApr);
    }

    public double getApr() {
        return apr;
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double deposit) {
        balance += deposit;
    }

    public void withdraw(double withdrawal) {
        balance -= withdrawal;
        withdrawalsThisMonth += 1;
        if (balance < 0) {
            balance = 0;
        }
    }

    public boolean isValidDeposit(Double depositAmount) {
        return (depositAmount <= maxDeposit) && (accountType != "cd");
    }

    public abstract boolean isValidWithdrawal(Double withdrawalAmount);

    public void passMonths(int months, Bank bank) {
        for (int i = 0; i < months; i++) {
            if (balance == 0) {
                bank.deleteBankAccount(id);
            } else if (balance < 100) {
                withdraw(25);
            }
            withdrawalsThisMonth = 0;
            monthsPassed += 1;
            balance += aprCalculator.calculateInterest(this);
        }
    }

}
