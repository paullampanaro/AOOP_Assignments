/*
 * NumberCounterServerSerialization.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 13.3
 * This class creates the  registry of the methods to be implemented and sends back the count
 * to the client
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class NumberCounterServerSerialization {

    int port;

    NumberCounterServerSerialization(int port) {
        this.port = port;
    }


    public void listen() {

        try {
            Registry r = LocateRegistry.createRegistry(port);
            //creates registry using the port no.
            String test = "CounterObject";
            r.rebind(test, new Counter());
            //rebinds the lookup name and creates new object for the counter
            System.out.println("Ready");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        NumberCounterServerSerialization server = new NumberCounterServerSerialization(Integer.parseInt(args[1]));
        server.listen();
    }
}
