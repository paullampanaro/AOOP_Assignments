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
 * HW 10.1
 * This Thread class counts the digits of PI within a section of a text file, summing them within an int array.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */
import java.io.*;

public class CounterThread extends Thread {

    private int[] count; // digit count of this thread
    private File file;
    private int position; // character position to start within text file
    private int width; // how many characters to read

    CounterThread(File file, int position, int width) {

        count = new int[10];
        this.file = file;
        this.position = position;
        this.width = width;
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
                    count[number - 48]++;
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
     * @return int array containing digit count
     */
    public int[] getCount()  {
        return count;
    }
}
