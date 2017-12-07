package Threads;

/**
 * Created by sasha on 09.12.15.
 */
public class Counter implements Runnable {
    @Override
    public void run() {
        Inc.increment();
        Inc.increment();
        Inc.increment();
    }
}
