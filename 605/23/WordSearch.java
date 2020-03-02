/*
 * WordSearch.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * This class accepts user input in order to find the requested word in a word search puzzle. First the file is
 * requested from the user. If the file is not present, the user is prompted to try again. Once a valid file has been
 * entered, the user can input words to search in the puzzle. The program searches both rows and columns, backwards and
 * forwards, and prints any matches to the console. The user can exit the program by typing "exit".
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;


public class WordSearch {

    /**
     * The main program. Accepts user input using the Scanner class. Prompts name of file from user, then attempts to
     * build new Scanner from the file name. If unsuccessful, prompts the user to try again. If successful, begins a new
     * loop where user can enter words to search for. Typing "exit" will exit the program.
     *
     * @param    args    command line arguments
     */
    public static void main(String[] args) {

        Scanner fromUser = new Scanner(System.in);
        System.out.println("Word search puzzle file name: ");
        String userInput = fromUser.nextLine();

        while (!userInput.equals("exit")) {

            File wordSearchFile = new File(userInput);

            try {

                Scanner wordSearch = new Scanner(wordSearchFile);

                // While the Scanner has more lines to find in the puzzle, print them out.
                while(wordSearch.hasNextLine()){

                    System.out.println(wordSearch.nextLine());
                }

                System.out.println("Word to search for: ");
                userInput = fromUser.nextLine();

                // Until the user types exit, search for the input in the puzzle.
                while(!userInput.equals("exit")) {

                    // Rebuild the Scanner upon each attempt, because it is emptied after each attempt.
                    wordSearch = new Scanner(wordSearchFile);
                    findWordInPuzzle(wordSearch, userInput, 6);
                    System.out.println("Word to search for: ");
                    userInput = fromUser.nextLine();
                }

                // Close scanner after using.
                wordSearch.close();

            } catch (FileNotFoundException exception) {

                // If there is no file in the default location (within project) then try again.
                System.out.println("File does not exist. Please try again: ");
                userInput = fromUser.nextLine();
            }
        }

        // Close the user input Scanner when done.
        fromUser.close();
    }

    /**
     * Searches for the string in the puzzle, both in row and column, backwards and forwards. If the word is found,
     * prints the row or column containing word.
     *
     * @param wordSearch Scanner loaded with word search puzzle
     * @param wordToFind String to search for in the puzzle
     * @param puzzleSize number of rows and columns in puzzle - must be the same
     */
    private static void findWordInPuzzle(Scanner wordSearch, String wordToFind, int puzzleSize) {

        /* Build a string with every row of the puzzle concatenated together. This allows us to search both rows and
           columns in a similar fashion.
         */
        String wordSearchString = new String();

        while(wordSearch.hasNextLine()) {
            wordSearchString += wordSearch.nextLine();
        }

        /*
           Loop through the combined string and build substrings of the appropriate size (using puzzle size). Then turn
           these into char arrays and check them in findWordInRow() method. If found, print the row.
         */
        for(int index = 0; index < puzzleSize; index++) {

            String row = wordSearchString.substring((index * puzzleSize), (index * puzzleSize) + puzzleSize);
            char[] rowArray = row.toCharArray();

            if(findWordInRow(rowArray, wordToFind)) {
                System.out.println("Found \"" + wordToFind + "\" in row: " + index + " " + row);
            }
        }

        /*
           Loop through the combined string and build char arrays for each column. This works by starting at an index
           from 0 to puzzleSize, then jumping ahead a number of indices equal to puzzleSize, until the column is built.
           Then use findWordInRow and print if a match is found.
         */
        for(int index = 0; index < puzzleSize; index++) {

            char[] columnArray = new char[puzzleSize];
            for(int x = 0; x < puzzleSize; x ++) {

                columnArray[x] = wordSearchString.charAt(index + (x * puzzleSize));
            }

            if(findWordInRow(columnArray, wordToFind)) {

                String column = new String(columnArray);
                System.out.println("Found \"" + wordToFind + "\" in column: " + index + " " + column);
            }
        }
    }

    /**
     * This method searches a char array to find another String. It does this by counting consecutive matches. If a
     * number of consecutive matches are found equal to the length of the wordToFind, then stop searching and return
     * true. Reverse of the word is also searched by counting matches starting from the end of the wordToFind.
     *
     * @param rowArray char array to search
     * @param wordToFind String to look for within rowArray
     * @return If the word is found within the char array
     */
    private static boolean findWordInRow(char[] rowArray, String wordToFind) {

        int wordLength = wordToFind.length();
        int matchCount = 0;
        int matchCountReverse = wordLength; // for reverse, begin at end of word and count down

        for(int letterIndex = 0; letterIndex < rowArray.length; letterIndex++) {

            char rowLetter = rowArray[letterIndex];

            if(rowLetter == wordToFind.charAt(matchCount)) {
                matchCount++;
            }
            else if(rowLetter == wordToFind.charAt(0)){
                /*
                   This one is tricky. If the letter does not match our current letter, but still matches the first,
                   then we must set the counter to 1, not 0, otherwise we will miss certain cases.
                */
                matchCount = 1;
            }
            else {
                // If a letter does not match, restart the count
                matchCount = 0;
            }

            if(rowLetter == wordToFind.charAt(matchCountReverse - 1)) {
                matchCountReverse--;
            }
            else if(rowLetter == wordToFind.charAt(wordLength - 1)) {
                /*
                   This one is tricky. If the letter does not match our current letter, but still matches the last,
                   then we must set the reverseCount to the last latter, otherwise we will miss certain cases.
                */
                matchCountReverse = wordLength - 1;
            }
            else {
                // If a letter does not match, restart the count
                matchCountReverse = wordLength;
            }

            if(matchCount == wordLength || matchCountReverse == 0) {
                return true;
            }
        }

        return false;
    }
}
