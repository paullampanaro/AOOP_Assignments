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

    void testClass() {
        super.testClass();
        System.out.println("Area of this regular dodecagon is " + this.area());
    }
}
