package banking;

import org.junit.jupiter.api.BeforeEach;

public class CommandProcessorTest {

    Bank bank;
    CommandProcessor commandProcessor;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
    }

}
