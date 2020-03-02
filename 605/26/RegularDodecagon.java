public class RegularDodecagon extends Regular {

    RegularDodecagon() { }

    RegularDodecagon(String name) {
        super(name);
    }

    RegularDodecagon(String name, double x, double y, double length) {
        super(name, x, y, length);
    }

    public double area(){
        double side = length / 2;
        return 3 * Math.pow(side, 2) * (2 + Math.sqrt(3));
    }

    public void testClass() {

        RegularDodecagon testDodecagon = new RegularDodecagon("test", 1,1,6);
        if(testDodecagon.area() != 27 * (2 + Math.sqrt(3))) {
            System.out.println("Area set incorrectly.");
        }
        if(testDodecagon.center().getX() != 4 && testDodecagon.center().getY() != 4) {
            System.out.println("Center calculated incorrectly.");
        }
        testDodecagon.scale(2);
        if(testDodecagon.getLength() != 8) {
            System.out.println("Scaled incorrectly.");
        }
        System.out.println("Test complete.");
    }
}
