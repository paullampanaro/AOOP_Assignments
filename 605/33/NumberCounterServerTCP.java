/*
 * NumberCounterServerTCP.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 13.1
 * This class creates the server class which connects the client using TCP and collects data
 * using the same connection.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class NumberCounterServerTCP {

    static final String FILE_PATH = "/home/fac/hpb/public_html/30/pi-billion.txt";
    private int port;
    private Socket socket;
    private ServerSocket serverSocket;
    private int position;
    private int width;
    private HashMap<Integer,Integer> count;

    NumberCounterServerTCP(int port) {
        this.port = port;
        count = new HashMap<>();
    }
    /**Connects the client with the server using port*/
    public void open() throws IOException {
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
    }
    /**Gets data from the client and splits the file among the server threads*/
    public void listen() throws IOException {
        BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String[] command = fromClient.readLine().split(",");
        position = Integer.parseInt(command[0]);
        width = Integer.parseInt(command[1]);
    }
    /**
     * calculates the occurrences of digits in a pi and converting
     * from ASCII to integer value
     * */


    public void calculate() throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {

            reader.skip(position);
            int iterations = 0;
            int number = reader.read();

            while (number != -1 && iterations < width) {

                if(number > 47 && number < 59) {
                    int temp = count.getOrDefault(number-48,0);
                    count.put(number-48,temp+1);
                }
                number = reader.read();
                iterations++;
            }
        }
    }
    /**
     * Sends back the result of the total count  to the client using the same TCP connection
     * */
    public void respond() throws IOException {
        PrintWriter toClient = new PrintWriter(socket.getOutputStream(), true);
        String output = "";
        for(int index = 0; index < 10; index++) {
            output += index + ":" + count.getOrDefault(index,0) + ",";
        }
        toClient.println(output);
        toClient.close();
        socket.close();
        serverSocket.close();
    }

    public static void main(String[] args) {
        try {
            NumberCounterServerTCP server = new NumberCounterServerTCP(Integer.parseInt(args[1]));
            server.open();
            server.listen();
            server.calculate();
            server.respond();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
