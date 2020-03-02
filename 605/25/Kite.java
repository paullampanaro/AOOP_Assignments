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

    void testClass() {
        super.testClass();
        System.out.println("Area of this kite is " + this.area());
    }
}
