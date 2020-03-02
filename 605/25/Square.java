public class Square extends Regular {

    Square() { }

    Square(String name) {
        super(name);
    }

    Square(String name, double x, double y, double length) {
        super(name, x, y, length);
    }

    public double area(){
        return Math.pow(length, 2);
    }

    void testClass() {
        super.testClass();
        System.out.println("Area of this square is " + this.area());
    }
}
