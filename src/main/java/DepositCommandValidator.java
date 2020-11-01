public class DepositCommandValidator extends CommandValidator {

    public DepositCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    boolean validate(String command) {
        return false;
    }


    public boolean isValidDeposit() {
        String id = getCommandArray()[1];
        Double depositAmount = Double.parseDouble(getCommandArray()[2]);
        return bank.isValidDeposit(id, depositAmount);
    }
}
