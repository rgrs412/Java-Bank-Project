package banking;

public abstract class BankAccount {

    protected double balance;
    protected String accountType;
    protected double minimumInitialDeposit;
    protected double maxDeposit;
    protected double maxWithdraw;
    private double apr;
    private String id;

    public BankAccount(String id, double apr) {
        this.apr = apr;
        this.id = id;
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
        balance = balance - withdrawal;
        if (balance < 0) {
            balance = 0;
        }
    }

    public boolean isValidInitialDeposit(Double initialDeposit) {
        return initialDeposit >= minimumInitialDeposit;
    }

    public boolean isValidDeposit(Double depositAmount) {
        return depositAmount <= maxDeposit;
    }

    public boolean isValidWithdraw(Double withdrawAmount) {
        return withdrawAmount <= maxWithdraw;
    }
}
