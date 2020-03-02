/*
 * Deadlock.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 12.3 This class accepts user input in order to make multiple threads.The threads synchronize to Lock objects,
 * which wrap a boolean variable. The main objective of this class is to establish a deadlock between given number of
 * Threads such that different threads lock an object and are waiting for another object which is locked by some other
 * Thread. All threads are involved in the deadlock, as proven by the logic and thread dump.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */
public class DeadLock extends Thread {
    private int id;
    private final Lock left;
    private Lock right;
    private Boolean isLast;

    DeadLock(int id, Lock left, Lock right, Boolean isLast) {
        this.id = id;
        this.left = left;
        this.right = right;
        this.isLast = isLast;
    }

    public void run() {
        try {
            while (true) {
                synchronized (left) { // Object 1
                    System.out.println("Thread " + id + " has lock on left.");
                    /*
                        Changes lock to false. Conceptually could be "open" instead of "closed", but that does not
                        matter. As long as this Thread doesn't enter the next synchronized block before the next Thread
                        enters its first synchronized block
                     */
                    left.close();

                    // this while loop lets the next Thread (which shares the "right" lock with this thread) enter its
                    // first synchronized block
                    while(right.isOpen()) {
                        this.sleep(100); // This lets another thread enter object
                    }
                    // should not be able to enter this
                    synchronized (right) {
                        System.out.println("Thread " + id + " has lock on right.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int numberOfThreads = 5;    //creates default 5 Threads if no arguments are passed
        Lock first = new Lock(false); // modified to false for Deadlock
        Lock left = first;
        Lock right = new Lock(false);

        try {
            numberOfThreads = args.length > 0 ? Integer.parseInt(args[0]) : numberOfThreads;
        }
        catch(NumberFormatException e) {
            System.out.println("Invalid arguments.");
            System.exit(0);
        }
        for (int index = 1; index <= numberOfThreads; index ++){
            DeadLock deadLock = index!= numberOfThreads ?
                    new DeadLock(index,left, right,false) :
                    new DeadLock(index,left,first, true);
            left = right;
            right = new Lock(false);
            deadLock.start();
        }
    }
}
/*
THREAD DUMP :
        "Thread-0" #13 prio=5 os_prio=0 cpu=0.00ms elapsed=1.69s tid=0x0000020d66c51000 nid=0xf58 waiting for monitor entry  [0x000000d4594ff000]
        java.lang.Thread.State: BLOCKED (on object monitor)
        at DeadLock.run(DeadLock.java:26)
        - waiting to lock <0x00000000886a8a48> (a Lock)
        - locked <0x00000000886a8a38> (a Lock)


        "Thread-1" #14 prio=5 os_prio=0 cpu=15.63ms elapsed=1.69s tid=0x0000020d66c51800 nid=0x2738 waiting for monitor entry  [0x000000d4595fe000]
        java.lang.Thread.State: BLOCKED (on object monitor)
        at DeadLock.run(DeadLock.java:26)
        - waiting to lock <0x00000000886a8df0> (a Lock)
        - locked <0x00000000886a8a48> (a Lock)


        "Thread-2" #15 prio=5 os_prio=0 cpu=0.00ms elapsed=1.69s tid=0x0000020d66cd0000 nid=0x1ee4 waiting for monitor entry  [0x000000d4596ff000]
        java.lang.Thread.State: BLOCKED (on object monitor)
        at DeadLock.run(DeadLock.java:26)
        - waiting to lock <0x00000000886a9040> (a Lock)
        - locked <0x00000000886a8df0> (a Lock)


        "Thread-3" #16 prio=5 os_prio=0 cpu=0.00ms elapsed=1.69s tid=0x0000020d66cd1000 nid=0x93c waiting for monitor entry  [0x000000d4597fe000]
        java.lang.Thread.State: BLOCKED (on object monitor)
        at DeadLock.run(DeadLock.java:26)
        - waiting to lock <0x00000000886a9290> (a Lock)
        - locked <0x00000000886a9040> (a Lock)


        "Thread-4" #17 prio=5 os_prio=0 cpu=0.00ms elapsed=1.69s tid=0x0000020d66cd1800 nid=0x24f8 waiting for monitor entry  [0x000000d4598fe000]
        java.lang.Thread.State: BLOCKED (on object monitor)
        at DeadLock.run(DeadLock.java:26)
        - waiting to lock <0x00000000886a8a38> (a Lock)
        - locked <0x00000000886a9290> (a Lock)
*/
