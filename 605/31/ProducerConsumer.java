/*
 * ProducerConsumer.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

import java.util.Scanner;

/**
 * HW 11.1
 * This program simulates a producer, consumer-producer, consumer problem. This class accepts a command line argument,
 * the number of cycles to run in this loop. Default is 1. Each type of thread must wait for the previous to finish
 * production. This configuration is achieved with a custom Lock class, which simply wraps a boolean. The threads
 * are synchronized to a Lock object between each thread, and the boolean indicates that the successive thread is
 * allowed to run. If the thread is not allowed to run, it waits until the previous thread opens the Lock and calls
 * notify.
 *
 * Please note that due to the specifications of the assignment, some threads will not terminate and instead wait
 * once all cycles are complete.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */
public class ProducerConsumer {

    // there must be a lock between each thread
    static final Lock lock1 = new Lock(false); // this connects Producer and CP
    static final Lock lock2 = new Lock(false); // this connects CP and Consumer
    static final Lock lock3 = new Lock(true); // this connects Consumer and Producer

    public static void main(String[] args) {

        int cycles = 1;
        try {
            cycles = args.length > 0 ? Integer.parseInt(args[0]) : cycles;
        }
        catch(NumberFormatException e) {
            System.out.println("Invalid arguments.");
            System.exit(0);
        }

        new Producer(lock1, lock3, cycles).start();
        new CP(lock1, lock2).start();
        new Consumer(lock2, lock3).start();

        // this is added for user convenience. Threads will continue to wait after cycles are complete.
        Scanner input = new Scanner(System.in);
        input.nextLine();
        System.exit(0);
    }
}
