public class IsoscelesTriangle extends Irregular{

    IsoscelesTriangle() { }

    IsoscelesTriangle(String name) {
        super(name);
    }

    IsoscelesTriangle(String name, double x, double y, double height, double width) {
        super(name, x, y, height, width);
    }

    public double area(){
        return height * width / 2;
    }

    public void testClass() {

        IsoscelesTriangle testTriangle = new IsoscelesTriangle("test", 1,1,2,4);
        if(testTriangle.area() != 4) {
            System.out.println("Area set incorrectly.");
        }
        if(testTriangle.center().getX() != 3 && testTriangle.center().getY() != 2) {
            System.out.println("Center calculated incorrectly.");
        }
        testTriangle.scale(2);
        if(testTriangle.getHeight() != 4 && testTriangle.getWidth() != 8) {
            System.out.println("Scaled incorrectly.");
        }
        System.out.println("Test complete.");
    }
}
