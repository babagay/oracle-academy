package calc;

/**
 * Created by babagay on 01.03.16.
 *
 *
 * Программа приостанавливается после вывода двойки и продолжает крутиться в фоне, ожидая команды продолжения
 */
public class Trun extends Thread {

    public void run(){
        System.out.println("1");
        yield();
        System.out.println("2");
        suspend();
        System.out.println("3");
        resume();
        System.out.println("4");
    }

    public static void main(String[] args) {
        Trun t = new Trun();
        t.start();
    }

}
