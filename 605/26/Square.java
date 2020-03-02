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

    public void testClass() {

        Square testSquare = new Square("test", 1,1,6);
        if(testSquare.area() != 36) {
            System.out.println("Area set incorrectly.");
        }
        if(testSquare.center().getX() != 4 && testSquare.center().getY() != 4) {
            System.out.println("Center calculated incorrectly.");
        }
        testSquare.scale(2);
        if(testSquare.getLength() != 8) {
            System.out.println("Scaled incorrectly.");
        }
        System.out.println("Test complete.");
    }
}
