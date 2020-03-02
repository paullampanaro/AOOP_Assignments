public class Kite extends Irregular{

    Kite() { }

    Kite(String name) {
        super(name);
    }

    Kite(String name, double x, double y, double height, double width) {
        super(name, x, y, height, width);
    }

    public double area(){
        return width * height / 2;
    }

    public void testClass() {

        Kite testKite = new Kite("test", 1,1,2,4);
        if(testKite.area() != 4) {
            System.out.println("Area set incorrectly.");
        }
        if(testKite.center().getX() != 3 && testKite.center().getY() != 2) {
            System.out.println("Center calculated incorrectly.");
        }
        testKite.scale(2);
        if(testKite.getHeight() != 4 && testKite.getWidth() != 8) {
            System.out.println("Scaled incorrectly.");
        }
        System.out.println("Test complete.");
    }
}
