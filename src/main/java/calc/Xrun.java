package calc;

/**
 * Created by babagay on 01.03.16.
 *
 * Будут выведены пары одинаковых значений х и у,
 * причём каждая пара НЕ встретится дважды.
 * Числа в парах будут идти строго последовательно от 1 до 20
 */
public class Xrun implements Runnable {

    private int x;
    private int y;

    @Override
    public synchronized void run() {
        for (int i = 0; i<10; ++i){
            x++;
            y++;
            System.out.println(x + " " + y);
        }
    }

    public static void main(String[] args) {
        Xrun that = new Xrun();

        (new Thread(that)).start();
        (new Thread(that)).start();
    }
}
