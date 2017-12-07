package calc;

import java.util.Date;

/**
 * Created by babagay on 01.03.16.
 */
public class Calendar {

    public static void main(String[] args) {

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime( new Date() );

        // Определить номер текущего дня недели
        int day = calendar.get(java.util.Calendar.DAY_OF_WEEK );

        System.out.println(day);


    }

}
