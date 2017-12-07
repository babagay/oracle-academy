package Threads;

/**
 * Created by sasha on 09.12.15.
 */
public class App2 {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Counter());
        Thread t2 = new Thread(new Counter());
        Thread t3 = new Thread(new Counter());

        t1.start();
        t2.start();
        t3.start();

        // Если приджойнить мейн к потокам, println сработает после их завершения
        //t1.join();
        //t2.join();
        //t3.join();

        // i на выходе получается любым от 0 до 9
        System.out.println( "[" + Inc.i  + "]");

    }

}
