/**
 * HW 4.3
 * Anuj Kulkarni
 * Paul Lampanaro
 */
public class Ex {

    String anOtherVariable;
    int[] aVariable = { 4, 2 }; //Creates int array aVariable and points to an int array in the heap

    /**
     * The parameter value is coped to a String variable pointed to the same String as the original. The copy is then
     * pointed to a new variable.
     * @param arg
     */
    public void test1(String arg ) {
        arg = "b";
    }

    /**
     * The parameter value is copied to an int array variable, and then the array that this copy is pointed to is
     * modified.
     *
     * @param aVariable
     */
    public void test2(int[] aVariable ) {
        aVariable[1] = 3; // changes value of position [1] of the int array in the heap to 3
    }

    public void test1() {
        anOtherVariable = "a"; // generates "a" in string pool and points anOtherVariable to it
        System.out.println("1. " + anOtherVariable); // output is "1. a"
        // Makes a copy of anOtherVariable, and points that copy to "b". anOtherVariable is still pointing to "a"
        test1(anOtherVariable);
        System.out.println("2. " + anOtherVariable); // output is "2. a"
    }

    public void test2() {
        System.out.println("3. " + aVariable[0] + ", " + aVariable[1]); // output will be "3. 4,2"
        test2(aVariable); // Copy of aVariable is made, then values of int array that copy is pointing are modified
        System.out.println("4. " + aVariable[0] + ", " + aVariable[1]); // output will be "4. 4,3
    }
    public static void main(String args[] ) {
        new Ex().test1();
        new Ex().test2();
    }
}

