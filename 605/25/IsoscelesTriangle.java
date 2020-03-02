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

    void testClass() {
        super.testClass();
        System.out.println("Area of this isosceles triangle is " + this.area());
    }
}
