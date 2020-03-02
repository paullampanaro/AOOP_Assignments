/**
 * HW 5.3
 * Output and explanations at bottom.
 *
 * Paul Lampanaro
 * Anuj Kulkarni
 */
public class AAA extends AA {

    public int instanceV = 3;

    public void set(int value)	{ instanceV = value; 		}
    public int get() 		{ return instanceV;		}
    public String toString()	{ return "AAA: " + instanceV; 	}

    public void print(int counter, String text)	{
        System.out.println(counter + ":	" + text);
    }
    public void go()	{
        int counter = 1;

        AAA aAAA = new AAA();
        AA aAA = new AA();
        A aA = new A();
        print(counter++, aAAA + " "); // 1
        print(counter++, aAA + " "); // 2
        print(counter++, aA + " "); // 3
        Object aObject = (Object)aAAA;
        print(counter++, aObject + " "); // 4
        aA.set(11);
        print(counter++, aObject + " "); // 5
        aA = (A)aAAA;
        print(counter++, aA + " "); // 6
        aA.instanceV = 11;
        print(counter++, aA.instanceV + " "); // 7
        print(counter++, aA + " "); // 8
        aA = (A)aAA;
        print(counter++, aA + " "); // 9
        aAA.instanceV = 22;
        print(counter++, aA.instanceV + " "); // 10
        print(counter++, aA + " "); // 11
        // aA = new Object();
        /* You cannot initialize an class to a variable of a different type without some sort of casting.
         */
        print(counter++, aA.instanceV + " "); // 12
        print(counter++, aA + " "); // 13

    }
    public static void main(String args[]) {
        new AAA().go();
    }
}
/*
OUTPUT :
        1:	AAA: 3
        aAAA is an object of class AAA for which the instanceVariable is set to 3

        2:	AA: 2
        aAA is an object of class AA for which the instanceVariable is set to 2

        3:	A: 1
        aA is an object of class A for which the instanceVariable is set to 1

        4:	AAA: 3
        aObject is class-casted to Object with reference to object aAAA where the value of
        instanceVariable is 3, using the toString method lowest in the hierarchy.

        5:	AAA: 3
        aObject is class-casted to Object with reference to object aAAA, so changing aAA should not affect aObject,
        same as line 4

        6:	AAA: 3
        object aA points to  Object aAAA and uses toString at lowest hierarcy, which uses instance variable of aAAA,
        which is 3

        7:	11
        instanceVariable of object aA is set to 11. Accessing the instance variable this way retrieves the expected
        result, as opposed to using toString method

        8:	AAA: 3
        object aA points to  Object aAAA and uses toString at lowest hierarcy, which uses instance variable of aAAA,
        which is 3

        9:	AA: 2
        object aA is now pointing to object aAA and uses toString at lowest heirarchy, where the value of
        instanceVariable is 2

        10:	1
        accessing the instance variable of aA this way returns expected result, the instance variable of aA which is 1

        11:	AA: 22
        instanceVariable of aAA was set to 22. object aA  uses lowest toString in hierarchy, of object aAA where the
        value of instanceVariable is now set to 22

        12:	1
        accessing the instance variable of aA this way returns expected result, the instance variable of aA which is 1

        13:	AA: 22
        instanceVariable of aAA was set to 22. object aA  uses lowest toString in hierarchy, of object aAA where the
        value of instanceVariable is now set to 22

*/
