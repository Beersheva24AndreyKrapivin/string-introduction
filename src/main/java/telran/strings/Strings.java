package telran.strings;

import java.util.Arrays;

public class Strings {

    static final String keyWords[] = { "abstract", "assert", "boolean",
                "break", "byte", "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else", "enum", "extends", "false",
                "final", "finally", "float", "for", "goto", "if", "implements",
                "import", "instanceof", "int", "interface", "long", "native",
                "new", "null", "package", "private", "protected", "public",
                "return", "short", "static", "strictfp", "super", "switch",
                "synchronized", "this", "throw", "throws", "transient", "true",
                "try", "void", "volatile", "while" };

    public static String firstName() {
        // regex for strings starting with capitallet letter and
        // rest as lowercase letter
        // minimal length is 5 letters
        return "[A-Z][a-z]{4, }";
    }

    /**
     * 
     * @return regex Java veriable names: A-Z, a-z 0-9, and _ (underscore) and $ (dollar sign),
     * first character must not be a digit
     * If a string consists of one character, it cannot be a number, _ or $
     */
    public static String javaVariable() {
        // regular expression for testing syntax of Java veriable names
        // only ASCII symbols are allowed
        return "([a-zA-Z_$][a-zA-Z0-9_$]+)|([a-zA-Z$])";
    }

    public static String number0_300() { 
        return "[1-9]\\d?|[1-2]\\d\\d|300|0";
    }

    public static String ipv4Octet() {
        return "([0-1]?\\d{1,2}|2([0-4]\\d|5[0-5]))";
    }

    public static String ipV4Address() {
        String octetExpr = ipv4Octet();
        return String.format("%s(\\.%s){3}", octetExpr, octetExpr);
    }

    public static String stringWithJavaNames(String names) {
        String [] tokens = names.split("\\s+");
        int i = getJavaNameIndex(tokens, -1);
        String res = "";
        if (i >= 0) {
            res = tokens[i];
        }
        while ((i = getJavaNameIndex(tokens, i)) > 0) {
            res += " " + tokens[i];
        }
        return res;
    }

    private static int getJavaNameIndex(String[] tokens, int i) {
        i++;
        while (i < tokens.length && !isJavaName(tokens[i])) {
            i++;    
        }
        return i < tokens.length ? i : -1;
    }

    private static boolean isJavaName(String string) {
        return string.matches(javaVariable()) && Arrays.binarySearch(keyWords, string) < 0;
    }

    public static boolean isArithmeticExpression(String expr) {
        //1. brackets 
        //right position of open / close bracket is matter of regex
        //matching between open and close bracket is matter of the method you are supporsed to write
        //based on a counter. If counter is negative - no matching; if at ending up going through a string
        //matching may be only in one case: at the  ending up of going the counter will be 0
        //Operator - regular expression for one out of 4 artithmetic operators [*/+-]
        //Operand may be either Java variable name or number (better any)

        boolean res = true;
        expr = expr.replaceAll("\\s+", "");

        if (!isEqualNumbersOfBrakets(expr) || expr.length() == 0) {
            res = false;
        } else {
            String[] arrayOperation = expr.split("[\\-\\+\\/\\*]");
            int i = 0;
            while (i < arrayOperation.length && res) {
                  if (arrayOperation[i].matches("[\\)].*||.*[\\($]||.*[a-zA-Z0-9_$][\\\\(].*||.*[\\\\)][a-zA-Z0-9_$].*")) {
                    res = false;    
                } else {
                    String stringWihtoutBrakets = arrayOperation[i].replaceAll("[\\(\\)]+", "");
                    if (!isJavaName(stringWihtoutBrakets) && !stringWihtoutBrakets.matches("\\d+")) {
                        res = false;
                    }
                }
                i++;
            }
        } 

        return res;
    }

    private static boolean isEqualNumbersOfBrakets(String expr) {
        return (expr.length() - expr.replaceAll("[\\(]+", "").length()) 
            == (expr.length() - expr.replaceAll("[\\)]+", "").length());    
    }

}
