public class Circle extends Regular {

    Circle() { }

    Circle(String name){
        super(name);
    }

    Circle(String name, double x, double y, double length) {
        super(name, x, y, length);
    }

    public double area(){
        return Math.PI * length;
    }

    void testClass() {
        super.testClass();
        System.out.println("Area of this circle is " + this.area());
    }
}
