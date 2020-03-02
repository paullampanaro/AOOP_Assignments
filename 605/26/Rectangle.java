public class Rectangle extends Irregular {

    Rectangle() { }

    Rectangle(String name) {
        super(name);
    }

    Rectangle(String name, double x, double y, double height, double width) {
        super(name, x, y, height, width);
    }

    public double area(){
        return width * height;
    }

    public void testClass() {

        Rectangle testRectangle = new Rectangle("test", 1,1,2,4);
        if(testRectangle.area() != 8) {
            System.out.println("Area set incorrectly.");
        }
        if(testRectangle.center().getX() != 3 && testRectangle.center().getY() != 2) {
            System.out.println("Center calculated incorrectly.");
        }
        testRectangle.scale(2);
        if(testRectangle.getHeight() != 4 && testRectangle.getWidth() != 8) {
            System.out.println("Scaled incorrectly.");
        }
        System.out.println("Test complete.");
    }
}
