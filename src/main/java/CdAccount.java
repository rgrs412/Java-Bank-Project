public class CdAccount extends BankAccount {

    public CdAccount(String id, double apr, double initial_deposit) {
        super(id, apr);
        accountType = "cd";
        balance = initial_deposit;
        minimumInitialDeposit = 1000;
    }
}
