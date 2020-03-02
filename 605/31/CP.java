public class CP extends Thread {

    private Lock lock1;
    private Lock lock2;

    CP(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    public void run() {

        try {
            while (true) {
                synchronized (lock1) {
                    if (!lock1.isOpen()) lock1.wait();
                    System.out.println("    2. add inlay");
                }
                synchronized (lock2) {
                    lock1.close();
                    lock2.open();
                    lock2.notify();
                }
            }
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
