/*
 * Firefighter.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 11.1
 * The Firefighter class runs a set number of times, as specified by the number of buckets. When the Firefighter thread
 * runs, it checks to see if it should run, then closes that boolean to prevent from running multiple times out of
 * order. It prints, then open the next Lock, and notifies the other thread on that Lock in case it is asleep.
 *
 * There is some additional checks in case the Firefighter is first or last in the chain, for output.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */
public class Firefighter extends Thread {

    private int id;
    private int cycles; // this is the number of buckets
    private Lock left; // synchronization object for previous Firefighter
    private Lock right; // synchronization object for next Firefighter
    private boolean isLast;

    Firefighter(int id, Lock left, Lock right, int cycles, boolean isLast) {
        this.id = id;
        this.cycles = cycles;
        this.left = left;
        this.right = right;
        this.isLast = isLast;
    }

    public void run() {

        try {
            for(int index = 1; index <= cycles; index++) {
                /* This first synchronized block is used to prevent the thread from running out of order. It checks the
                   boolean contained within the previous Lock to see if it should run. If not, wait. It will be awoken
                   when the previous thread runs and calls notify.
                 */
                synchronized (left) {
                    if (!left.isOpen()) left.wait();
                    if(id == 1)
                        System.out.println("New bucket (bucket# = " + index + ") is handed to firefighter " + id);
                    else
                        System.out.println("   bucket " + index + " is handed to firefighter " + id);
                    if(isLast) System.out.println("   and is emptied out.");
                }
                /* This next synchronized block is used to wake up the following thread. It closes the boolean within
                   the previous Lock to prevent this thread from running multiple times out of order, or infinitely.
                   Then it opens the boolean in the following Lock and calls notify on it, in case the following thread
                   ran out of order and is waiting.
                 */
                synchronized (right) {
                    left.close();
                    right.open();
                    right.notify();
                }
            }
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
