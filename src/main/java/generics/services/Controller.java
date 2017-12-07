package generics.services;

import generics.dao.DAOBook;
import generics.dao.DAOUser;
import generics.model.Book;
import generics.model.BookIsNotAvailableException;
import generics.model.User;
import generics.model.UsersToBooks;

import java.io.BufferedReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * FIXME
 * addBook()
 */

public class Controller extends abstractServices {

    private static BufferedReader reader;
    private static String usersTable = "./src/main/resources/Users.txt";
    private static String booksTable = "./src/main/resources/Books.txt";

    private DAOUser userStorageInterface;
    private DAOBook bookStorageInterface;

    private static UsersToBooks u2b = new UsersToBooks<User, Book>();

    private static List<Book> foundBooks = new ArrayList<>();


    public static void main( String[] args ) throws Exception {

        Controller controller = new Controller();

        /** Добавить пользователя  */
        controller.addUser( "Masha Rasputina", "_mashka_", "M_1234", "05.06.1980" );

        /** Добавить книгу */
        controller.addBook( "ISBN:234898", "Dostoyevskiy", "The Brothers Caramazovs", 1 );

        if ( new User().setPath( usersTable ).initReader().deployTable() ) {
            if ( new Book().setPath( booksTable ).initReader().deployTable() ) {

                /** Удалить пользователя */
                //controller.delUser( 4 );

                /** Вывод таблиц целиком */
                //controller.getAllUsers().forEach(System.out::println);
                //controller.getAllBooks().forEach( System.out::println );

                /** Взять книгу */
                takeAbook(User.get(1), Book.get(1));
                takeAbook(User.get(1), Book.get(2));
                takeAbook(User.get(1), Book.get(4));

                takeAbook(User.get(2), Book.get(4));
                takeAbook(User.get(2), Book.get(3));

                takeAbook(User.get(3), Book.get(4));
                takeAbook(User.get(3), Book.get(3));

                /** Вернуть книгу */
                //returnAbook( User.get(1), Book.get(1) );

                /** Cписок книг пользователя */
                try {
                   // bookByUser( User.get( 1 ) ).forEach( System.out::println );
                } catch ( NullPointerException ne ){}

                /** Удалить книгу */
                // controller.delBook( Book.get( 3 ) );

            }
        }

        /** Найти книгу */
        //System.out.println(controller.findBook( "Galaxy hitchhiking" ));
        //System.out.println(controller.findBook( "Expirience of fool man" ));

        /** История по книге */
        //System.out.println( controller.getHistoryByBook( 3 ) );

        /** История по пользователю */
        //System.out.println(  controller.getHistoryByUser( User.get(3) ) );


    }

    public Controller(){
        userStorageInterface = new DAOUser();
        bookStorageInterface = new DAOBook();
    }


    @Override
    public Book addBook(String isbn, String author, String title, int count) {

       if ( bookStorageInterface.create(new Book(++DAOBook.id, isbn, author, title, count )) > 0 )
           return Book.get( DAOBook.id );

        return null;
    }

    @Override
    public boolean delBook(long id) {

         u2b.releaseBook( Book.get( id ) );

        return bookStorageInterface.delete( id );
    }

    @Override
    public boolean delBook(Book book) {

         u2b.releaseBook( book );

        return bookStorageInterface.delete( book.getId() );
    }

    /**
     * Простой вариант реализации, работающий на ПОЛНОЕ соответствие
     * @param name
     * @return
     */
    @Override
    public List<Book> findBook(String name) {

        //DAOBook.readAllStatic().stream().allMatch( book -> book.getTitle().equals(name) );

        foundBooks = new ArrayList<>();

        DAOBook.readAllStatic().stream().forEach(
            book -> {
                if( book.getTitle().equals( name ) ){
                    foundBooks.add( book );
                }
            }
        );

        return foundBooks;
    }

    @Override
    public Book getBook() {
        return null;
    }

    @Override
    public Book refundBook() {
        return null;
    }

    @Override
    public List<User> getHistoryByBook( long id ) {

        return u2b.reportByBook( Book.get(id) );

    }

    @Override
    public Book getHistoryByBook(Book book) {
        return null;
    }

    @Override
    public List<Book> getHistoryByUser(long id) {
        return null;
    }

    @Override
    public List<Book> getHistoryByUser(User user) {
        return ( List<Book> ) u2b.getBooksByUser( user ).stream().collect( Collectors.toList() );
    }

    public List<User> getAllUsers() {
        return DAOUser.readAllStatic();
    }

    public List<Book> getAllBooks() {
        return DAOBook.readAllStatic();
    }

    /** Интерфейс */

    /**
     *  @deprecated
     */
    @Override
    public User addUser() {
        return null;
    }

    @Override
    public User addUser(String userName, String Login, String Pass, String Birthday) {

        Date date = null;

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
        try {
            date = dateFormat.parse(Birthday);
        } catch (ParseException e) {
            //e.printStackTrace();
        }

        // Pass можно шифровать

        User user = new User(++DAOUser.id, userName, Login, Pass, date);

        DAOUser userStorageInterface = new DAOUser();

        userStorageInterface.create( user );

        return user;
    }

    @Override
    public boolean delUser(long id) {

        u2b.removeWholeUserCollection( User.get(id) );

        return userStorageInterface.delete( id );
    }

    @Override
    public boolean delUser(User user){

        u2b.removeWholeUserCollection( user );

        return userStorageInterface.delete( user.getId() );
    }

    public static void takeAbook(User u, Book b) {
        try {
            u2b.addRelation(u, b);
        } catch (BookIsNotAvailableException e) {
            //e.printStackTrace();
        }
    }

    public static void returnAbook(User u, Book b){
        u2b.removeRelation(u,b);
    }

    public static Set<Book> bookByUser(User user){
        return u2b.getBooksByUser( user );
    }
}
