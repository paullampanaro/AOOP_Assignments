public class Ellipse extends Irregular{

    Ellipse() { }

    Ellipse(String name) {
        super(name);
    }

    Ellipse(String name, double x, double y, double height, double width) {
        super(name, x, y, height, width);
    }

    public double area(){
        return (height / 2) * (width / 2) * Math.PI;
    }

    public void testClass() {

        Ellipse testEllipse = new Ellipse("test", 1,1,2,4);
        if(testEllipse.area() != 2 * Math.PI) {
            System.out.println("Area set incorrectly.");
        }
        if(testEllipse.center().getX() != 3 && testEllipse.center().getY() != 2) {
            System.out.println("Center calculated incorrectly.");
        }
        testEllipse.scale(2);
        if(testEllipse.getHeight() != 4 && testEllipse.getWidth() != 8) {
            System.out.println("Scaled incorrectly.");
        }
        System.out.println("Test complete.");
    }
}
