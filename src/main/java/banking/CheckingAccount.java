package banking;

public class CheckingAccount extends BankAccount {

    public CheckingAccount(String id, double apr) {
        super(id, apr);
        accountType = "checking";
        maxDeposit = 1000;
        maxWithdrawalAmount = 400;
    }

    @Override
    public boolean isValidWithdrawal(Double withdrawalAmount) {
        return withdrawalAmount <= maxWithdrawalAmount;
    }
}
