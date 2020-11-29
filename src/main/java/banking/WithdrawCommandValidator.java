package banking;

public class WithdrawCommandValidator extends CommandValidator {
    public WithdrawCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        return true;
    }
}
