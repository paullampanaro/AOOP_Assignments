/*
 * NumberCounterClientSerialization.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 13.3
 * This class reads the file , divides the files and assigns to the server and gets back
 * the result of the total count
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */
import java.io.File;
import java.util.HashMap;

public class NumberCounterClientSerialization {

    final String FILE_PATH = "/home/fac/hpb/public_html/30/pi-billion.txt";
    int port;
    String[] serverNames;
    ClientThreadSerialization[] clients;

    NumberCounterClientSerialization(String[] serverNames, int port) {
        this.port = port;
        this.serverNames = serverNames;
        clients = new ClientThreadSerialization[serverNames.length];
    }
    /**
     * Divides the files among the threads and servers and assigns them position and width
     */
    public void assign() {


        File file = new File(FILE_PATH);
        int length = (int)file.length();
        int numThreads = clients.length;

        int perServer = length / numThreads;
        int leftover = length % numThreads;

        for(int index = 0; index < numThreads; index++) {
            int width = index == numThreads - 1 ? perServer + leftover : perServer;
            clients[index] = new ClientThreadSerialization(serverNames[index], port, index * perServer, width);
            clients[index].start();
        }
    }/**Collects the data from the server and calculates the total pi count*/

    public void collect() {
        try {
            for (ClientThreadSerialization client : clients) {
                client.join();
            }
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }

        int[] total = new int[10];

        for(ClientThreadSerialization client: clients) {
            HashMap<Integer,Integer> count = client.getCount();

            for(int index = 0; index < 10; index++) {
                int temp = count.getOrDefault(index, 0);
                total[index] += temp;
            }
        }

        for(int index = 0; index < 10; index++) {
            System.out.println(index + ": " + total[index]);
        }
    }

    public static void main(String args[] ) {
        String[] serverNames = new String[args.length - 2];
        System.arraycopy(args, 2, serverNames, 0, args.length - 2);
        NumberCounterClientSerialization client =
                new NumberCounterClientSerialization(serverNames, Integer.parseInt(args[1]));
        client.assign();
        client.collect();
    }
}