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

    public void testClass() {

        Circle testCircle = new Circle("test", 1,1,6);
        if(testCircle.area() != Math.PI * 6) {
            System.out.println("Area set incorrectly.");
        }
        if(testCircle.center().getX() != 4 && testCircle.center().getY() != 4) {
            System.out.println("Center calculated incorrectly.");
        }
        testCircle.scale(2);
        if(testCircle.getLength() != 12) {
            System.out.println("Scaled incorrectly.");
        }
        System.out.println("Test complete.");
    }
}
