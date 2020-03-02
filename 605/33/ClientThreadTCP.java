/*
 * ClientThreadTCP.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 13.1
 * This class assigns the threads with the portion of file to carry the operation
 * and send the data to the server and back using TCP Connection.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */
import java.io.*;
import java.net.Socket;

public class ClientThreadTCP extends Thread{

    private String server;
    private Socket socket;
    private int port;
    private int position;
    private int width;
    private String result;

    ClientThreadTCP(String server, int port, int position, int width) {
        this.server = server;
        this.port = port;
        this.position = position;
        this.width = width;
    }

    public void run() {

        try {
            socket = new Socket(server, port);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            String command = position + "," + width;
            writer.println(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            result = reader.readLine();
            reader.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        return result;
    }
}
