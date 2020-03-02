/*
 * Producer.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 11.1
 * The Producer class runs a set number of times, as specified in the constructor. Because it is the first in the chain
 * of threads, each time the Producer cycles in the run method, it begins a chain of wait-notify that return back to
 * Producer.
 *
 * CP and Consumer follow this same model, but with a continuous while loop and less detail for output.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */
public class Producer extends Thread {

    private Lock lock1;
    private Lock lock3;
    private int cycles;

    Producer(Lock lock1, Lock lock3, int cycles) {
        this.lock1 = lock1;
        this.lock3 = lock3;
        this.cycles = cycles;
    }

    public void run() {

        try {
            for (int index = 0; index < cycles; index++) {
                /* This first synchronized block is used to prevent the thread from running out of order. It checks the
                   boolean contained within the previous Lock to see if it should run. If not, wait. It will be awoken
                   when the previous thread runs and calls notify.
                 */
                synchronized (lock3) {
                    if (!lock3.isOpen()) lock3.wait();
                    System.out.println("cycle: " + index);
                    System.out.println("    1. cd case");
                }
                /* This next synchronized block is used to wake up the following thread. It closes the boolean within
                   the previous Lock to prevent this thread from running multiple times out of order, or infinitely.
                   Then it opens the boolean in the following Lock and calls notify on it, in case the following thread
                   ran out of order and is waiting.
                 */
                synchronized (lock1) {
                    lock3.close();
                    lock1.open();
                    lock1.notify();
                }
            }
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
