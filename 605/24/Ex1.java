/**
 * HW 4.3
 * Anuj Kulkarni
 * Paul Lampanaro
 */
public class Ex1{
    int instanceVariable;
    static int classVariable;
    static int itIsMe;
    final int finalVariable;
    volatile int volatileVariable;
    transient int transientVariable;


    // default constructor
    public Ex1() {
        finalVariable = 42; // changes global instance variable instanceVariable to 42
    }

    // constructor with parameter
    public Ex1(int aLocalVariable) {

        itIsMe = 1; // change global static variable itIsMe to 1
        finalVariable = 43; // changes finalVariable to 43, if possible, otherwise throws error because it is final
    }

    // constructor with parameters
    public Ex1(int aLocalVariable, int itIsMe) {
        finalVariable = 43; // explain the error if this would be commented out
        /* If finalVariable is commented the final variable (finalVariable) will not be initialized and a
           final variable needs to be initialized at the declaration or constructor or the compiler throws an error
        */
        itIsMe = aLocalVariable;
        /*
            Since java is pass by value, the copy of the argument itIsMe is set to the value of aLocalVariable
         */
    }

    // instance method, always invoked in reference to particular object
    void instanceMethod() {
        instanceVariable = 22; // sets the global instance variable instanceVariable to 22
        classVariable = 33; // sets the global static variable classVariable to 33
    }

    // static method, is invoked without reference to a particular object
    static void classMethod() {
        classVariable = 3; // sets the global static variable classVariable to 3
    }

    public static void main(String args[] ) {
        Ex1 aEx1 = new Ex1(); // points aEx1 to a new Ex1 object
        Ex1 bEx1 = new Ex1(); // points bEx1 to a new Ex1 object
        Ex1 cEx1 = new Ex1(1); // points cEx1 to a new Ex1 object
        Ex1 dEx1 = new Ex1(1, 2); // // points dEx1 to a new Ex1 object
        aEx1.instanceMethod();
        bEx1.instanceMethod(); // modified
        //Will this compile?
        // if not
        // explain the compiler error in your own words
        // modify it so it does
        /*
           No,it wont compile since instanceMethod is not referred to any object
           Calling it for an object such as bEx1 will remove the compile error
        */
        bEx1.classMethod();
        // What are the values of all instance variables of aEx1 and bEx1
        //For aEx1=instanceVariable-> 22, finalVariable ->42
        //For bEx1=instanceVariable-> 22, finalVariable ->42
        // What is the value of aEx1.finalVariable?
        // -> 42
        // What is the value of aEx1.finalVariable?
        // -> 42

    }
}