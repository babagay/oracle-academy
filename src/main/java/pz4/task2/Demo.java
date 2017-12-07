package pz4.task2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by babagay on 29.11.15.
 *
 * демонстрирует работу всех методов контейнера.
 *
 * TODO как сделать возможным вызов итератора на самой очереди: for ( Number number : deque )?
 * TODO тесты
 */
public class Demo {

    public static void main(String[] args) {

        MyDeque<Number> deque = new MyDequeImpl<>();

        deque.addFirst(433);
        deque.addFirst( 4 );
        deque.addLast(8.88);
        deque.addLast( 51L );
        deque.addFirst( 525 );
        deque.addFirst( 8.2 );
        deque.addLast( 90 );
        deque.addLast( 99 );

        //System.out.println( deque.removeFirst() );  // Ok
        //System.out.println( deque.removeLast() );  // Ok

        //System.out.println(deque);  // Ok

        //System.out.println( deque.toList() ); // Ok

        //System.out.println( deque.getFirst() );

        //System.out.println( deque.getLast() );

        //System.out.println(deque.size()); // Ok

        //deque.toList().forEach( System.out::println ); // ok

        //System.out.println("Deque contains 8.88 --> " + deque.contains(8.88)); //Ok

//        deque.clear(); // Ok
//        System.out.println( deque.size() );
//        System.out.println( deque.getLast()  );
//        System.out.println(deque.getFirst());

        // Ок
//        Object[] numbers =  deque.toArray();
//        System.out.println(numbers.length);
//        System.out.println(numbers[0]);
//        System.out.println( numbers[1]);

          // Ok
          // проверить, содержит ли очередь все элементы списка deque
//        List<Number> list = new ArrayList<>(  );
//        list.add( 8.2 );
//        list.add( 90 );
//        list.add( 8.88 );
//        list.add( 51L );
//        System.out.println( deque.containsAll(list) );

    }

}
