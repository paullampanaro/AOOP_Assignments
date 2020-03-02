/*
 * TreeNode.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 8.1
 * The tree node uses an array of 26 tree nodes as branches. Each represents a letter, and this allows WordCounter.java
 * to traverse down the branches of the tree to store unique word values. An end of word counter allows for tracking of
 * word count.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */

public class TreeNode {

    private final int ALPHABET = 26;
    private char value; // while not necessary, this saves us from having to convert to char when printing
    int endOfWord;
    TreeNode[] letters;

    // default constructor is used for the root node
    TreeNode() {
        endOfWord = 0;
        letters = new TreeNode[ALPHABET]; // initialize the branches as null, until child letters are created
    }

    // argument constructor is used for word counting to store the char value
    TreeNode(char value) {
        this.value = value;
        endOfWord = 0;
        letters = new TreeNode[ALPHABET]; // initialize the branches as null, until child letters are created
    }

    /**
     * This helper function is made to be called on the root node, and calls the recursive print function for each
     * letter found within root
     */
    public void print() {
        for(TreeNode letter : letters) {
            if(letter != null) {
                letter.printRec("");
            }
        }
    }

    /**
     * This recursive function traverses down the tree, building strings for each word and printing them when a valid
     * end of word count is found
     * @param construction the string to build upon
     */
    private void printRec(String construction) {

        String word = construction + value;
        if(endOfWord > 0) {
            System.out.println(word + ": " + endOfWord);
        }
        for(TreeNode letter : letters) {
            if(letter != null) {
                letter.printRec(word);
            }
        }
    }
}
