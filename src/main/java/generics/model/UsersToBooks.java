package generics.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * [!] Класс обобщённым НЕ получился
 */
public class UsersToBooks<U extends User, B extends Book> extends ModelAbstract {

    //private HashMap<U, Set<B>> relation;
    final private static HashMap<User, Set<Book>> relation = new HashMap();

    private static Book bookTmp = null;

    private final int BORROW_PERIOD = 10;

    public UsersToBooks() {
        //relation = new HashMap<>();
    }

    @Override
    public boolean deployTable() throws Exception {

        if ( true )
            throw new Exception( "Not implemented" );

        return false;
    }

    /**
     * TODO проверять, есть ли такой юзер в GenericStorage of USers
     * TODO проверять, есть ли такая книга в GenericStorage of Books
     * @param user
     * @param book
     */
    public void addRelation(U user, B book) throws BookIsNotAvailableException {

        Set<Book> userBooks =  relation.get(user);

        //TODO узнать, совпадает ли book.getCount() и Book.get(book.getId()).getCount()
        int bookCount = Book.get(book.getId()).getCount();

        if (bookCount < 1)
            throw new BookIsNotAvailableException("");

        try {
            if (userBooks.size() > 0)
                 userBooks.add( compileNewBook(book) );

        } catch (NullPointerException e) {
            ////System.out.println("User " + user.getName() + " has not borrowed any books yet");
            userBooks = new HashSet<>();
            userBooks.add( compileNewBook(book) );
        }

        relation.remove( user );
        relation.put( user, userBooks );

        book.setCount( --bookCount );
        Book.set( book );

        ////  System.out.println(relation.get(user));
    }

    /**
     * Удалить книгу из связи с пользователем
     * @param user
     * @param book
     * @return
     */
    public boolean removeRelation(U user, B book){

        Set<Book> userBooks =  relation.get(user);

        try {
            // [!] Так не работает, потому что в коллекции лежат КОПИИ объектов книг
            userBooks.remove( book );
            relation.remove( user );
            relation.put( user, userBooks );
        } catch ( NullPointerException e ){}

        // Работает либо так,
        userBooks.forEach(
                book1 -> {
                    if ( book1.getId() == book.getId() ) bookTmp = book1;
                }
        );

        if( bookTmp != null ) {
         // userBooks.remove( bookTmp );
            bookTmp = null;
        }

        // ...либо вот так
        userBooks.removeIf( book1 -> book1.getId() == book.getId() );

        int bookCount = Book.get(book.getId()).getCount();
        book.setCount( ++bookCount );
        Book.set( book );

        return true;
    }

    /**
     * Полностью удалить пару "пользователь - коллекция книг"
     * @param u
     * @return
     */
    public boolean removeWholeUserCollection(User u){

        relation.remove( u );

        return true;
    }

    /**
     * Полностью удалить книгу из таблицы
     * @param b
     * @return
     */
    public boolean releaseBook(B b){

        // [!] Так не работает
        // ConcurrentModificationException
        for ( User user : relation.keySet() ) {
            // removeRelation( ( U ) user, b );
        }

        // и так тоже
        // relation.keySet().stream().filter( user -> hasBook( (U)user, b ) ).forEach( user1 -> removeRelation( (U) user1, ( B ) b ) );

        for ( User filteredUser : reportByBook(b) )
            removeRelation( ( U ) filteredUser, b );

        return true;
    }

    /**
     * Список юзеров, связанных с книгой
     * @param item
     * @return
     */
    public List<User> reportByBook(B item){

        return relation.keySet().stream().filter( user -> hasBook( (U)user, item ) ).collect( Collectors.toList() );

    }

    /**
     * Взята ли данная книга пользователем
     * @param user
     * @param book
     * @return
     */
    public boolean hasBook(U user, B book){
        return relation.get( user ).stream().filter( book1 -> book.getId() == book1.getId() ).count() > 0 ? true : false;
    }

    /**
     * Собирает новый объект книги с датами взятия и возвращения
     * @param item
     * @return
     */
    private Book compileNewBook(B item) {
        Book newBook = item.clone();

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.US);

        String borrowD = dateFormat.format( new Date() );  // now date

        Calendar calendar = Calendar.getInstance();
        calendar.add( Calendar.DAY_OF_MONTH, BORROW_PERIOD );

        String returnD = dateFormat.format(calendar.getTime()); // future date

        try {
            Date now = dateFormat.parse( borrowD );
            Date shifted = dateFormat.parse( returnD );

            newBook.setBorrowDate( now );
            newBook.setReturnDate(  shifted );

        } catch ( ParseException e ) {
        }

        /// System.out.println(newBook);

        return newBook;
    }

    public Set<Book> getBooksByUser( U user ) {
        if (User.get(user.getId()).getId() > 0)
            return  relation.get(user);

        return null;
    }


}


