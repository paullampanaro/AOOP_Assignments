/*
 * ExpressionLR.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * Code provided by instructor. Edits for HW 2.2 signified by documentation comments and corresponding author tags.
 *
 * @author 605 hpb
 */
import java.util.Vector;

public class ExpressionLR {

    public static void main (String args []) {
        Vector<String> aLine = new Vector<String>();

/*
	  for ( String arg: args ) {
	  }
		aLine.add(arg);
*/
        aLine.add("(");
        aLine.add("(");
        aLine.add("2");
        aLine.add("+");
        aLine.add("2");
        aLine.add(")");
        aLine.add("*");
        aLine.add("3");
        aLine.add("-");
        aLine.add("4");
        aLine.add(")");
        aLine.add("*");
        aLine.add("2");
        aLine.add("+");
        aLine.add("3");

        if ( aLine.size() > 0 )
            System.out.println(copyExpression(aLine) + " == " + sum(aLine) );
    }

    public static String copyExpression (Vector aLine) {
        String rValue = "";
        for ( int index = 0; index < aLine.size(); index ++ )   {
            rValue += aLine.elementAt(index) + " ";
        }
        return rValue;
    }

    /** recognizes sum: sum [{ ('+'|'-') product }];
     */
    public static double sum (Vector s) {
        double result = product(s);

        while (s.size() > 0 ) {
            char op = ((String)s.elementAt(0)).charAt(0);
            switch (op) {
                case '+':
                    s.remove(0);
                    result = result +  product(s);
                    continue;
                case '-':
                    s.remove(0);
                    result = result - product(s);
                    continue;
                default:
                    return result;
            }
        }
        return result;
    }

    /** recognizes product: term [{ ('*'|'%'|'/') term }];
     */
    public static double product (Vector s) {
        double result = term(s);

        while (s.size() > 0 ) {
            char op = ((String)s.elementAt(0)).charAt(0);
            switch (op) {
                case '*':
                    s.remove(0);
                    result = result * term(s);
                    continue;
                case '/':
                    s.remove(0);
                    result = result / term(s);
                    continue;
                case '%':
                    s.remove(0);
                    result = result % term(s);
                    continue;
                /**
                 * following case statement added for HW 2.2
                 *
                 * @author Paul Lampanaro
                 * @author Anuj Kulkarni
                 */
                case ')':
                    /*
                     * The closing parenthesis will exit an indirect recursive call started by '('
                     * Remove the closing parenthesis before returning the result.
                     */
                    s.remove(0);
                    return result;
                default:
                    return result;
            }
        }
        return result;
    }

    public static double term (Vector s) {
        double result;
        char op = ((String)s.elementAt(0)).charAt(0);

        switch (op) {
            /**
             * following case statement added for HW 2.2
             *
             * @author Paul Lampanaro
             * @author Anuj Kulkarni
             */
            case '(':
                /*
                 * If a opening parenthesis is found, first remove the parenthesis.
                 * Then begin a indirect recursive call starting at the top level, sum(vector s).
                 * The exit statement for this indirect recursive call will be found in product(vector s)
                 *
                 * This effectively replaces the opening and closing parenthesis with the calculated value within,
                 * allowing to continue to function as expected, by returning a value for product() or subsequently
                 * sum() to perform an operation on. This also supports multiple levels of parenthesis, by beginning
                 * indirect recursion each time an opening parenthesis is found.
                 */
                s.remove(0);
                result = sum(s);
                return result;
            default:
                result = Double.parseDouble((String)s.elementAt(0));
                s.remove(0);
        }
        return result;
    }

}
