package Threads;

/**
 * Created by sasha on 09.12.15.
 *
 * TODO AutoStart
 */
public class App {

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new TestThread();
        Thread thread2 = new TestThread();
        Thread thread3 = new TestThread();
        Thread thread4 = new TestThread();

        // Создание через Runnable
        TestThreadRunnable runnable = new TestThreadRunnable();

        Thread thread5 = new Thread(runnable);

//        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();

        thread5.start();

        // Создание через лямбду
        Thread thread6 = new Thread( () ->
            {
                int i;
                for(i=0; i<10; i++)
                    System.out.println(  Thread.currentThread().getName() + " Lambda - "  + " " + i);
            },
                "Lamda Thread"
        );

        thread6.start();

        // Мэйн будет ждать завершения thread5
        try {
            thread5.join();
        } catch (InterruptedException e) {
        }

        Thread thread7 = new Thread( () -> {
            int i = 0;
            for(;;){
                i++;
                System.out.println(i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    //  После 10 мс Программа заходит сюда, и если здесь нет return, работает бесконено
                     return;
                }
            }
        });

        // FIXME что дает превращение потока в демон?
        thread7.setDaemon(true);

        thread7.start();

        // [!] Ошибка. Так thread7 будет крутиться бесконечно
        // thread7.join();

        Thread.sleep(100);

        thread7.interrupt();

        thread7.join();


        System.out.println("======= "+ Thread.currentThread().getName() +" end ========");
    }


}
