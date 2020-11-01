public abstract class CreateCommandValidator extends CommandValidator {

    public CreateCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    abstract boolean validate(String command);


    public boolean bankAccountIdIs8DigitNaturalNumber() {
        String id = getCommandArray()[2];
        return (id.split("").length == 8) && (id.matches("\\d+"));
    }

    public boolean aprIsPercentage() {
        String apr = getCommandArray()[3];
        return apr.matches("\\d+(\\.\\d{1,2})?");
    }

    public boolean bankAccountExistsById() {
        String id = getCommandArray()[2];
        if (bank.bankAccountExistsById(id)) {
            return false;
        } else {
            return true;
        }
    }
}
