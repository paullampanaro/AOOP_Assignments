/*
 * CounterThread.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 12.1
 * This Thread class counts the digits of PI within a section of a text file, summing them within an int array.
 *
 * For HW 12, this thread stores the count within a class from the Java Collections framework, in this case, an
 * ArrayList. If the thread is instructed to run synchronized, it will store the count within the static storage class
 * in NumberCounter, otherwise it will store to an instanced storage class.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */
import java.io.*;
import java.util.ArrayList;

public class CounterThread extends Thread {

    private ArrayList<Integer> count; // class from Java Collections framework. This will be used un-synchronized.
    private File file;
    private int position; // character position to start within text file
    private int width; // how many characters to read
    private boolean sync; // if true, operate synchronized

    CounterThread(File file, int position, int width, boolean sync) {

        count = new ArrayList<>(10);
        // need to some extra initialization for ArrayList
        for(int index = 0; index < 10; index++) {
            count.add(0);
        }
        this.file = file;
        this.position = position;
        this.width = width;
        this.sync = sync;
    }

    /**
     * This run method attempts to open a BufferedReader within the text file. It skips forward to its assigned
     * position, then counts valid digits until it has read the specified number of characters or there are no further
     * characters to read.
     */
    public void run() {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            reader.skip(position); // skip forward to the correct position
            int iterations = 0; // we will keep track of characters read here
            int number = reader.read();

            while (number != -1 && iterations < width) {

                // must be a valid digit to be counted
                if(number > 47 && number < 59) {
                    // if sync, store to static storage and use synchronized block
                    if(sync) {
                        synchronized (NumberCounter.count) {
                            int total = NumberCounter.count.get(number-48);
                            NumberCounter.count.set(number-48,total+1);
                        }
                    }
                    else {
                        int temp = count.get(number-48);
                        count.set(number-48,temp+1);
                    }
                }
                number = reader.read();
                iterations++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This get method is used to retrieve this thread's digit count by the main NumberCounter class.
     * @return ArrayList containing digit count
     */
    public ArrayList<Integer> getCount()  {
        return count;
    }
}
