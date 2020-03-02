/*
 * WordCounter.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 8.1
 * This program counts the number of instances of each word in a text file. We have made the reasonable assumption that
 * contractions should be treated differently from the explicit expansions, for example "shouldn't" is counted
 * separately from "should not". Numbers in digit form are not counted.
 *
 * This solution uses tree nodes. See TreeNode.java for more information.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */

import java.io.*;
import java.util.Scanner;

public class WordCounter {

    public static void main(String[] args) {

        String fileName;
        TreeNode root = new TreeNode(); // root tree node

        // if there is no command line argument, accept user input
        if(args.length == 0) {
            try(Scanner userInput = new Scanner(System.in)) {
                System.out.print("File name: ");
                fileName = userInput.nextLine();
            }
        }
        else { fileName = args[0]; }

        double startTime = System.currentTimeMillis();

        // use BufferedReader for optimal speed and "try with resources" to automatically close
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            TreeNode current; // this node keeps track of current letter in a word
            byte[] letters; // we will convert the line into a byte array for faster traversal

            while(line != null) {
                current = root; // at the start of the line, we can assume that new word begins
                letters = line.toLowerCase().getBytes(); // switch to lower case and send to byte array
                for(byte letter : letters) {
                    // if character is not a letter (within 97-122) then start a new word unless it is an apostrophe
                    if(letter < 97 || letter > 122) {
                        // if not a apostrophe, we can end the word and start back at root node
                        if(letter != 39) {
                            current.endOfWord++;
                            current = root;
                        }
                    }
                    else {
                        // a is 97 in bytes, so if we subtract 97 from the byte value, we can acquire the proper
                        // position within the tree node's branches
                        int index = letter - 97;
                        if(current.letters[index] == null) {
                            current.letters[index] = new TreeNode((char)letter);
                        }
                        // move the current letter to the appropriate branch
                        current = current.letters[index];
                    }
                }
                // at the end of line, we can assume the word ends
                current.endOfWord++;
                line = reader.readLine();
            }
            // print the whole thing
            root.print();
        }
        // if there is an error opening the file, exit gracefully
        catch(IOException e) {
            System.out.println("Unable to open file.");
        }

        double endTime = System.currentTimeMillis();
        System.out.println("Runtime was " + (endTime - startTime) + " milliseconds.");
    }
}
