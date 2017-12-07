package calc;

/**
 * Truck
 *
 * Created by babagay on 01.03.16.
 */
class Automobile {

    protected String color = "blue";

    public void drive(){
        System.out.println("Automobile: drive");
    }

    public void print(String s){
        System.out.println( "PArent " + s );
    }

}

public class Truck extends Automobile {

    int count;

    protected String color = "red";

    public void drive(){
        System.out.println("Truck: drive");
    }

    public void print(String s){
        //System.out.println( "Child " + s );

        super.print(s);
    }

    public void Truck(){ // [!] Это НЕ конструктор
        System.out.println("new Tr");
        count = 4;
    }

    public Truck() { // [!] Это конструктор
    }

    public static void main(String[] args) {
        Automobile automobile = new Automobile();
        Truck t = new Truck();
        automobile.drive();
        t.drive();
        automobile = t;

        // Ссылка типа Automobile указывает на объект типа Truck -
        // будет загружен метод по типу объекта, т.е. Truck.drive()
        // [!] Хотя, Idea подсказывает неправильно
        automobile.drive();

        //t.print("print tr");

        System.out.println(t.count);


        // byte B = 1;
        // byte C = -B; // Error: incompatible types

        // int x = 1;
        // byte B = x; // Error: incompatible types



    }

}
