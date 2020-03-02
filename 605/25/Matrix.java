/*
 * Matrix.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 5.2
 * This Matrix class uses a 5x2 matrix to store origin and each point of the quadrilateral object. It inherits from
 * Shape, but does not use the x or y variables, instead using the matrix.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */

public class Matrix extends Shape {

    /*
       stores origin and four corners of quadrilateral object
       begins with origin and then moves clockwise from top right
       [origin][top right][bottom right][bottom left][top left]
    */
    double[][] coordinates = new double[5][2];

    Matrix() { }

    Matrix(String name) {
        super(name);
    }

    public void setXY(double x, double y) {

        double oldX = coordinates[0][0];
        double oldY = coordinates[0][1];

        // use moveXYbyDelta method because we need to move the corners as well
        moveXYbyDelta(x - oldX, y - oldY);
    }

    public void moveXYbyDelta(double x, double y) {

        // adjust each coordinate by the same amount
        for(int index = 0; index < 5; index++) {
            coordinates[index][0] += x;
            coordinates[index][1] += y;
        }
    }

    public double getX() {
        return coordinates[0][0];
    }

    public double getY() {
        return coordinates[0][1];
    }

    public void setScale(double scale) {

        // store current origin
        double oldX = coordinates[0][0];
        double oldY = coordinates[0][1];

        // set origin of shape to 0,0
        moveXYbyDelta(-x, -y);

        // move to center before scaling
        for(int index = 0; index < 5; index++) {
            coordinates[index][0] *= scale;
            coordinates[index][1] *= scale;
        }

        // return to original position
        moveXYbyDelta(oldX, oldY);
    }

    /**
     * This method assumes that the object is a rectangle. We were not sure what other assumption to make as the HW
     * prompt does not specify a way to adjust the individual corners to make other shapes.
     *
     * @param height
     * @param width
     */
    public void setSize(double height, double width) {

        double halfHeight = height / 2;
        double halfWidth = width / 2;

        x = coordinates[0][0];
        y = coordinates[0][1];

        // top right
        coordinates[1][0] = x + halfWidth;
        coordinates[1][1] = y + halfHeight;

        // bottom right
        coordinates[2][0] = x + halfWidth;
        coordinates[2][1] = y - halfHeight;

        // bottom left
        coordinates[3][0] = x - halfWidth;
        coordinates[3][1] = y - halfHeight;

        // top left
        coordinates[4][0] = x - halfWidth;
        coordinates[4][1] = y + halfHeight;
    }

    public double getHeight() {

        // determine greatest distance between x points
        double lowX = coordinates[0][0];
        double highX = coordinates[0][0];

        for(int index = 1; index < 5; index++) {
            if(coordinates[index][0] < lowX) {
                lowX = coordinates[index][0];
            }
            if(coordinates[index][0] > highX) {
                highX = coordinates[index][0];
            }
        }

        // return the difference
        return highX - lowX;
    }

    public double getWidth() {

        // determine greatest distance between y points
        double lowY = coordinates[0][1];
        double highY = coordinates[0][1];

        for(int index = 1; index < 5; index++) {
            if(coordinates[index][1] < lowY) {
                lowY = coordinates[index][1];
            }
            if(coordinates[index][1] > highY) {
                highY = coordinates[index][1];
            }
        }

        // return the difference
        return highY - lowY;
    }

    public static void main(String[] args) {

        Matrix test = new Matrix();
        System.out.println(test.getName()); // check to make sure default constructor is not setting name

        test = new Matrix("testMatrix");
        System.out.println(test.getName()); // check to make sure argument constructor is setting name
        System.out.println("X: " + test.getX() + ", Y: " + test.getY()); // make sure x and y have not been set yet

        test.setXY(3, 5);
        System.out.println("X: " + test.getX() + ", Y: " + test.getY()); // check to make sure x and y are set

        test.moveXYbyDelta(2, 3);
        System.out.println("X: " + test.getX() + ", Y: " + test.getY()); // check to make sure x and y are moved

        // check to make sure height and width have not been set yet
        System.out.println("Height: " + test.getHeight() + ", Width: " + test.getWidth());

        test.setSize(5, 4);
        // check to make sure set size method is correct
        System.out.println("Height: " + test.getHeight() + ", Width: " + test.getWidth());

        test.setScale(2);
        // check to make sure set scale method is correct
        System.out.println("Height: " + test.getHeight() + ", Width: " + test.getWidth());
    }
}
