package calc;

/**
 * Created by babagay on 01.03.16.
 */
public class WaterBottle {

    private static String s;
    private static boolean b;

    private String brand;
    private boolean empty;

    public static void main(String[] args) {

        WaterBottle wb = new WaterBottle();

        System.out.print("Empty = " + wb.empty);

        System.out.print(", Brand = " + wb.brand);

        //System.out.println( s + " " + b );

        try {
            System.out.println("Try");
            return;
        } catch (Exception e) {}
        finally {
            System.out.println("Finally");
        }
    }
}
