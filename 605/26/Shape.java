/*
 * Shape.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

import java.awt.geom.Point2D;

/**
 * HW 5.1
 * This is the parent class for Regular and Irregular. Regular is used for shapes that only need a length variable
 * to determine their area. Irregular is used for shapes that need height and width to determine their area.
 * HW 6.1
 * Now is abstract, along with Regular and Irregular
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */

abstract public class Shape {

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

    public abstract double area();

    public abstract Point2D center();

    public abstract void scale(double scaleFactor);

    public abstract void testClass();
}
