/*
 * X.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 9.3
 * Comments explaining the possible outputs of this program.
 *
 * @author hpb
 */
public class X extends Thread	{

    private int info;
    static int theNumber = 0;
    int theLocalNumber = 0;

    public X (int info) {
        this.info = info;
    }

    public void run () {
        if ( info == 1 )	{
            theNumber = 3;
            theLocalNumber = theNumber;
        } else {
            theNumber = 1;
            theLocalNumber = theNumber;
        }
    }

    public static void main (String args []) {
        X aX1 = new X(1);
        X bX1 = new X(2);
        aX1.start();
        bX1.start();
        System.err.println(bX1.theLocalNumber);
        System.err.println(theNumber);
        System.err.println(aX1.theLocalNumber);
    }
}
/*
Output 1: 0 3 3
        Value of theLocalNumber for Bx1 is 0 before it can be changed ny bx1
        Before theNumber can be accessed ax1 runs and changes the value of theNumber to 3
        ThelocalNumbar is now changed to 3 by ax1
Output 2: 0 1 3
        Value of initial theLocalNumber for Bx1 is 0 before it can be changed ny bx1
        Value of theNumber is changed by bx1 before it can print its initial value and prints the new value
        ax1 changes the value of theLocalNumber to 3 and prints it.
Output 3:  1 1 3
        Value of theLocalNumber is changed by bx1 and prints it
        The new set value of theNumber is printed.
        ax1 changes the value of theLocalNumber to 3 and prints it.
Output 4:1 3 3
        Value of theLocalNumber is changed by bx1 and prints it
        Before theNumber can be accessed ax1 runs and changes the value of theNumber to 3
        ax1 changes the value of theLocalNumber to 3 and prints it.
Output 5:3 3 3
        Value of theLocakNumber is set up by ax1 before another thread can print it
        Before theNumber can be accessed ax1 runs and changes the value of theNumber to 3
        ax1 changes the value of theLocalNumber to 3 and prints it.
Output 6:0 0 3
        initial value of ThelocalNumber is printed
        initial value of Thenumber is printed
        ax1 changes the value of theLocalNumber to 3 and prints it
Output 7:0 0 0
        initial value of ThelocalNumber is printed
        initial value of Thenumber is printed
        initial value of ThelocalNumber is printed

*/
