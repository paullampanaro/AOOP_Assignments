/*
 * ProducerConsumer.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 10.2
 * This program creates a number of Producer and Consumer threads. Producer threads add a specified amount of items to
 * a shared storage, and Consumer threads consume an amount of items equal to its id from this shared storage. Detailed
 * explanation of Producers and Consumers can be found within each class.
 * Some assumptions were made:
 * The Producers and Consumers continue running until the program is terminated.
 * Command line is correct, although missing arguments will be set to a default value
 * If user enters value that will ensure deadlock, such as a too small storage size, this will be allowed.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */

import java.util.ArrayList;
import java.util.Scanner;

public class ProducerConsumer {

    // This ArrayList will be shared amongst threads, and access will be synchronized. For this purpose, it is declared
    // static and final, to ensure proper synchronization.
    final static ArrayList<Integer> storage = new ArrayList<>();
    static int storageSize = 1; // maximum units allowed in storage

    /**
     * This main method processes command line arguments and creates Consumers and Producers with the desired
     * specifications. After starting the threads, they will continue to run until the program is forcefully
     * terminated (as per assignment).
     * @param args command line arguments
     */
    public static void main(String[] args) {

        int soManyP = 1; // how many producers to create
        int soMuchToProduce = 1; // how many units each producer should make each cycle
        int soOftenToProduce = 1; // how often producers should cycle, in seconds
        int soManyC = 1; // how many consumers to create (they will consume units equal to their id each cycle)
        int soOftenToConsume = 1; // how often consumers should cycle, in seconds

        // process command line arguments
        soManyP          = args.length > 0 ? Integer.parseInt(args[0]) : soManyP;
        soMuchToProduce  = args.length > 1 ? Integer.parseInt(args[1]) : soMuchToProduce;
        soOftenToProduce = args.length > 2 ? Integer.parseInt(args[2]) : soOftenToProduce;
        soManyC          = args.length > 3 ? Integer.parseInt(args[3]) : soManyC;
        soOftenToConsume = args.length > 4 ? Integer.parseInt(args[4]) : soOftenToConsume;
        storageSize      = args.length > 5 ? Integer.parseInt(args[5]) : storageSize;

        // create and start Producers with unique id's
        for (int id = 1 ; id <= soManyP ; id ++)        {
            Producer aProducer = new Producer(id, soMuchToProduce, soOftenToProduce, storageSize, storage);
            aProducer.start();
        }

        // create and start Consumers with unique id's
        for (int id = 1 ; id <= soManyC ; id ++)        {
            Consumer aConsumer = new Consumer(id, soOftenToConsume, storage);
            aConsumer.start();
        }

        /*
        You can press enter to cancel this program. Why was this added?
        The assignment was unclear as to whether each Producer should run once, or run forever. We made the reasonable
        assumption that the intent of the Producer (and Consumer) is to run forever. Please understand that this was
        the most logical assumption available based on the wording of the assignment, and hp's comments.
         */
        Scanner cancel = new Scanner(System.in);
        cancel.nextLine();
        System.exit(0);
    }

    /**
     * This method checks to see if too many items have been added to storage. If so, it notifies user and exits the
     * program. This method can be called by other classes.
     */
    public static void test() {
        if(storage.size() > storageSize) {
            System.out.println("Overflow.");
            System.exit(0);
        }
    }
}
