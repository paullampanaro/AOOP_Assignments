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

    void testClass() {
        super.testClass();
        System.out.println("Area of this rectangle is " + this.area());
    }
}
