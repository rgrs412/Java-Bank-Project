public class BankAccount {

    private double balance;
    private double apr;
    private int id;
    private String accountType;

    public BankAccount(double apr, int id, String accountType) {
        this.apr = apr;
        this.id = id;
        this.accountType = accountType;
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
