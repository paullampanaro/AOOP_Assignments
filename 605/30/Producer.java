/*
 * Producer.java
 *
 * Version: 1.0
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 10.2
 * The Producer thread adds a set number of items to a shared storage each cycle. The number of items, frequency of
 * cycles, and maximum allowed items in storage is set by the constructor. Access to Storage is synchronized with other
 * Producers and Consumers, but the thread will wait for its cycle frequency on its own time.
 *
 * @author Paul Lampanaro
 * @author Anuj Kulkarni
 */

import java.util.ArrayList;
import java.util.Collections;

public class Producer extends Thread {

    int id;
    int soMuchToProduce; // how many units to add each cycle
    int soOftenToProduce; // how often to cycle, in seconds
    int storageSize; // max number of items in storage
    final ArrayList<Integer> storage;

    Producer(int id, int soMuchToProduce, int soOftenToProduce, int storageSize, ArrayList<Integer> storage) {

        this.id = id;
        this.soMuchToProduce = soMuchToProduce;
        this.soOftenToProduce = soOftenToProduce;
        this.storageSize = storageSize;
        this.storage = storage;
    }

    public void run() {

        // first we wrap the cycle in a try block. If a thread error occurs, we want the thread to terminate.
        try {
            // continue cycling until program ends.
            while (true) {
                // only operations on storage need to be synchronized
                synchronized (storage) {

                    // only add when  there is room
                    // there is a chance that this thread may be awoken too soon (such as by another Producer)
                    // enclose wait inside loop so that thread rechecks condition before continuing
                    while (storage.size() > storageSize - soMuchToProduce) {
                        storage.wait();
                    }
                    // add the set number of items
                    for (int index = 0; index < soMuchToProduce; index++) {
                        storage.add(id);
                    }
                    ProducerConsumer.test(); // must test storage
                    Collections.sort(storage); // sort before printing
                    System.out.println("Producer " + id + ": produced quantity " + soMuchToProduce);
                    System.out.println("Storage: " + storage);
                    storage.notifyAll(); // let other threads know that they can resume
                }
                // sleep can run outside of synchronization, as cycle frequency is per thread
                this.sleep(soOftenToProduce * 1000);
            }
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
