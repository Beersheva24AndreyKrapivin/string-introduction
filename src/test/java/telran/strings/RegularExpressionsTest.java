package telran.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static telran.strings.Strings.*;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

public class RegularExpressionsTest {

    @Test
    void javaVariableTest() {
        assertTrue(Pattern.matches(javaVariable(), "name"));
        assertTrue(Pattern.matches(javaVariable(), "_name"));
        assertTrue(Pattern.matches(javaVariable(), "n"));
        assertTrue(Pattern.matches(javaVariable(), "name12"));
        assertTrue(Pattern.matches(javaVariable(), "naAe_"));
        assertTrue(Pattern.matches(javaVariable(), "name$$"));
        assertTrue(Pattern.matches(javaVariable(), "$"));
       
        assertFalse(Pattern.matches(javaVariable(), "name 2"));
        assertFalse(Pattern.matches(javaVariable(), "1name"));
        assertFalse(Pattern.matches(javaVariable(), "nam.e"));
        assertFalse(Pattern.matches(javaVariable(), "[1"));
        assertFalse(Pattern.matches(javaVariable(), "_"));
    }

    @Test
    void number0_300TrueTest() {
        String regex = Strings.number0_300();
        assertTrue("0".matches(regex));
        assertTrue("300".matches(regex));
        assertTrue("250".matches(regex));
        assertTrue("25".matches(regex));
        assertTrue("12".matches(regex));
        assertTrue("299".matches(regex));
        assertTrue("1".matches(regex));
    }

    @Test
    void number0_300FalseTest() {
        String regex = Strings.number0_300();
        assertFalse("00".matches(regex));
        assertFalse("301".matches(regex));
        assertFalse("01".matches(regex));
        assertFalse("00".matches(regex));
        assertFalse("1(".matches(regex));
        assertFalse("1000".matches(regex));
        assertFalse(" 20".matches(regex));
        assertFalse("1001".matches(regex));
    }

    @Test
    void ipv4OctetTrueTest() {
        String regex = Strings.ipv4Octet();
        assertTrue("0".matches(regex));
        assertTrue("00".matches(regex));
        assertTrue("000".matches(regex));
        assertTrue("10".matches(regex));
        assertTrue("100".matches(regex));
        assertTrue("255".matches(regex));
        assertTrue("199".matches(regex));
        assertTrue("249".matches(regex));
    }

    @Test
    void ipv4OctetFalseTest() {
        String regex = Strings.ipv4Octet();
        assertFalse("0000".matches(regex));
        assertFalse("t".matches(regex));
        assertFalse("-1".matches(regex));
        assertFalse("1111".matches(regex));
        assertFalse("0001".matches(regex));
        assertFalse("256".matches(regex));
        assertFalse("300".matches(regex));
        assertFalse("*".matches(regex));
        assertFalse("1 ".matches(regex));
    }

    @Test
    void ipV4AddressTrueTest() {
        
        String regex = Strings.ipV4Address();
        assertTrue("0.0.0.0".matches(regex));
        assertTrue("255.255.255.255".matches(regex));
    }

    @Test
    void ipV4AddressFalseTest() {
        
        String regex = Strings.ipV4Address();
        assertFalse("0.0.0".matches(regex));
        assertFalse("0.0.0+0".matches(regex));
        assertFalse("0".matches(regex));
        assertFalse("0.-".matches(regex));
        assertFalse("0.0.0*0".matches(regex));
        assertFalse("0.0.0 0".matches(regex));
    }

    @Test
    void stringWithJavaNamesTest() {
        String names = "123 1a _ abs int enum null lmn";
        String expected = "abs lmn";
        assertEquals(expected, Strings.stringWithJavaNames(names));
    }

    @Test
    void isArithmeticExpressionTrueTest() {
        String expr1 = "((a + x) / 5) + 15";
        assertTrue(isArithmeticExpression(expr1));
        String expr2 = "per / name + (10 + 2) * (10 + 5)";
        assertTrue(isArithmeticExpression(expr2));
        String expr3 = "7 - 4 + 8 * (tr + nm)";
        assertTrue(isArithmeticExpression(expr3));
        String expr4 = "(  a + (b * c )  ) - ( (   (   d) / e ) )";
        assertTrue(isArithmeticExpression(expr4));
        String expr5 = "((((a))))";
        assertTrue(isArithmeticExpression(expr5));
    }

    @Test
    void isArithmeticExpressionFalseTest() {
        String expr1 = "((a + x) / 5 + 15";
        assertFalse(isArithmeticExpression(expr1));
        String expr2 = "3 + double";
        assertFalse(isArithmeticExpression(expr2));
        String expr3 = "(a (+) b)";
        assertFalse(isArithmeticExpression(expr3));
        String expr4 = "per / name + )10 + 2( * (10 + 5)";
        assertFalse(isArithmeticExpression(expr4));
        String expr5 = "5 +- 6";
        assertFalse(isArithmeticExpression(expr5));
        String expr6 = "5 ++ 6";
        assertFalse(isArithmeticExpression(expr6));
        String expr7 = ")a + b(";
        assertFalse(isArithmeticExpression(expr7));
        String expr8 = "а b + 3";
        assertFalse(isArithmeticExpression(expr8));
        String expr9 = "7 - () + 4";
        assertFalse(isArithmeticExpression(expr9));
        String expr10 = "7 - (b)4";
        assertFalse(isArithmeticExpression(expr10));
        String expr11 = "";
        assertFalse(isArithmeticExpression(expr11));
        String expr12 = "    ";
        assertFalse(isArithmeticExpression(expr12));
        String expr13 = "7 - 4(b)";
        assertFalse(isArithmeticExpression(expr13));
    }
}
