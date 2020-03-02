/*
 * ClientThreadSerialization.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 13.3
 * This class uses thread and serialization for carrying out the work by the client.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */
import java.rmi.Naming;
import java.util.HashMap;

public class ClientThreadSerialization extends Thread {

    private int port;
    String serverName;
    int position;
    int width;
    public HashMap<Integer,Integer> count;

    ClientThreadSerialization(String serverName, int port, int position, int width) {
        this.port = port;
        this.serverName = serverName;
        this.position = position;
        this.width = width;
    }
/** runs threads for server to create multiple servers and get the total count*/
    public void run() {

        try {
            String address = "rmi://" + serverName + ":" + port + "/CounterObject";
            CounterInterface obj = (CounterInterface) Naming.lookup(address);
            obj.calculate(position, width);
            count = obj.getCount();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer,Integer> getCount() {
        return count;
    }
}
