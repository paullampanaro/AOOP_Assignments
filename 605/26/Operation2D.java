import java.awt.geom.Point2D;

public interface Operation2D {

    Vector multiply(Vector aVector);
    Matrix multiply(Matrix aMatrix);
    Point2D center();
    void scale(double scaleFactor);
    void printPoints();
}
