/**
 * HW 11.3
 * We have provided comments explaining the possible outputs, and how to manipulate the program to produce
 * certain outputs. We have also explained the effect of uncommenting the marked line.
 */
public class X extends Thread   {
    static int count=0;
    private String info;
    static Object o = new Object();
    public X (String info) {
        this.info    = info;
    }
    public void run () {
        while ( true )  {
            if((count > 0) || (this.info.equals("0"))){
                count++;
                 // o = new Object(); // marked
                /* This will increase the chance the the object is reassigned during the execution of a synchronized
                   block, which will mean that the notify will fail and both threads will be stuck waiting.
                try {
                    sleep(1000);
                }
                catch (Exception e ) {} */
                synchronized ( o ) {
                    if((count > 0) || (this.info.equals("0"))){
                        count++;
                        System.out.println(info);
                        try {
                            o.notify();
                            sleep(100);
                            o.wait();
                        } catch ( Exception e ) { }
                    }
                }
            }
        }
    }
    public static void main (String args []) {
        ( new X("0") ).start();
        ( new X("1") ).start();
    }
}


/*
Output :
A.1. 01010... : Gives a recurring following output as the while statement never terminates.
            Tread 0 with info as 0 enter the loop increases the count to 1 , enters the synchronized block
            increments the count to 2 and prints its info value of 0
            Thread 1 enters the while loop satisfies the condition, increments the count value and
            prints its info value of 1.
            The loop goes on forever as the while condition never fails.

  2. 10101... : Gives a recurring following output as the while statement never terminates.
             Tread 1 with info as 1 enter the loop increases the count to 1 , enters the synchronized block
             increments the count to 2 and prints its info value of 1
             Thread 0 enters the while loop satisfies the condition, increments the count value and
             prints its info value of 0.
            The loop goes on forever as the while condition never fails.

B.Some of the outputs are :
1.01010101010
2.010
3.0101
4.10101010101
5.101
6.1010
The following show that when a new object is created anytime in the loop, if at time when both the threads acquire
there own keys for the object in synchronisation block ,both the threads will land up in the wait block.
Probability of such occurrence depends on the scheduler of when it runs any the thread . Hence the set of outputs
in this case is indefinite.
*/

