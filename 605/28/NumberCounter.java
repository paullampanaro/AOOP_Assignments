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
 * HW 8.1
 * The program counts the occurrences of each digit (0-9) in a given length of PI. The assumption is made that the file
 * is not corrupted and contains only the digits of PI.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */
import java.io.*;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class NumberCounter {

    public static void main(String[] args) {

        String fileName;
        int[] count = new int[10]; // for a billion digits of pi, int should be enough to store without overflow

        // if there is no command line argument, accept user input
        if(args.length == 0) {
            try(Scanner userInput = new Scanner(System.in)) {
                System.out.print("File name: ");
                fileName = userInput.nextLine();
            }
        }
        else { fileName = args[0]; }

        // we will do a very simple check to see if the file is compressed
        boolean isCompressed = fileName.contains(".gz");

        double startTime = System.currentTimeMillis();

        // this "try with resources" creates a BufferedReader with a different instantiation depending on if the file
        // is compressed or not
        try(BufferedReader reader = isCompressed ?
                new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(fileName)))) :
                new BufferedReader(new FileReader(fileName))) {
            count[3]++; // we know the first digit is 3
            reader.skip(2); // having already accounted for the 3, we can discard "3." of PI
            int number = reader.read();
            while(number != -1) {
                // since 0 is 48 in int/byte form, subtracting this from our read value will provide the correct
                // position within our counter array
                count[number-48]++;
                number = reader.read();
            }
            // this loop prints our result
            for(int index = 0; index < 10; index++) {
                System.out.println(index + ": " + count[index]);
            }
        }
        // in case of an error opening the file, exit the program gracefully
        catch(IOException e) {
            System.out.println("File not found.");
        }

        double endTime = System.currentTimeMillis();
        System.out.println("Runtime was " + (endTime - startTime) + " milliseconds.");
    }
}
