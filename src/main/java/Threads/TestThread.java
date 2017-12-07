package Threads;

import java.util.Random;

/**
 * Created by sasha on 09.12.15.
 */
public class TestThread extends Thread {

    private int uid;

    @Override
    public void run() {
        int i ;
        Random r = new Random();
        r.setSeed(10);
        uid = r.nextInt();

        for(i=0; i<10; i++)
            System.out.println(getName() + " " + uid + " " + i);

        try {
            sleep(10,10);
        } catch (InterruptedException e) {
            // Eсли не ставить ретурн, поток проснется

            // Чтобы завершить явно прерванный поток:
            // if (Thread.currentThread().isInterrupted()) return;

            return;
        }
    }
}
