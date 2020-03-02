/**
 * HW 4.3
 * Anuj Kulkarni
 * Paul Lampanaro
 */
public class Ex2 {
    int instanceVariable;
    static int classVariable;
    static int itIsMe;
    final int finalVariable = 3;
    volatile int volatileVariable;
    transient int transientVariable;


    public Ex2() {
        // finalVariable = 42; // why does this not compile?
        /*
            No,since the final integer finalVariable is already initialized.A final variable can only be
            initialized once.
        */
    }

    public Ex2(int aLocalVariable) {
        itIsMe = 1;
        Ex1 aEx1 = new Ex1(); // is this vailid? if yes why?
        // objects can be created outside main method if you do it as part of the declaration of an instance variable
        // uses the default constructor
        Ex2 aEx2 = new Ex2(); // is this vailid? if yes why?
        // Object has been created inside its own method instead of main method, being an instance type is allowed.
        // uses default constructor
    }

    public Ex2(int aLocalVariable, int itIsMe) {
        this.instanceMethod1(); // is this vailid? if yes why?
        //Yes it is valid since this keyword as part of constructor refers to a object
        // which calls the method instanceMethod1, but return is thrown out
        // this = new Ex2(); // is this vailid? if yes why?
        /*
            No this is not valid since this refers to a current object and can only be used to refer any member of
            current object within an instance method
        */
        itIsMe = aLocalVariable; // does not do anything useful, only assigns copy of argument because scope first
    }

    Ex1 instanceMethod1() {
        new Ex1(2, 3); // creates a new Ex1 object that is immediately thrown out
        return new Ex1(2, 3); // returns Ex1 variable pointed to this Ex1 object created
    }

    Ex2 instanceMethod2() {
        return new Ex2(2, 3); // returns an Ex2 variable pointed to this Ex2 object created
    }

    static void classMethod() {
        classVariable = 3; // changes the global static variable classVariable to 3
    }

    public static void main(String args[]) {
        Ex2 aEx2 = new Ex2(); // makes a new object in the heap
        Ex2 bEx2 = new Ex2(); // makes a new object in the heap
        Ex2 cEx2 = new Ex2(1); // makes 3 new objects in the heap, but 2 lose reference
        Ex2 dEx2 = new Ex2(1, 2); // makes a new object in the heap
        // how many objeccts do exist at this point in time
        //-> 6 objects are created so far, 2 are thrown out because they lose reference
        Ex1 aEx1 = aEx2.instanceMethod1(); // how many instance variable do exist at this point in time
        //-> 20 because each object created has 4 instance variables
        Ex2 eEx2 = aEx2.instanceMethod2(); // how many instance variable do exist at this point in time
        //-> 24, 4 more because of this object creation
        for (int index = 0; index < 10; index++) {
            eEx2 = new Ex2(index, index * index);
        }
        // how many instance variable do exist at this point in time
        //-> still 24, because eEx2 just gets assigned to new objects and the old ones lose reference
        // how many instance variable do exist at this point in time
    }
}
