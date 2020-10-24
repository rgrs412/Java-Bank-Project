public class SavingsAccount extends BankAccount {

    public SavingsAccount(int id, double apr) {
        super(id, apr);
        accountType = "savings";
    }
}
