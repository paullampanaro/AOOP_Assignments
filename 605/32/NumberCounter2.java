/*
 * NumberCounter2.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 12.2
 * This program counts the number of digits of PI within a text file using multiple threads. The total sum of all
 * threads is printed after counting is completed. Several assumptions are made, as written in homework prompt:
 * The command line arguments are correct.
 *
 * See CounterThread2.java for more information to our thread class.
 *
 * This version of PI Counter uses a custom Collection framework class, MyCollectionFramework. It uses also uses a
 * custom iterator. It has different behavior whether running synchronized or un-synchronized. In synchronized, all
 * threads will modify a static storage class. In un-synchronized, each will modify their own storage class.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class NumberCounter2 {

    int numThreads;
    String filePath;
    boolean sync; // this will determine whether threads use static storage or instanced storage

    static MyCollectionFrameWork count; // custom Collection framework class. This will be used in synchronized.
    CounterThread2[] threads;
    File file;
    int length; // length of PI file

    /**
     * This constructor takes the command line arguments and processes them to prepare for thread assignment. It will
     * terminate the program if arguments do not pass some basic validation checks.
     * @param args command line arguments
     */
    NumberCounter2(String[] args) {

        count = new MyCollectionFrameWork(10);
        numThreads = 1;
        filePath = "pi.txt"; // we can default to our test file
        sync = false;

        // simplified processing of command line arguments based on assumption that they are correct
        numThreads = args.length > 0 ? Integer.parseInt(args[0]) : numThreads;
        filePath = args.length > 1 ? args[1] : filePath;
        sync = args.length > 2 && args[2].equals("synchronized");

        file = new File(filePath);
        // if the file does not exist, then we can assume the filepath argument is incorrect
        if(!file.exists()) {
            System.out.println("File not found.");
            System.exit(0);
        }

        // capture the file length here. This will be used to split work amongst threads
        length = (int)file.length();
        threads = new CounterThread2[numThreads];
    }

    /**
     * This helper method returns the synchronized state of this program.
     * @return true if this program is running synchronized, false if not
     */
    public boolean isSync() {
        return sync;
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

            threads[index] = new CounterThread2(file, index * perThread, width, sync);

            threads[index].start();
        }
    }

    /**
     * This method waits for each CounterThread to complete.
     */
    public void joinCount() {

        for(CounterThread2 thread: threads) {

            try {
                thread.join();
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is used if program is running un-synchronized. It retrieves the total from each thread and prints
     * the total for each digit. It uses an Iterator based on the assumption that we are using any Collections class.
     */
    public void printTotal(){

        int[] total = new int[10];

        for(CounterThread2 thread : threads) {

            MyCollectionFrameWork fromThread = thread.getCount();
            Iterator<Integer> iterator = fromThread.iterator();
            int index = 0;
            while(iterator.hasNext()) {
                total[index] += iterator.next();
                index++;
            }
        }

        for(int index = 0; index < 10; index++) {
            System.out.println(index + ": " + total[index]);
        }
    }

    /**
     * This helper method prints the result of the summed digit count stored within the static storage of this class. It
     * uses an iterator based on the assumption that we are using any Collections class.
     */
    public void printCount() {

        Iterator<Integer> iterator = count.iterator();
        int index = 0;
        while(iterator.hasNext()) {
            System.out.println(index + ": " + iterator.next());
            index++;
        }
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        NumberCounter2 counter = new NumberCounter2(args);
        counter.assignThreads();
        counter.joinCount();
        // use appropriate method if program is running synchronized
        if (counter.isSync()) counter.printCount();
        else counter.printTotal();

        System.out.println("Runtime was " + (System.currentTimeMillis() - start) + " milliseconds.");
    }
}
