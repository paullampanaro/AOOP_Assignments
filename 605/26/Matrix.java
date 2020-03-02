/*
 * Matrix.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
import java.awt.geom.Point2D;

/**
 * HW 5.2
 * This Matrix class uses a 5x2 matrix to store origin and each point of the quadrilateral object. It inherits from
 * Shape, but does not use the x or y variables, instead using the matrix.
 * Hw 6.2
 * Now implements Operations2D interface
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */

public class Matrix implements Operation2D{

    /*
       stores origin and four corners of quadrilateral object
       begins with origin and then moves clockwise from top right
       [origin][top right][bottom right][bottom left][top left]
    */
    double[][] coordinates = new double[5][2];
    String name;

    Matrix() { }

    Matrix(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
        moveXYbyDelta(-oldX, -oldY);

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

        double x = coordinates[0][0];
        double y = coordinates[0][1];

        // top left
        coordinates[1][0] = x - halfWidth;
        coordinates[1][1] = y + halfHeight;

        // bottom left
        coordinates[2][0] = x - halfWidth;
        coordinates[2][1] = y - halfHeight;

        // top right
        coordinates[3][0] = x + halfWidth;
        coordinates[3][1] = y + halfHeight;

        // bottom right
        coordinates[4][0] = x + halfWidth;
        coordinates[4][1] = y - halfHeight;
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

    public void setFourCorners(Point2D topLeft, Point2D topRight, Point2D bottomLeft, Point2D bottomRight) {

        // top left
        coordinates[1][0] = topLeft.getX();
        coordinates[1][1] = topLeft.getY();

        // bottom left
        coordinates[2][0] = bottomLeft.getX();
        coordinates[2][1] = bottomLeft.getY();

        // top right
        coordinates[3][0] = topRight.getX();
        coordinates[3][1] = topRight.getY();

        // bottom right
        coordinates[4][0] = bottomRight.getX();
        coordinates[4][1] = bottomRight.getY();
    }

    public Point2D center() {
        return new Point2D.Double(coordinates[0][0], coordinates[0][1]);
    }

    public Vector multiply(Vector aVector) {

        Vector mVector = new Vector();

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                int jump = i == 0 ? 1 : 3;
                mVector.coordinates[i][j] = aVector.coordinates[i][j] * this.coordinates[jump][j] +
                        aVector.coordinates[i][j] * this.coordinates[jump + 1][j];
            }
        }

        return mVector;
    }

    public Matrix multiply(Matrix aMatrix) {

        Matrix mMatrix = new Matrix();

        for(int i = 1; i < 5; i++) {
            for(int j = 0; j < 2; j++) {

                int jump = i < 2 ? 1 : 2;
                double coord = this.coordinates[i][j];
                coord = coord * aMatrix.coordinates[i][j] + coord * aMatrix.coordinates[(i + jump)%5][j];
                mMatrix.coordinates[i][j] = coord;
            }
        }

        return mMatrix;
    }

    public void scale(double scaleFactor) {

        for(int index = 0; index < coordinates.length; index++) {
            for(int XorY = 0; XorY < 2; XorY++) {
                coordinates[index][XorY] *= scaleFactor;
            }
        }
    }

    public void printPoints() {
        System.out.println("Matrix points: ");
        for(int index = 0; index < coordinates.length; index++) {
            System.out.println("X = " + coordinates[index][0] + " Y = " + coordinates[index][1]);
        }
    }

    /**
     * This methods checks all methods required by HW 5.2 and HW 6.2, and checks Vector class as well, to ensure
     * Interface Operations2D is implemented correctly.
     * @param args
     */
    public static void main(String[] args) {

        // check to make sure default constructor is not setting name
        Matrix testMatrix = new Matrix();
        System.out.println(testMatrix.getName());

        // check to make sure argument constructor is working correctly
        testMatrix = new Matrix("testMatrix");
        System.out.println(testMatrix.getName());
        System.out.println("X: " + testMatrix.getX() + ", Y: " + testMatrix.getY());

        // check to make sure setXY is working
        testMatrix.setXY(3, 5);
        System.out.println("X: " + testMatrix.getX() + ", Y: " + testMatrix.getY());

        // check to make sure moveXYbyDelta is working
        testMatrix.moveXYbyDelta(2, 3);
        System.out.println("X: " + testMatrix.getX() + ", Y: " + testMatrix.getY());

        // check to make sure height and width have not been set yet
        System.out.println("Height: " + testMatrix.getHeight() + ", Width: " + testMatrix.getWidth());

        testMatrix.setSize(5, 4);
        // check to make sure set size method is correct
        System.out.println("Height: " + testMatrix.getHeight() + ", Width: " + testMatrix.getWidth());

        testMatrix.setScale(2);
        // check to make sure set scale method is correct
        System.out.println("Height: " + testMatrix.getHeight() + ", Width: " + testMatrix.getWidth());

        // building points for setFourCornersMethod
        Point2D bottomRight = new Point2D.Double(0, 0);
        Point2D topRight = new Point2D.Double(0, 3);
        Point2D topLeft = new Point2D.Double(3, 0);
        Point2D bottomLeft = new Point2D.Double(3,3);

        // checking setFourCorners method
        testMatrix.setFourCorners(topLeft, topRight, bottomLeft, bottomRight);
        System.out.println("After setFourCorners: ");
        testMatrix.printPoints();

        // check for matrix * matrix multiply
        Matrix matrixXmatrix = testMatrix.multiply(testMatrix);
        System.out.println("Matrix times matrix: ");
        matrixXmatrix.printPoints();

        // check matrix scale method
        testMatrix.scale(2);
        System.out.println("Matrix scaled: ");
        testMatrix.printPoints();

        // check matrix center method
        Point2D matrixCenter = testMatrix.center();
        System.out.println("Matrix center is " + matrixCenter.getX() + ", " + matrixCenter.getY());

        // building vector for matrix * vector multiply
        Point2D firstPoint = new Point2D.Double(1, 5);
        Point2D secondPoint = new Point2D.Double(3,4);
        Vector testVector = new Vector();
        testVector.setPoints(firstPoint, secondPoint);
        testVector.printPoints();

        // check vector scale
        testVector.scale(2);
        System.out.println("Vector scaled: ");
        testVector.printPoints();

        // check vector center method
        Point2D vectorCenter = testVector.center();
        System.out.println("Vector center is " + vectorCenter.getX() + ", " + vectorCenter.getY());

        // check matrix * vector multiply
        Vector matrixXvector = testMatrix.multiply(testVector);
        System.out.println("Matrix times vector: ");
        matrixXvector.printPoints();

        // check vector * matrix multiply
        Matrix vectorXmatrix = testVector.multiply(testMatrix);
        System.out.println("Vector times matrix: ");
        vectorXmatrix.printPoints();

        // check  vector * vector multiply
        Vector vectorXvector = testVector.multiply(testVector);
        System.out.println("Vector times vector: ");
        vectorXvector.printPoints();
    }
}
