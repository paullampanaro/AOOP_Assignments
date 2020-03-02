/*
 * Expression.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * Code provided by instructor. Following comment explains functionality of program for HW 2.1
 *
 * Paul Lampanaro
 * Anuj Kulkarni
 *
 * First each element of the expression is loaded into a vector array of strings. Method copyExpression() is used to
 * concatenate these values together in order to print the expression. The expression is examined by passing the vector
 * to sum(), which in turn passes the vector to product(), which passes the vector to term(). term() examines the first
 * string in the vector, at position 0, and stores the character in that string. Currently the switch statement within
 * term() always defaults to parsing the string at position 0, and returns the value as double. It also calls remove()
 * on the string at position 0, which remove the element and shifts subsequent elements to the left. product() then
 * begins to loop through the vector, examining the next string to see if any of the desired operators are found. If so,
 * it removes that string, attempting to perform the operation on the result and calling term() on what it assumes to
 * be the next number. If the desired operators are not found in product(), it breaks the loop by returning result to
 * sum(). sum() then attempts to perform a very similar process, but asks for product() to find the value of the next
 * number. Only if product is unable to find any relevant operators will sum() be allowed to perform addition or
 * multiplication. This ensures that multiplication, division, and modulus operations are performed first.
 *
 * The program relies on the assumption that the expression will follow the pattern of number -> operator -> number...
 * The core functionality of this program is explained as such: sum() loops through the vector, but always calls
 * product() on each element to make sure that the correct order of operation is followed. Each function removes the
 * element it is working on, shifting the remaining elements to the left. term() is called only when a number is
 * expected to be found in the expression (at the beginning, or following an operator).
 *
 * @author 605 hpb
 */
import java.util.Vector;

public class Expression {

    public static void main (String args []) {
        Vector<String> aLine = new Vector<String>();

/*
	  for ( String arg: args ) {
	  }
		aLine.add(arg);
*/
        aLine.add("2");
        aLine.add("+");
        aLine.add("2");
        aLine.add("*");
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
        if (s.size() == 0 )
            return result;
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
            default:
                result = Double.parseDouble((String)s.elementAt(0));
                s.remove(0);
        }
        return result;
    }

}
