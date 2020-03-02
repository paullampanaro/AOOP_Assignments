/*
 * NumberCounter.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 10.1
 * This program counts the number of digits of PI within a text file using multiple threads. The total sum of all
 * threads is printed after counting is completed. Several assumptions are made, as written in homework prompt:
 * The command line arguments are correct.
 *
 * See CounterThread.java for more information to our thread class.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */

import java.io.*;

public class NumberCounter  {

    int numThreads;
    String filePath;
    int[] count; // total count of all threads summed here after thread completion
    CounterThread[] threads;
    File file;
    int length; // length of PI file

    /**
     * This constructor takes the command line arguments and processes them to prepare for thread assignment. It will
     * terminate the program if arguments do not pass some basic validation checks.
     * @param args command line arguments
     */
    NumberCounter(String[] args) {

        count = new int[10];

        // here we process the command line arguments. If no number of threads is specified, default is 1
        if(args.length == 2) {

            try {
                numThreads = Integer.parseInt(args[0]);
                filePath = args[1];
            }
            catch (NumberFormatException e) {
                System.out.println("Incorrect thread argument.");
                System.exit(0);
            }
        }
        else if(args.length == 1) {
            numThreads = 1;
            filePath = args[0];
        }
        else {
            System.out.println("No arguments.");
            System.exit(0);
        }

        file = new File(filePath);
        // if the file does not exist, then we can assume the filepath argument is incorrect
        if(!file.exists()) {
            System.out.println("File not found.");
            System.exit(0);
        }

        // capture the file length here. This will be used to split work amongst threads
        length = (int)file.length();
        threads = new CounterThread[numThreads];
    }

    /**
     * This method calculates the amount of characters each thread needs to read, and where to begin within the file.
     * It then creates the desired number of threads with these parameters, and starts them.
     */
    public void assignThreads() {

        int perThread = length / numThreads;
        int leftover = length % numThreads; // the last thread will grab any leftover characters

        for(int index = 0; index < numThreads; index++) {

            // if this is the last thread, also add on leftover characters
            int width = index == numThreads - 1 ? perThread + leftover: perThread;

            threads[index] = new CounterThread(file, index * perThread, width);

            threads[index].start();
        }
    }

    /**
     * This method waits for each CounterThread to complete, then sums the digit count from each thread. The assumption
     * is made that the threads have already been started.
     */
    public void totalCount() {

        for(CounterThread thread: threads) {

            try {
                thread.join();
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(CounterThread thread : threads) {

            int[] fromThread = thread.getCount();
            for(int index = 0; index < 10; index++) {
                count[index] += fromThread[index];
            }
        }
    }

    /**
     * This helper method prints the result of the summed digit count stored within this class.
     */
    public void printCount() {

        for(int index = 0; index < 10; index++) {
            System.out.println(index + ": " + count[index]);
        }
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        NumberCounter counter = new NumberCounter(args);
        counter.assignThreads();
        counter.totalCount();
        counter.printCount();

        System.out.println("Runtime was " + (System.currentTimeMillis() - start) + " milliseconds.");
    }
}
