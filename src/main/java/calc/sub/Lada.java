package calc.sub;

import calc.Truck;

/**
 * Created by babagay on 01.03.16.
 */
public class Lada   {

    // public  String color = "red+"; // Ok
    // private String color = "red+"; // Ok
    // protected     String color = "red+";

    public static void main(String[] args) {

        Lada lada = new Lada();

        Truck truck = new Truck();

        //System.out.println( truck.color ); // НЕ доступно из подпакета
    }
}
