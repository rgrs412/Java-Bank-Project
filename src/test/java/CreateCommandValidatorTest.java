import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateCommandValidatorTest {

    public static final String VALID_CREATE_COMMAND = "create checking 12345678 0.01";
    CreateCommandValidator createCommandValidator;

    @BeforeEach
    void setUp() {
        createCommandValidator = new CreateCommandValidator();
    }

    @Test
    void create_checking_has_4_arguments_is_valid() {
        assertTrue(createCommandValidator.validate(VALID_CREATE_COMMAND));
    }
}
