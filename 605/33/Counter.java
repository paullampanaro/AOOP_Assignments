/*
 * Counter.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 13.3
 * This class implements CounterInterface which calculates and gets count of the occurrences of Pi digits by
 * the server.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */
import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class Counter extends UnicastRemoteObject implements CounterInterface {
    //implements the interface CounterInterface

    String fileName = "/home/fac/hpb/public_html/30/pi-billion.txt";
    int position;
    int width;
    private HashMap<Integer,Integer> count;

    public Counter() throws RemoteException {
        super();
        count = new HashMap<>();
    }

    public void calculate(int position, int width) throws IOException, RemoteException {

        this.position = position;
        this.width = width;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            reader.skip(position);  //Each thread jumps to its starting position.
            int iterations = 0;
            int number = reader.read();

            while (number != -1 && iterations < width) {

                if(number > 47 && number < 59) {
                    //counts the pi digits from 0 to 9 in there ASCII form
                    int temp = count.getOrDefault(number - 48,0);
                    count.put(number-48, temp + 1);
                    //convert it into integer form from ASCII and put it into hashmap
                }
                number = reader.read();
                iterations++;
            }
        }
    }

    public HashMap<Integer, Integer> getCount() throws RemoteException {
        return count;
        //returns the total count by the server
    }
}
