public class CheckingAccount extends BankAccount {

    public CheckingAccount(String id, double apr) {
        super(id, apr);
        accountType = "checking";
    }
}
