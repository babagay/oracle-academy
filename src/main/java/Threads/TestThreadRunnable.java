package Threads;

import java.util.Random;

/**
 * Created by sasha on 09.12.15.
 */
public class TestThreadRunnable implements Runnable{

    private int uid;

    @Override
    public void run() {
        int i ;
        Random r = new Random();
        uid = r.nextInt();

        // Ссылка на поток - через Thread.currentThread()

        for(i=0; i<10; i++)
            System.out.println(  Thread.currentThread().getName() + " Runnable -" + uid + " " + i);
    }

}
