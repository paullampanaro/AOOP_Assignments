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
 * HW 9.2
 * This program counts the number of digits of PI within multiple text files using threads. A total sum of all threads
 * is printed after counting is completed. Several assumptions are made, as written in homework prompt:
 * There are more files than threads.
 * The command line arguments are correct.
 * The files contained within the given directory do not have an extension.
 *
 * See CounterThread.java for more information to our thread class.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */

import java.io.File;
import java.util.Arrays;

public class NumberCounter {

    int numThreads; // number of threads to use
    String filePath; // given by command line argument
    String[] files; // array of paths to assign to threads. Built using filePath and numFiles
    int[] count; // total digit count totalled from all threads
    CounterThread[] threads; // array of CounterThreads
    int numFiles; // number of files expected within the directory

    NumberCounter(int numThreads, String filePath, int numFiles) {

        count = new int[10];
        this.numThreads = numThreads;
        this.filePath = filePath;
        this.numFiles = numFiles;
        threads = new CounterThread[numThreads];
        files = new String[numFiles];

        // here we build the expected files to be found within the directory.
        // the assumption is made that there will be no file extensions.
        for(int index = 0; index < numFiles; index++) {
            files[index] = filePath + "/" + (index + 1);
        }
    }

    /**
     * This method calculates how many files to assign to each thread, creates each thread and passes the filePath a
     * array, and starts each thread.
     */
    public void assignThreads() {

        // divide the files between the number of threads
        int perThread = numFiles / numThreads;
        int leftover = numFiles % numThreads; // there may be a leftover number of files. it is handled here

        for(int index = 0; index < numThreads; index++) {

            // the width variables controls how many files are given to the thread
            // if this is the last thread, also assign the leftover files from the modulus operation
            int width = index == numThreads - 1 ? perThread + leftover: perThread;

            // pass a section of the files to this thread
            threads[index] = new CounterThread(Arrays.copyOfRange(
                    files, index * perThread, index * perThread + width));

            threads[index].start();
        }
    }

    /**
     * This method waits for each thread to finish, then totals the result of each thread to the main count. It is
     * expected that this method be called after there threads have been created and started.
     */
    public void totalCount() {

        // wait for each thread to finish
        for(CounterThread thread: threads) {

            try {
                thread.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // total the result from each thread
        for(CounterThread thread : threads) {

            int[] fromThread = thread.getCount();
            for(int index = 0; index < 10; index++) {
                count[index] += fromThread[index];
            }
        }
    }

    /**
     * This method prints the result totalled from each thread, after they have been collected by totalCount()
     */
    public void printCount() {

        for(int index = 0; index < 10; index++) {
            System.out.println(index + ": " + count[index]);
        }
    }

    /**
     * The main method of this program processes the command line arguments, checks the given file path, then begins
     * thread creation process. It also prints the total runtime of the program.
     * @param args
     */
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        String filePath = null;
        int numThreads = 1;
        int numFiles = 48;

        // as instructed by homework prompt, we can assume the command line arguments are correct.
        if(args.length == 3) {
            try {
                numThreads = Integer.parseInt(args[0]);
                filePath = args[1];
                numFiles = Integer.parseInt(args[2]);
            }
            catch(Exception e) {
                System.out.println("Invalid arguments.");
                System.exit(0);
            }
        }
        // if there are not enough arguments, then user input was invalid.
        else {
            System.out.println("Invalid arguments.");
            System.exit(0);
        }

        // here we do some basic checks to make sure the file path is valid
        File check = new File(filePath);
        if(!check.isDirectory()) {
            System.out.println("Not a valid directory.");
            System.exit(0);
        }

        // begin the process
        NumberCounter counter = new NumberCounter(numThreads, filePath, numFiles);
        counter.assignThreads();
        counter.totalCount();
        counter.printCount();

        System.out.println("Runtime was " + (System.currentTimeMillis() - start) + " milliseconds.");
    }
}
