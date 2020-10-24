public class CdAccount extends BankAccount {

    public CdAccount(int id, double apr, double initial_deposit) {
        super(id, apr);
        accountType = "cd";
        balance = initial_deposit;
    }
}
