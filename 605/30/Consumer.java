/*
 * Consumer.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 10.2
 * The Consumer thread consumes a number of units from a shared storage each cycle. The number of items is equal to the
 * Consumer's id. Frequency of cycles, and maximum allowed items in storage is set by the constructor. Access to Storage
 * is synchronized with other Producers and Consumers, but the thread will wait for its cycle frequency on its own time.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */

import java.util.ArrayList;
import java.util.Collections;

public class Consumer extends Thread {

    int id;
    int soOftenToConsume; // how often to cycle in seconds
    final ArrayList<Integer> storage;

    Consumer(int id, int soOftenToConsume, ArrayList<Integer> storage) {

        this.id = id;
        this.soOftenToConsume = soOftenToConsume;
        this.storage = storage;
    }

    public void run() {

        // first we wrap the cycle in a try block. If a thread error occurs, we want the thread to terminate.
        try {
            // continue cycling until program ends.
            while(true) {

                // only operations on storage need to be synchronized
                synchronized (storage) {

                    // only consume when there are enough units
                    // there is a chance that this thread may be awoken too soon (such as by another Consumer)
                    // enclose wait inside loop so that thread rechecks condition before continuing
                    while (storage.size() < id) {
                        storage.wait();
                    }
                    // consume a number of units equal to this thread's id
                    for (int index = 0; index < id; index++) {
                        storage.remove(storage.size() - 1);
                    }
                    ProducerConsumer.test(); // must test storage
                    Collections.sort(storage); // sort storage before printing
                    System.out.println("Consumer " + id + ": consumed quantity " + id);
                    System.out.println("Storage: " + storage);
                    storage.notifyAll(); // let other threads know they can resume
                }
                // sleep can run outside of synchronization, as cycle frequency is per thread
                this.sleep(soOftenToConsume * 1000);
            }
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
