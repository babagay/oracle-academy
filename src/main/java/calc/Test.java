package calc;


/**
 * Created by babagay on 29.02.16.
 *
 * Данный код выведет Tort Tort.
 * Вроде как должен работать полиморфизм, и, как известно,
 * выбор осуществляется по типу объекта, на который ссылается ссылка,
 * а не по типу самой ссылки. Но в джаве есть переопределенные методы. И всё.
 * Переопределенных полей либо конструкторов просто не существует.
 * То есть реализация метода всегда выбирается в зависимости от объекта,
 * на который мы ссылаемся. Поля класса, наоборот, выбираются исходя из типа ссылки,
 * и переопределение тут не работает. Поэтому мы и получаем такой результат.
 */
public class Test extends Super {

    public String name = "Habr";

    public String getName() {
        return name + " (Test)  ";
    }

    public static void main(String[] args) {

        /**
         * переменная s - это ссылка типа Super
         * Test (конструируемый класс) - это тип объекта, на который ссылается ссылка
         *
         * [!] здесь неявно генерится конструктор без параметров,
         * в теле которого вызывается super()
         */
        Super s = new Test();

        System.out.println(s.name + " " + s.getName());
    }
}

class Super {

//    Super(String ... args){
//        System.out.println("Super");
//    }

    public String name = "Tort";

    public String getName() {
        return name;
    }
}
