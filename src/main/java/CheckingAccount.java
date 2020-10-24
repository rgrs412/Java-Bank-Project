public class CheckingAccount extends BankAccount {

    public CheckingAccount(int id, double apr) {
        super(id, apr);
        accountType = "checking";
    }
}
