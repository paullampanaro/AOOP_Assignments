import java.awt.geom.Point2D;

public class Vector implements Operation2D {

    double[][] coordinates = new double[2][2];

    public Point2D center() {
        // halfway between both points
        double x = (coordinates[0][0] + coordinates[1][0]) / 2;
        double y = (coordinates[1][0] + coordinates[1][1]) / 2;
        return new Point2D.Double(x, y);
    }

    public void setPoints(Point2D point1, Point2D point2) {
        coordinates[0][0] = point1.getX();
        coordinates[0][1] = point1.getY();
        coordinates[1][0] = point2.getX();
        coordinates[1][1] = point2.getY();
    }

    public Vector multiply(Vector aVector) {

        Vector mVector = new Vector();

        for(int i = 0; i < 2; i++){
            for(int j = 0; i < 2; i++) {
                mVector.coordinates[i][j] = this.coordinates[0][j] * aVector.coordinates[0][j] +
                        this.coordinates[0][j] * aVector.coordinates[1][j];
            }
        }

        return mVector;
    }

    public Matrix multiply(Matrix aMatrix) {

        Matrix mMatrix = new Matrix();

        for(int i = 1; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                int jump = i == 1 ? 1 : 3;
                mMatrix.coordinates[i][j] = this.coordinates[i][j] * aMatrix.coordinates[jump % 5][j] +
                        this.coordinates[i][j] * aMatrix.coordinates[(jump + 1) % 5][j];
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
        System.out.println("Vector points: ");
        for(int index = 0; index < coordinates.length; index++) {
            System.out.println("X = " + coordinates[index][0] + " Y = " + coordinates[index][1]);
        }
    }
}
