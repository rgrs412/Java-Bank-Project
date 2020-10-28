import java.util.ArrayList;

public class BankInfo {

    private ArrayList<String> bankActions;
    private ArrayList<String> bankAccountTypes;

    public BankInfo() {
        bankActions = new ArrayList<>();
        bankAccountTypes = new ArrayList<>();
        this.addBankActions();
        this.addBankAccountTypes();
    }

    public ArrayList<String> getBankActions() {
        return bankActions;
    }

    public ArrayList<String> getBankAccountTypes() {
        return bankAccountTypes;
    }

    private void addBankActions() {
        bankActions.add("create");
        bankActions.add("deposit");
    }

    private void addBankAccountTypes() {
        bankAccountTypes.add("checking");
        bankAccountTypes.add("savings");
    }
}
