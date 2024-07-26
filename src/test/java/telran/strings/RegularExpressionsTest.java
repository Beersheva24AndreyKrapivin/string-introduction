package telran.strings;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static telran.strings.Strings.*;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

public class RegularExpressionsTest {

    @Test
    void javaVariableTest() {
        assertTrue(Pattern.matches(javaVariable(), "name"));
        assertTrue(Pattern.matches(javaVariable(), "n"));
        assertTrue(Pattern.matches(javaVariable(), "name12"));
        assertTrue(Pattern.matches(javaVariable(), "naAe_"));
        assertTrue(Pattern.matches(javaVariable(), "name$$"));
        assertFalse(Pattern.matches(javaVariable(), "name 2"));
        assertFalse(Pattern.matches(javaVariable(), "1name"));
        assertFalse(Pattern.matches(javaVariable(), "nam.e"));
    }
}
