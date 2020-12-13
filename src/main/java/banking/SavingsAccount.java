package banking;

public class SavingsAccount extends BankAccount {

    private static final int MONTHLY_WITHDRAWAL_LIMIT = 1;

    public SavingsAccount(String id, double apr) {
        super(id, apr);
        accountType = "savings";
        maxDeposit = 2500;
        maxWithdrawalAmount = 1000;
        withdrawalsThisMonth = 0;
    }

    @Override
    public boolean isValidWithdrawal(Double withdrawalAmount) {
        return (withdrawalAmount <= maxWithdrawalAmount) && (withdrawalsThisMonth < MONTHLY_WITHDRAWAL_LIMIT);
    }

}
