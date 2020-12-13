package banking;

import java.util.List;

public class MasterControl {

    Bank bank;
    CommandValidator commandValidator;
    CommandProcessor commandProcessor;
    CommandStorage commandStorage;
    OutputManager outputManager;

    public MasterControl(Bank bank, CommandValidator commandValidator, CommandProcessor commandProcessor,
                         CommandStorage commandStorage) {
        this.bank = bank;
        this.commandValidator = commandValidator;
        this.commandProcessor = commandProcessor;
        this.commandStorage = commandStorage;
        this.outputManager = new OutputManager(bank, commandStorage);
    }

    public List<String> start(List<String> input) {
        for (String command : input) {
            if (commandValidator.validate(command)) {
                commandProcessor.processCommand(command);
            } else {
                commandStorage.addInvalidCommand(command);
            }
        }
        return outputManager.getOutput();
    }
}
