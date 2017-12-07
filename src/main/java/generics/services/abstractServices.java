package generics.services;

import generics.model.Book;
import generics.model.User;

import java.util.List;

/**
 *
 * - Добавить пользователя

 - Удалить пользователя

 - Добавить книгу

 - Удалить книгу

 - Найти книгу

 - Взять книгу

 - Вернуть книгу

 - История по книге

 - История по пользователю

 */
public abstract class abstractServices {


    public abstract User addUser();
    public abstract User addUser(String userName, String Login, String Pass, String Birthday);

    public abstract boolean delUser(long id);
    public abstract boolean delUser(User user);

    public abstract Book addBook(String isbn, String author, String title, int count);
    public abstract boolean delBook(long id);
    public abstract boolean delBook(Book book);

    public abstract List<Book> findBook(String name);

    public abstract Book getBook();
    public abstract Book refundBook();

    /**
     * todo Какой тип оно возвращает?
     */
    public abstract List<User> getHistoryByBook( long id );
    public abstract Book getHistoryByBook(Book book);

    /**
     * todo Какой тип оно возвращает?
     */
    public abstract List<Book> getHistoryByUser(long id);
    public abstract List<Book> getHistoryByUser(User user);

}
