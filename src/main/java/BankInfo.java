import java.util.ArrayList;

public class BankInfo {

    private ArrayList<String> bankActions;

    public BankInfo() {
        bankActions = new ArrayList<>();
        this.addBankActions();
    }

    public ArrayList<String> getBankActions() {
        return bankActions;
    }

    private void addBankActions() {
        bankActions.add("create");
    }
}
