public class Regular extends Shape {

    double length = 0;

    Regular() { }

    Regular(String name){
        super(name);
    }

    Regular(String name, double x, double y, double length){
        super(name, x, y);
        this.length = length;
    }

    public void setLength(double length){
        this.length = length;
    }

    public double getLength(){
        return length;
    }

    void testClass(){
        super.testClass();
        Regular test = new Regular("test", 1, 2, 4);
        if(test.getLength() != 4) {
            System.out.println("Constructor sets length incorrectly.");
        }

        test.setLength(5);
        if(test.getLength() != 5) {
            System.out.println("Set length method does not work correctly.");
        }

        System.out.println("Second test is complete.");
    }
}
