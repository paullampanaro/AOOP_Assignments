/*
 * Lock.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 11.1 & 11.2
 * This class is used to synchronize threads in a sequential manner for the Producer-ConsumerProducer-Consumer problem
 * as well as the Firefighter problem. The boolean is used to have threads wait if they run before their turn order.
 * Once the previous thread has executed, it can set the boolean to open and notify the sleeping thread.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */
public class Lock {

    private boolean open;

    Lock(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }

    public void open() {
        open = true;
    }

    public void close() {
        open = false;
    }
}
