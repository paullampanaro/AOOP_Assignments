/*
 * CounterInterface.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 13.3
 * This Interface has the methods to be be implemented by the remote servers
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.IOException;
import java.util.HashMap;

public interface CounterInterface extends Remote {
    //Create interface to be implemented by the remote servers to create registry.

    public void calculate(int position, int width) throws RemoteException, IOException;
    public HashMap<Integer,Integer> getCount() throws RemoteException;
}
