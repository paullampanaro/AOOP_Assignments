import java.awt.geom.Point2D;

abstract public class Regular extends Shape {

    double length = 0;

    Regular() { }

    Regular(String name){
        super(name);
    }

    Regular(String name, double x, double y, double length){
        super(name, x, y);
        this.length = length;
    }

    public void setLength(double length){
        this.length = length;
    }

    public double getLength(){
        return length;
    }

    public Point2D center() {
        return new Point2D.Double(x + length / 2, y + length / 2);
    }

    public void scale(double scaleFactor) { this.length *= scaleFactor; }
}
