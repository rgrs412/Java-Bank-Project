public abstract class BankAccount {

    protected double balance;
    protected String accountType;
    private double apr;
    private int id;

    public BankAccount(int id, double apr) {
        this.apr = apr;
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getApr() {
        return apr;
    }

    public int getId() {
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
}
