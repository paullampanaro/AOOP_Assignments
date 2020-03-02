/*
 * NumberCounterServerUDP.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 13.2
 * This class creates the server class which connects the client using udp and collects data
 * using datagram
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */
import java.io.*;
import java.net.*;
import java.util.HashMap;

public class NumberCounterServerUDP {

    static final String FILE_PATH = "/home/fac/hpb/public_html/30/pi-billion.txt";
    private int port;
    private int clientPort;
    private InetAddress clientAddress;
    private DatagramSocket socket;
    private int position;
    private int width;
    private HashMap<Integer,Integer> count;

    NumberCounterServerUDP(int port) {
        this.port = port;
        count = new HashMap<>();
    }
/**Connects the client with the server using port*/
    public void open() throws IOException {
        socket = new DatagramSocket(port);
    }
/**Gets data from the client and traces its ip address and port no to send
 * back the data*/
    public void listen() throws IOException {

        byte[] input = new byte[256];
        DatagramPacket dpInput = new DatagramPacket(input, input.length);
        socket.receive(dpInput);
        clientPort = dpInput.getPort();
        clientAddress = dpInput.getAddress();

        String command = new String(input).trim();
        String[] commands = command.split(",");
        position = Integer.parseInt(commands[0]);
        width = Integer.parseInt(commands[1]);
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
 * Sends back the result of the total count  to the client using datagram
 * */
    public void respond() throws IOException {

        String output = "";
        for(int index = 0; index < 10; index++) {
            output += index + ":" + count.getOrDefault(index,0) + ",";
        }

        byte[] response = output.getBytes();
        DatagramPacket dpResponse = new DatagramPacket(response, response.length, clientAddress, clientPort);
        socket.send(dpResponse);
    }

    public static void main(String[] args) {
        try {
            NumberCounterServerUDP server = new NumberCounterServerUDP(Integer.parseInt(args[1]));
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
