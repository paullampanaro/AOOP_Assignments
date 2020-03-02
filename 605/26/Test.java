/*
 * Test.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 5.1
 * This method is used to create and test the shapes for HW 5.1. It accepts command line arguments or user input.
 * No data validation is done, format must match [shape type] [name] [x] [y] [length/height] [width]* (if applicable)
 * HW 6.1
 * No changes to functionality, but now all methods added in HW 6.2 are tested.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */
import java.util.Scanner;

public class Test {

    final String regular = "Circle RegularDodecagon RegularHexagon Square";
    final String irregular = "Ellipse IsoscelesTriangle Kite Rectangle";

    // if no command line arguments present, use Scanner to accept argument
    public void HandleInput(String[] arguments) {

        String[] inputValues = arguments;

        if(inputValues.length == 0) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter <ShapeType> <name> <x> <y> <length/height> <width>* \n*if applicable:");
            inputValues = input.nextLine().split(" ");
            input.close();
        }

        System.out.println(inputValues[0]);

        // if the desired shape type is within the Regular category, call helper method makeRegular
        if (regular.contains(inputValues[0])) {
            makeRegular(inputValues[0], inputValues[1], Double.parseDouble(inputValues[2]),
                    Double.parseDouble(inputValues[3]), Double.parseDouble(inputValues[4]));
        }
        // otherwise call the helper method makeIrregular
        else  {
            makeIrregular(inputValues[0], inputValues[1], Double.parseDouble(inputValues[2]),
                    Double.parseDouble(inputValues[3]), Double.parseDouble(inputValues[4]),
                    Double.parseDouble(inputValues[5]));
        }
    }

    public void makeRegular(String type, String name, double x, double y, double length) {
        switch ( type ) {
            case "Circle":
                Circle aCircle = new Circle();
                aCircle.setXY(x, y);
                aCircle.setLength(length);
                aCircle.testClass();
                break;
            case "Square":
                Square aSquare = new Square();
                aSquare.setXY(x, y);
                aSquare.setLength(length);
                aSquare.testClass();
                break;
            case "RegularHexagon":
                RegularHexagon aRegularHexagon = new RegularHexagon();
                aRegularHexagon.setXY(x, y);
                aRegularHexagon.setLength(length);
                aRegularHexagon.testClass();
                break;
            case "RegularDodecagon":
                RegularDodecagon aRegularDodecagon = new RegularDodecagon();
                aRegularDodecagon.setXY(x, y);
                aRegularDodecagon.setLength(length);
                aRegularDodecagon.testClass();
                break;

        }

    }

    public void makeIrregular(String type, String name, double x, double y, double height, double width) {
        switch ( type ) {
            case "Ellipse":
                Ellipse aEllipse = new Ellipse();
                aEllipse.setXY(x, y);
                aEllipse.setSize(height, width);
                aEllipse.testClass();
                break;
            case "IsoscelesTriangle":
                IsoscelesTriangle aIsoscelesTriangle = new IsoscelesTriangle();
                aIsoscelesTriangle.setXY(x, y);
                aIsoscelesTriangle.setSize(height, width);
                aIsoscelesTriangle.testClass();
                break;
            case "Kite":
                Kite aKite = new Kite();
                aKite.setXY(x, y);
                aKite.setSize(height, width);
                aKite.testClass();
                break;
            case "Rectangle":
                Rectangle aRectangle = new Rectangle();
                aRectangle.setXY(x, y);
                aRectangle.setSize(height, width);
                aRectangle.testClass();
                break;
        }
    }

    public static void main(String[] args) {

        new Test().HandleInput(args);
    }
}

