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

    void testClass() {
        super.testClass();
        System.out.println("Area of this ellipse is " + this.area());
    }
}
