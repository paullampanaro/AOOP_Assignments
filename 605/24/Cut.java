/*
 * Cut.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * This program accepts a text file or user input and removes sections of the text based on command line arguments.
 * If no fields argument is present, then only the first field will be returned.
 * Fields argument is inclusive, both ends
 * If no delimiter argument is present, then the delimiter will be ","
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Cut {

    // these variables will store command line arguments if present
    private String fieldsArg;
    private String delimiterArg;
    private String fileArg;

    // these will hold the parsed field variables, otherwise will be set 1,1 inclusive
    private int firstField;
    private int lastField;
    private String delimiter;

    public static void main(String[] args){

        Cut cutter = new Cut();

        // store relevant command line arguments for parsing
        for(String s: args) {
            if(s.contains("-f")) {
                cutter.fieldsArg = s;
            }
            if(s.contains("-d")) {
                cutter.delimiterArg = s;
            }
            if(s.contains(".txt")) {
                cutter.fileArg = s;
            }
        }

        // once command line is captured, begin program
        cutter.applyChanges();
    }

    /**
     * This method analyzes the provided arguments, assigns default values if needed, then opens File and Scanner
     * objects needed to parse text.
     */
    private void applyChanges() {

        // first determine the fields
        if(fieldsArg == null) {
            // if no field argument, only grab fields 1 to 1 inclusive, i.e. the first field
            firstField = 1;
            lastField = 1;
        }
        else {
            // we can skip the first 2 characters of the string, which will be "-f"
            Scanner fieldScanner = new Scanner(fieldsArg.substring(2));
            fieldScanner.useDelimiter(",");
            firstField = fieldScanner.nextInt();
            lastField = fieldScanner.nextInt();
            fieldScanner.close();
        }

        // then determine the delimiter
        if(delimiterArg == null) {
            // if no delimiter specified, then default will be "," the comma
            delimiter = ",";
        }
        else {
            // we can skip the first 2 characters of the string, which will be "-d"
            delimiter = delimiterArg.substring(2);
        }

        // then determine whether to use text file or user input
        if(fileArg == null) {
            // if no file input, then read from user input
            Scanner userInput = new Scanner(System.in);
            System.out.println("Enter text to be cut: ");
            String cutString = userInput.nextLine();
            Scanner cutScanner = new Scanner(cutString);

            // this method loops through the text using the established arguments
            loopPrint(cutScanner);

            userInput.close();
            cutScanner.close();
        }
        else {
            // if the file argument is provided, build a File and Scanner, then begin the same procedure
            File cutFile = new File(fileArg);

            try {
                Scanner cutScanner = new Scanner(cutFile);

                // this method loops through the text using the established arguments
                loopPrint(cutScanner);

                cutScanner.close();
            }
            catch (FileNotFoundException exception) {
                System.out.println("File not found.");
                System.exit(0);
            }
        }
    }

    /**
     * This method examines each String returned by Scanner.next(), and if it is in the range as specified by the
     * fields argument, then print it.
     *
     * @param cutScanner Scanner file containing text to process
     */
    private void loopPrint(Scanner cutScanner) {

        // using this while loop allows the text file to have multiple lines, like the HW example does
        while(cutScanner.hasNextLine()) {

            // build a new Scanner out of the line
            Scanner lineScanner = new Scanner(cutScanner.nextLine());

            // apply the delimiter
            lineScanner.useDelimiter(delimiter);

            // only need to loop a number of times less than or equal to lastField
            for (int index = 1; index <= lastField; index++) {
                if (lineScanner.hasNext()) {
                    // if the index is greater than or equal to firstField, than it is within the desired range
                    if (index >= firstField && index <= lastField) {
                        System.out.print(lineScanner.next() + " ");
                    } else {
                        // if not, discard the next segment
                        lineScanner.next();
                    }
                }
            }
            System.out.println();
        }
    }
}
