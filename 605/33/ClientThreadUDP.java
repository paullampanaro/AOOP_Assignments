/*
 * ClientThreadUDP.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 13.2
 * This class assigns the threads with the portion of file to carry the operation
 * and send the data to the server and back using databyte stream.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientThreadUDP extends Thread{

    private String server;
    private DatagramSocket socket;
    private int port;
    private int position;
    private int width;
    private String result;

    ClientThreadUDP(String server, int port, int position, int width) {
        this.server = server;
        this.port = port;
        this.position = position;
        this.width = width;
    }

    public void run() {

        try {
            socket = new DatagramSocket();
            String command = position + "," + width;
            byte[] output = command.getBytes();
            DatagramPacket dpOutput = new DatagramPacket(output, output.length, InetAddress.getByName(server), port);
            socket.send(dpOutput);

            byte[] response = new byte[256];
            DatagramPacket dpResponse = new DatagramPacket(response, response.length);
            socket.receive(dpResponse);
            result = new String(response).trim();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        return result;
    }
}
