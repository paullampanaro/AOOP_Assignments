/*
 * Shape.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 5.1
 * This is the parent class for Regular and Irregular. Regular is used for shapes that only need a length variable
 * to determine their area. Irregular is used for shapes that need height and width to determine their area.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */

public class Shape {

    String name;
    double x = 0;
    double y = 0;

    Shape(){ }

    Shape(String name){
        this.name = name;
    }

    Shape(String name, double x, double y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setXY(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void moveXYbyDelta(double x, double y){
        this.x += x;
        this.y += y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double area() {
        return 0;
    }

    /**
     * This method checks to make sure that default constructor does not set any instance variables, while the
     * argument constructor properly sets instance variables. It also tests the set methods, that are used to set X and
     * Y variables as well as name.
     */
    void testClass()	{
        Shape example = new Shape();
        String name = "name";
        if ( example.getName() != null )	{
            System.out.println("Default constructor should not set name");
        }
        if ( example.getX() != 0  || example.getY() != 0)	{
            System.out.println("Default constructor should not set instance variable");
        }
        example = new Shape(name);
        if ( ! example.getName().equals(name) )	{
            System.out.println("Constructor sets name incorrectly." );
        }

        String newName = "newName";
        example.setName(newName);
        if(! example.getName().equals(newName) ) {
            System.out.println("Set name method does not work correctly.");
        }

        example = new Shape(name, 3, 5);
        if(example.getX() != 3 || example.getY() != 5) {
            System.out.println("Constructor sets x and y incorrectly.");
        }

        example.setName("newName");
        if(! example.getName().equals("newName")) {
            System.out.println("Set name method does not work correctly.");
        }

        example.setXY(1, 2);
        if(example.getX() != 1 || example.getY() != 2) {
            System.out.println("Set X,Y method does not work correctly.");
        }

        System.out.println("Initial test is complete.");
    }
}
