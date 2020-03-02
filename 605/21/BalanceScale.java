/*
 * BalanceScale.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * This program uses a given set of weights to determine if a subset exists 
 * where the sum total matches another given weight.
 * 
 * Please note that we weren't sure if the 80 character limit on each line 
 * (from Coding Standard) includes the comments, so current indentation reflects
 * 80 line limit when comments are included.
 *
 * @author      Paul Lampanaro
 * @author      Anuj Kulkarni
 */
public class BalanceScale {
	
	// weights to be used to build a subset
    static int[] myWeights = { 35, 61, 6, 5, 1, 8, 12};
    // these weights will be checked against myWeights 
    static int[] toWeigh   = { 1, 4, 6, 7, 9, 14, 17, 18, 20, 23, 28, 110, 111, 
    		115, 62, 24, 202, 203, 204, 205};
    // this array will be flagged to determine successful weights
    // during each attempt, 0 = failure, 1 = success
    static int[] successfulSet = new int[myWeights.length];

    /**
     * Sort an int array into descending order, using bubble sort algorithm
     *
     * @param unsortedIntArray	int array to be sorted
     *
     * @return sorted int array
     */
    public static int[] sortMyWeights(int[] unsortedIntArray) {                  

    	int[] sortedIntArray = unsortedIntArray;
        int length = sortedIntArray.length;
        int temp = 0;

        // bubble sort algorithm used here
        for(int i = 0; i < length; i++) {
            for (int j = 1; j < (length - i); j++) {
            	// checks if the weight to the left is greater then the weight
            	// to the right,the bigger weight shifts to the right
                if (sortedIntArray[j - 1] > sortedIntArray[j]) {
                    temp = sortedIntArray[j - 1];
                    sortedIntArray[j - 1] = sortedIntArray[j];
                    sortedIntArray[j] = temp;
                }
            }
        }
        
        return sortedIntArray;
    }

    /**
     * Checks if a subset exists within myWeights that is equal to a given 
     * weight. This method begins attempt at a given index and ignores any 
     * weights sorted above this index.
     *
     * @param index	where to begin attempt within myWeights
     * @param weightToCheck	
     *
     * @return 
     */
    public static boolean checkWeight(int index, int weightToCheck) { 

    	// if the weights after subtracting are equal to zero , we find our set 
    	// of weights from MyWeights
        if(weightToCheck == 0) return true;
        // if the weights after subtracting all available weights are not equal
        // to zero , there are no available combination for the weight
        if(index == 0 && weightToCheck != 0) return false;

        /*
         * Begin recursive call. First call attempts to skip current weight. If 
         * this is successful, then we favor this attempt, because skipping 
         * larger weight will give us longest set available.
         * Second call will continue attempt after applying current weight. If 
         * this is a success, then a success will be flagged in successfulSet.
         */
        if(checkWeight(index-1, weightToCheck)){
            return true;
        }
        // check this second, it will favor shorter sets
        else if(checkWeight(index-1, weightToCheck - myWeights[index-1])) {
            // can only log successful weight here because this attempt uses
        	// the current weight instead of skipping it
            successfulSet[index-1] = 1;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * The main program. Sorts myWeights, and then loops through toWeigh
     *
     * @param    args    command line arguments
     */
    public static void main(String[] args) {

        myWeights = sortMyWeights(myWeights);

        int numberOfMyWeights = myWeights.length;

        for(int index = 0; index < toWeigh.length; index++) {

        	// must reset succesfulSet after each attempt
            for(int x = 0; x < successfulSet.length; x++){
                successfulSet[x] = 0;
            }

            // here is where recursive method is actually used
            boolean setExists = checkWeight(numberOfMyWeights, toWeigh[index]);
            System.out.print(toWeigh[index] + "g: ");
            if(setExists) System.out.print("Yes;");
            else System.out.print("No");

            if(setExists) {
                System.out.print(" My weights used: ");

                // loop through successful weights and print in reverse order
                for (int x = successfulSet.length - 1; x >= 0; x--) {
                    if (successfulSet[x] == 1) {
                        System.out.print(" " + myWeights[x] + "g");
                    }
                }
            }

            System.out.println();
        }
    }
}
