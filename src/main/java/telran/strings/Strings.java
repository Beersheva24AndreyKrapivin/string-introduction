package telran.strings;

public class Strings {

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
        return "(?=.{2,})(^[a-zA-Z_$][a-zA-Z0-9_$]*$)|(^[a-zA-Z]$)";
    }

}
