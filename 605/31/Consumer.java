
public class Consumer extends Thread{

    private Lock lock2;
    private Lock lock3;

    Consumer(Lock lock2, Lock lock3) {
        this.lock2 = lock2;
        this.lock3 = lock3;
    }

    public void run() {

        try {
            while (true) {
                synchronized (lock2) {
                    if (!lock2.isOpen()) lock2.wait();
                    System.out.println("    3. wrapped case");
                }
                synchronized (lock3) {
                    lock2.close();
                    lock3.open();
                    lock3.notify();
                }
            }
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
