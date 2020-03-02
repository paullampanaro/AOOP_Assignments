public class Irregular extends Shape {

    double height = 0;
    double width = 0;

    Irregular() { }

    Irregular(String name) {
        super(name);
    }

    Irregular(String name, double x, double y, double height, double width) {
        super(name, x, y);
        this.height = height;
        this.width = width;
    }

    public void setSize(double height, double width){
        this.height = height;
        this.width = width;
    }

    public double getHeight(){
        return height;
    }

    public double getWidth(){
        return width;
    }

    void testClass() {
        super.testClass();
        Irregular test = new Irregular("test", 3, 5, 4, 6);
        if(test.getHeight() != 4 || test.getHeight() != 6) {
            System.out.println("Constructor sets height and width incorrectly.");
        }

        test.setSize(1, 2);
        if(test.getHeight() != 1 || test.getHeight() != 2) {
            System.out.println("Set size method does not work correctly.");
        }

        System.out.println("Second test complete.");
    }
}
