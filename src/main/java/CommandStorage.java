import java.util.ArrayList;
import java.util.List;

public class CommandStorage {

    private List<String> invalidCommands;

    public CommandStorage() {
        invalidCommands = new ArrayList<>();
    }

    public List<String> getInvalidCommands() {
        return invalidCommands;
    }

    public void addInvalidCommand(String invalidCommand) {
        invalidCommands.add(invalidCommand);
    }
}

