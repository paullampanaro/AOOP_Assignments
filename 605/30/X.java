public class X extends Thread    {
    static Object o = new Object();
    static int counter = 0;
    int id;
    public X(int id)	{
        this.id = id;
        o       = new Object();
    }
    public void run () {
        if ( id == 0 )	{
            new X(1).start();
            /* If this sleep is added, then Thread 1 will most likely begin before the assignment of Object o occurs
               in the constructor of Thread 2. If this happens, then each thread is synchronized to a different object,
               and therefore will not be able to wake up. Then the program will not terminate.
             */
//            try {
//                this.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            new X(2).start();
        return;
        }
        /* If this sleep is added, then threads will be required to sleep before entering synchronized block. This
           will help ensure that all assignments to the static Object o are complete and therefore all synchronization
           is happening on the same object.
         */
//        try {
//            this.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        synchronized ( o ) {
            System.err.println(id + " --->");
            try {
                if ( counter == 0 )	{
                    counter = 1;
                    o.wait();
                } else
                    o.notifyAll();
            }
            catch (  InterruptedException e ) {
            }
            System.err.println(id + " <---");
        }
    }
    public static void main (String args []) {
        new X(0).start();
    }
}

/*
        Possible outputs:
        A)1 --->
        2 --->
        2 <---
        1 <---
        Thread 1  prints its id with right arrow satisfies the if statement and goes into wait,
        Thread 2 run prints the right arrow and notifies all and complietes its execution by printing left arrow
        Thread 1 resumes its operation prints left array and ends its execution.

        B)2 --->
        1 --->
        1 <---
        2 <---
        Thread 1 starts though doesnt run and the scheduler then chooses Thread 2 to run
        Thread 2 prints its id with right arrow satisfies the if statement and goes into wait,
        Thread 1 run prints the right arrow and notifies all and complietes its execution by printing left arrow
        Thread 2 resumes its operation prints left array and ends its execution.

        The program is already given in the most likely terminate format given however the scheduler executes the
        Threads.
*/
