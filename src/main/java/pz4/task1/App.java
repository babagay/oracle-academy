package pz4.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by babagay on 29.11.15.
 */
public class App {

    private List<Integer> arr01 = new ArrayList<>();
    private List<String> arr02 = new ArrayList<>();
    private List<Computer> arr03 = new ArrayList<>();
    private List<Auto> arr04 = new ArrayList<>();

    public static void main(String[] args) {

        App app = new App();

        app.arr01.add(120);
        app.arr01.add(20);
        app.arr01.add(1000);
        app.arr01.add(50);

        app.arr02.add("Christine");
        app.arr02.add("Stephani");
        app.arr02.add("Anabel");
        app.arr02.add("Leela");


        app.arr03.add(new Computer("Samsung", "AMD 64", "8GB DDR3"));
        app.arr03.add(new Computer("Apple", "Core i5", "16GB DDR3"));
        app.arr03.add(new Computer("Benq", "Core i7", "12GB DDR3"));

        app.arr04.add(new Auto("Lamborgini", "red", "600 ls"));
        app.arr04.add(new Auto("Dodge Viper", "blue", "500 ls"));
        app.arr04.add(new Auto("Pobeda", "pale", "50 ls"));

        //app.arr01.sort((o1, o2) -> o1.compareTo(o2));

        System.out.println(app.arr03);

        Collections.sort(app.arr01);
        Collections.sort(app.arr02);
        Collections.sort(app.arr03);

        /** Ошибка компиляции  */
        //Collections.sort(app.arr04);

        System.out.println(app.arr01);
        System.out.println(app.arr02);
        System.out.println(app.arr03);


    }

}
