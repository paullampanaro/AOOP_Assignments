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
 * This class creates Objects for the threads to go into deadlock.It includes boolean flags which help to acquire
 * the objective.
 *
 * For HW 12.3:
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