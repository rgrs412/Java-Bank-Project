public class CreateCommandValidator extends CommandValidator {

    private String bankAccountType;
    private String id;
    private String apr;

    public CreateCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        setArguments(command);
        boolean validation = bankAccountIdIs8DigitNaturalNumber() && aprIsPercentage()
                && bankAccountExistsById();
        if (bankAccountType.equals("checking")) {
            return new CreateCheckingValidator(bank).validate(command) && validation;
        } else if (bankAccountType.equals("savings")) {
            return new CreateSavingsValidator(bank).validate(command) && validation;
        } else if (bankAccountType.equals("cd")) {
            return new CreateCdValidator(bank).validate(command) && validation;
        } else {
            return false;
        }
    }

    public void setArguments(String command) {
        setCommandArray(command);
        bankAccountType = getCommandArray()[1];
        id = getCommandArray()[2];
        apr = getCommandArray()[3];
    }

    public boolean bankAccountIdIs8DigitNaturalNumber() {
        return (id.split("").length == 8) && (id.matches("\\d+"));
    }

    public boolean aprIsPercentage() {
        return apr.matches("\\d+(\\.\\d{1,2})?");
    }

    public boolean bankAccountExistsById() {
        if (bank.bankAccountExistsById(id)) {
            return false;
        } else {
            return true;
        }
    }
}
