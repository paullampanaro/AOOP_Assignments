/*
 * NumberCounterClientTCP.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 13.1
 * This class creates a client class with tcp connection to the server which divides the files and
 * assigns the work among the threads and recollects the data
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */
import java.io.*;

public class NumberCounterClientTCP {

    static final String FILE_PATH = "/home/fac/hpb/public_html/30/pi-billion.txt";
    private String[] serverNames;
    private ClientThreadTCP[] clients;
    private int port;

    NumberCounterClientTCP(String[] serverNames, int port) {
        this.port = port;
        this.serverNames = serverNames;
        clients = new ClientThreadTCP[serverNames.length];
    }
    /**Divides the files among the threads to carry out the counting
     * */
    public void assign() throws IOException {

        File file = new File(FILE_PATH);
        int length = (int)file.length();
        int numThreads = clients.length;

        int perServer = length / numThreads;
        int leftover = length % numThreads;

        for(int index = 0; index < numThreads; index++) {
            int width = index == numThreads - 1 ? perServer + leftover : perServer;
            clients[index] = new ClientThreadTCP(serverNames[index], port, index * perServer, width);
            clients[index].start();
        }
    }
    /**Recollects the counting data from the threads and gives the total count
     * */
    public void collect() {
        try {
            for (ClientThreadTCP client : clients) {
                client.join();
            }
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }

        int[] total = new int[10];

        for(ClientThreadTCP client: clients) {
            String[] counts = client.getResult().split(",");

            for(int index = 0; index < 10; index++) {
                String[] pair = counts[index].split(":");
                total[index] += Integer.parseInt(pair[1]);
            }
        }

        for(int index = 0; index < 10; index++) {
            System.out.println(index + ": " + total[index]);
        }
    }

    public static void main(String[] args) {

        try {
            String[] serverNames = new String[args.length - 2];
            System.arraycopy(args, 2, serverNames, 0, args.length - 2);
            NumberCounterClientTCP client = new NumberCounterClientTCP(serverNames, Integer.parseInt(args[1]));
            client.assign();
            client.collect();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
