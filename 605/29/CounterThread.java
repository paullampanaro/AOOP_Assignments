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
 * HW 9.2
 * This thread class extends the Java Thread class. It attempts to open each file path passed via constructor, then
 * reads each byte via a BufferedReader. Results are totalled within an int array, which can be accessed via get method.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */

import java.io.*;

public class CounterThread extends Thread {

    private int[] count;
    private String[] files;

    CounterThread(String[] files) {

        count = new int[10];
        this.files = files;
    }

    public void run() {

        for (String file : files) {

            // try with resources
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                int number = reader.read();

                // -1 indicates no further bytes, so continue until we have read the whole file
                while (number != -1) {

                    // if the byte read is not a number, skip it.
                    // we were not sure if this check affects runtime significantly, but figured it would be worth it
                    // it does not appear that hp's files have any non-digit characters other than the first period
                    if(number > 47 && number < 59) {
                        count[number - 48]++;
                    }
                    number = reader.read();
                }
            }
            // it is possible that the constructed file path might not exist. If so, inform the user.
            catch (IOException e) {
                System.out.println(file + " not found.");
            }
        }
    }

    public int[] getCount()  {
        return count;
    }
}
