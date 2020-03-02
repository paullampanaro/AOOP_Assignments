public class RegularHexagon extends Regular {

    RegularHexagon() { }

    RegularHexagon(String name) {
        super(name);
    }

    RegularHexagon(String name, double x, double y, double length) {
        super(name, x, y , length);
    }

    public double area(){
        double side = length / 2;
        return 3 * Math.sqrt(3) / 2 * Math.pow(side, 2);
    }

    public void testClass() {

        RegularHexagon testHexagon = new RegularHexagon("test", 1,1,6);
        if(testHexagon.area() != 27 * Math.sqrt(3) / 2) {
            System.out.println("Area set incorrectly.");
        }
        if(testHexagon.center().getX() != 4 && testHexagon.center().getY() != 4) {
            System.out.println("Center calculated incorrectly.");
        }
        testHexagon.scale(2);
        if(testHexagon.getLength() != 8) {
            System.out.println("Scaled incorrectly.");
        }
        System.out.println("Test complete.");
    }
}
