package pz4.task2;

import java.util.List;
import java.util.Optional;

/**
 * Created by babagay on 29.11.15.
 */
public interface MyDeque<E> {

    // добавить элемент в начало списка
    void addFirst(E e);

    // добавить элемент в конец списка
    void addLast(E e);

    // получить элемент из начала списка и удалить его
    E removeFirst();

    // получить элемент из конца списка и удалить его
    E removeLast();

    // получить элемент из начала списка, не удаляя его
    E getFirst();

    // получить элемент из конца списка, не удаляя его
    E getLast();

    // проверить, содержится ли объект o в списке
    // (с помощью equals)
    boolean contains(Object o);

    // очистить список
    void clear();

    public void clearIterator();

    // вернуть массив элементов из списка
    // (сохраняя упорядоченность элементов списка)
    <E> E[] toArray();

    // вернуть количество элементов в списке
    int size();

    // проверить, содержит ли список все элементы списка deque
    // boolean containsAll(MyDeque<? extends E> deque); - Это ошибка
    boolean containsAll(List<? extends E> list);

    List<E> toList();

}
