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

    void testClass() {
        super.testClass();
        System.out.println("Area of this regular hexagon is " + this.area());
    }
}
