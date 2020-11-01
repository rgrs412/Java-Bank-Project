public class SavingsAccount extends BankAccount {

    public SavingsAccount(String id, double apr) {
        super(id, apr);
        accountType = "savings";
        minimumInitialDeposit = 0;
    }
}
