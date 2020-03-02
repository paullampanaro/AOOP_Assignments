/*
 * Find.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 9.1
 * This program descends a directory recursively.
 * Use --directory followed by the path to run this program.
 * --printFile will print each file and folder found within the directory
 * --printDate will print the last modified date of each path
 * --printGMT will convert the last modified date to Greenwich Meridian Time
 * --printLength will print the length of each path
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */

import java.io.File;
import java.util.Date;
import java.util.TimeZone;

public class Find {

    String path; // the path given by command line argument
    boolean printFile;
    boolean printDate;
    boolean printGMT;
    boolean printLength;

    Find() {

        path = null;
        printFile = false;
        printDate = false;
        printGMT = false;
        printLength = false;
    }

    /**
     * The main method of this program take the command line arguments and creates a new Find object, which processes
     * the arguments. Walk is then called to traverse the directory.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        Find finder = new Find();
        finder.read(args);
        finder.walk();
    }

    /**
     * This method checks each command line argument to store the directory path, and other user requested flags.
     * @param args command line arguments
     */
    public void read(String[] args) {

        for(int index = 0; index < args.length; index++) {
            switch (args[index]) {
                case "--directory":
                    path = index == args.length - 1 ? null : args[index + 1]; // use the next argument after --directory
                    break;
                case "--printFile":
                    printFile = true;
                    break;
                case "--printDate":
                    printDate = true;
                    break;
                case "--printGMT":
                    printGMT = true;
                    break;
                case "--printLength":
                    printLength = true;
                    break;
            }
        }
    }

    /**
     * This is a helper function to call walkRec().
     */
    public void walk() {
        if(path == null) {
            System.out.println("No directory argument.");
            System.exit(0);
        }
        File file = new File(path);
        // here we do some basic File checking to see if the path is valid.
        if(!file.exists()) {
            System.out.println("Path not found.");
            System.exit(0);
        }

        // construct a relative path by starting with the last directory in the given directory argument
        String[] breakup = path.split("/|\\\\"); // either back-slash or forward-slash
        if(printGMT) { // if GMT is desired, adjust the timezone
            TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        }
        walkRec(file, breakup[breakup.length - 1]);
    }

    /**
     * This recursive function traverses the directory tree using File.listFiles(). Each user requested flag is checked,
     * then the method is called again on the File array give by listFiles().
     * @param file file to traverse recursively
     * @param path relative String path to build upon for --printFile
     */
    private void walkRec(File file, String path) {
        File[] children = file.listFiles(); // this will return null if the path is not a directory or has no children
        if(children != null) {
            for(File child : children) {

                String morePath = path + ":" + child.getName(); // build upon the relative path here
                if(printFile) {
                    System.out.print(morePath + " ");
                }
                if(printDate) {
                    Date date = new Date(child.lastModified());
                    System.out.print(date + "   ");
                }
                if(printLength) {
                    System.out.print(child.length());
                }
                System.out.println();

                if(child.isDirectory()) { // recursive call here
                    walkRec(child, morePath);
                }
            }
        }
    }
}
