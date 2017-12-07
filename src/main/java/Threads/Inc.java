package Threads;

/**
 * Created by sasha on 09.12.15.
 */
public class Inc {

    public static /* volatile */ int i;

     private final static Object monitor = new Object();

    /**
     * Без synchronized числа выводятся вразнобой
     * Другой вариант - использовать монитор
     * Блокируются ВСЕ синхронизированные блоки
     * В нестатическом объекте можно синхронизировать по this
     *
     * Вложенные блоки synchronized могут привести к дедлоку
     */
    public /* synchronized */ static void increment(){

        synchronized (monitor) {
            i++;
            System.out.println(i);
        }
    }

}
