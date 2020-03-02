/*
 * BattleShip.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * This program runs a single player Battleship game. It loads the game from a text file where blank spaces are
 * marked with the '.' character and ships are marked with integers from 0 to 9. Using the Scanner class, the program
 * takes user input to hit targets. Hits directly in the center of a ship that have not been hit elsewhere, count as
 * critical hits, instantly destroying the ship.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Battleship {

    /**
     * In main the user input Scanner is created and used to accept a battleship file. If the File is created
     * successfully, call runGame(). Otherwise, prompt the user again. "exit" will close the program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        Scanner fromUser = new Scanner(System.in);
        System.out.println("Battleship file name: ");

        while (fromUser.hasNext()) {

            String userInput = fromUser.nextLine();

            if(userInput.compareTo("exit") == 0) {
                System.exit(0);
            }
            else {
                try {

                    File wordSearchFile = new File(userInput);
                    Scanner grid = new Scanner(wordSearchFile);

                    // call runGame() here. This method will handle the rest of the game.
                    runGame(grid, fromUser);
                    System.out.println("Game over. Play again?\nBattleship file name: ");

                } catch (FileNotFoundException exception) {

                    // If there is no file in the default location (within project) then try again.
                    System.out.println("File does not exist. Please try again: ");
                }
            }
        }

        // Close the user input Scanner when done.
        fromUser.close();
    }

    /**
     * This method builds a master string from the battleship text file, which will be used to check for valid hits. It
     * also builds a char array for tracking hits and printing out to the user. The loop will continue to prompt the
     * user for row and column coordinates until all ships are destroyed.
     *
     * @param grid Scanner that is built from valid battleship text file. Empty spaces are indicated by '.' character,
     *             while the ships are indicated by integers from 0 to 9
     * @param userInput Scanner built from user input
     */
    private static void runGame(Scanner grid, Scanner userInput) {

        int gridSize = 0;
        String gridString = new String();

        // build a master string from the battleship text file
        // calculate the grid size from any line of the text file
        while(grid.hasNext()) {
            String line = grid.nextLine();
            gridString += line;
            gridSize = line.length();
        }

        // these two values will be used to track when the game ends
        int totalShips = 0;
        int shipsHit = 0;

        // countNonEmptySquares will return the number of squares occupied by ships
        totalShips = countNonEmptySquares(gridString.toCharArray());

        // this char array is used to record hits and output to user
        char[] playGrid = buildPlayGrid(gridString.length());


        // loop for user input until all ships are destroyed
        while(shipsHit < totalShips) {
            // print the play grid every loop
            printPlayGrid(playGrid, gridSize);

            int rowInput = 0;
            int columnInput= 0;
            // this boolean will be used to determine if the input was valid
            boolean validInput = true;

            System.out.print("row coordinates: ");
            if(userInput.hasNextInt()) {
                rowInput = userInput.nextInt();
            }
            else {
                validInput = false;
            }
            // always clear the user input Scanner
            userInput.nextLine();

            System.out.print("column coordinate: ");
            if(userInput.hasNextInt()) {
                columnInput = userInput.nextInt();
            }
            else {
                validInput = false;
            }
            // always clear the user input Scanner
            userInput.nextLine();

            if(!validInput) {
                System.out.println("Invalid input, please try again.");
            }
            else {
                // check for a hit, then update total ships hit
                checkHit(gridString, playGrid, gridSize, rowInput, columnInput);
                // count nonEmptySquares can also be used here, as it does not count misses either
                shipsHit = countNonEmptySquares(playGrid);
            }
        }

        // when loops finished, print play grid one last time, then indicate to user that all ships are gone
        printPlayGrid(playGrid, gridSize);
        System.out.println("All ships destroyed.");
    }

    /**
     * Checks if row and column coordinates are a critical hit, hit, miss, or a repeat location.
     *
     * @param gridString The master string built from battleship text file
     * @param playGrid char array used to track hits
     * @param gridSize size of the battleship grid
     * @param row row of target from
     * @param column column of target
     */
    private static void checkHit(String gridString, char[] playGrid, int gridSize, int row, int column) {

        // if out of bounds, then quit early
        if(row >= gridSize || row < 0 || column >= gridSize || column < 0) {
            System.out.println("Out of bounds.");
        }
        else {
            // position on the master string can be calculated using this formula
            int position = row * gridSize + column;
            char shipNumber = gridString.charAt(position);

            // if the location has already been targeted, quit early
            if(playGrid[position] == '.') {
                // this check determines if the target is at least a regular hit before checking for critical hit
                if (shipNumber != '.') {

                    // here we prepare to check for a critical, in the row and column of target
                    // first we build the row
                    String line = gridString.substring(row * gridSize, (row * gridSize) + gridSize);
                    char[] rowArray = line.toCharArray();

                    // then we build the column
                    char[] columnArray = new char[gridSize];
                    for (int index = 0; index < gridSize; index++) {
                        columnArray[index] = gridString.charAt(column + (gridSize * index));
                    }

                    // we must first check if the ship has already been hit. if so, it cannot be a critical hit
                    // then we check if the hit is critical by checking row and column
                    if (!checkShipAlreadyHit(playGrid, gridString, shipNumber) &&
                            (checkCriticalHitLine(rowArray, shipNumber, column) ||
                                    checkCriticalHitLine(columnArray, shipNumber, row))) {

                        // on a critical, mark all matching squares of the ship as hit
                        // Loop through the equivalent substring on the play grid and mark the hits.
                        for (int index = 0; index < gridString.length(); index++) {
                            if (gridString.charAt(index) == shipNumber) {
                                playGrid[index] = 'x';
                            }
                        }
                        System.out.println("CRITICAL HIT");
                    }
                    else {
                        playGrid[position] = 'x';
                        System.out.println("HIT");
                    }
                }
                else {
                    playGrid[position] = 'o';
                    System.out.println("MISS");
                }
            }
            else {
                System.out.println("Repeat location.");
            }
        }
    }

    /**
     * using a char array that represent either the row or column of the target, this method checks on either side of
     * the target and counts the number of matching ship squares. if there are an equal number on both sides of the
     * target, then we know that this is the center of ship that has odd number of squares, and thus is a critical hit
     * @param line char array that represents either the row or column of the target
     * @param shipNumber char that contains the type of shit hit
     * @param position this is either the column for row checks, or the row for column checks
     * @return whether or not the hit is a critical hit
     */
    private static boolean checkCriticalHitLine(char[] line, char shipNumber, int position) {

        // if the location is at the end or start of the array, it cannot be a critical hit
        if(position <= 0 || position >= line.length - 1) {
            return false;
        }

        // these variables will be incremented as matching ship squares are found
        int matchCountForward = 0;
        int matchCountBackward = 0;


        // check to the right of the target
        for(int index = position + 1; index < line.length; index++) {
            if(line[index] == shipNumber) matchCountForward++;
        }

        // check the left of the target
        for(int index = position - 1; index >= 0; index--) {
            if(line[index] == shipNumber) matchCountBackward++;
        }

        // if there are equal number of matching squares on either side, and this number is greater than 0,
        // then we know that this is a critical hit
        return (matchCountForward > 0 && matchCountForward == matchCountBackward);
    }

    /**
     * Helper function t0 build a char array to keep track of hits and misses. It is initialized with '.' at the start,
     * the same char used to represent blank location on the original battleship text file.
     * @param size the height and width of the grid
     * @return char array used to track hits and missed
     */
    private static char[] buildPlayGrid(int size) {

        char[] playGrid = new char[size];

        for(int index = 0; index < size; index++) {
            playGrid[index] = '.';
        }

        return playGrid;
    }

    /**
     * Helper function used to print out the grid used to track hits and misses
     * @param playGrid the grid used to track hits and missed
     * @param gridSize height and width of the play grid
     */
    private static void printPlayGrid(char[] playGrid, int gridSize) {

        for(int index = 0; index < playGrid.length; index++) {
            // once we hit the end of a row, must move to new line for proper formatting
            if(index % gridSize == 0 && index > 0) {
                System.out.println();
            }
            System.out.print(playGrid[index]);
        }
        System.out.println();
    }

    /**
     * This function checks through the play grid and checks to see if a ship has already been hit during the game.
     * This is used as a condition for a valid critical hit, because if the ship has already been hit, it cannot be a
     * critical hit.
     * @param playGrid the grid used for tracking hits and misses
     * @param gridString the master string from the battleship text file
     * @param shipNumber the char that contains the ID of the ship hit
     * @return
     */
    private static boolean checkShipAlreadyHit(char[] playGrid, String gridString, char shipNumber) {

        boolean shipAlreadyHit = false;

        for(int index = 0; index < playGrid.length; index++) {
            // if the tracking grid has a hit (or miss) and the master string matches the ship number, then the ship
            // has already been hit this game
            if (gridString.charAt(index) == shipNumber && playGrid[index] != '.') {
                shipAlreadyHit = true;
                break;
            }
        }

        return shipAlreadyHit;
    }

    /**
     * This function counts the number of non-empty, non-miss locations on a char array. It can be used to count total
     * ship squares on the original battleship text file, and also the number of hits on the tracking grid
     * @param grid either the master grid from the text file, or the tracking grid
     * @return
     */
    private static int countNonEmptySquares(char[] grid) {

        int total = 0;

        for(int index = 0; index < grid.length; index++) {
            // if not an empty location and also not a miss, increment
            if(grid[index] != '.' && grid[index] != 'o') {
                total++;
            }
        }

        return total;
    }
}