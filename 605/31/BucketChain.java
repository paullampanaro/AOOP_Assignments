/*
 * BucketChain.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 11.2
 * This program simulates a chain of Firefights who must pass a bucket in a specific order before it is emptied and a
 * new bucket is handed to the first.
 *
 * Number of firefighters and the number of buckets (in this order) can be specified by the command line arguments.
 *
 * Each Firefighter is synchronized to the left and right by Lock objects, which simply contains a boolean. For more
 * information, see the Firefighter and Lock class.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */
public class BucketChain {

    public static void main(String[] args) {

        int numberOfFirefighters = 10;
        int numberOfBuckets = 10;
        Lock first = new Lock(true); // reference to first Lock will be saved, for the last Firefighter
        Lock left = first;
        Lock right = new Lock(false); // all locks will start closed unless it is the first

        try {
            numberOfFirefighters = args.length > 0 ? Integer.parseInt(args[0]) : numberOfFirefighters;
            numberOfBuckets = args.length > 1 ? Integer.parseInt(args[1]) : numberOfBuckets;
        }
        catch(NumberFormatException e) {
            System.out.println("Invalid arguments.");
            System.exit(0);
        }

        for(int index = 1; index <= numberOfFirefighters; index++) {
            // if this is the last Firefighter, the next Lock will be the first, to loop back to the first Firefighter
            Firefighter firefighter = index != numberOfFirefighters ?
                    new Firefighter(index, left, right, numberOfBuckets, false) :
                    new Firefighter(index, left, first, numberOfBuckets, true);
            left = right; // switch the right to the left, then make a new right
            right = new Lock(false);
            firefighter.start();
        }
    }
}
