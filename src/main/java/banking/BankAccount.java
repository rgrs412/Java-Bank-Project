package banking;

public abstract class BankAccount {

    protected double balance;
    protected String accountType;
    protected double minimumInitialDeposit;
    protected double maxDeposit;
    protected double maxWithdrawalAmount;
    protected int withdrawalsThisMonth;
    protected int monthsPassed;
    private double apr;
    private String id;
    private AprCalculator aprCalculator;

    public BankAccount(String id, double apr) {
        this.apr = apr;
        this.id = id;
        monthsPassed = 0;
        aprCalculator = new AprCalculator();
    }

    public String getAccountType() {
        return accountType;
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

    public boolean isValidInitialDeposit(Double initialDeposit) {
        return initialDeposit >= minimumInitialDeposit;
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
