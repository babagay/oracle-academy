package generics.model;

import generics.dao.DAOBook;
import generics.dao.DAOUser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Book extends ModelAbstract {

    private long id;
    private String isbn;
    private String author;
    private String title;
    private int count;

    private Date borrowDate;
    private Date returnDate;

    /**
     * @deprecated
     */
    private List<User> users;

    /**
     * @deprecated
     */
    private List<Report> report;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Report> getReport() {
        return report;
    }

    public void setReport(List<Report> report) {
        this.report = report;
    }

    public String getIsbn() {       return isbn;    }

    public void setIsbn( String isbn ) {        this.isbn = isbn;    }

    public Book(){
    }

    public Book clone(){
        Book book = new Book();

        book.setId( this.getId() );
        book.setIsbn( this.getIsbn() );
        book.setAuthor( this.getAuthor() );
        book.setTitle( this.getTitle() );
        book.setCount( this.getCount() );

        return book;
    }

    public Book(long id,String isbn, String author, String title, int count){
        this.id = id;
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.count = count;
    }

    @Override
    public boolean deployTable() {
        reader.lines().forEach(s -> {
                    String[] arr = s.split("\\|");

                        DAOBook.id++;

                        DAOBook daoBook = new DAOBook();

                        daoBook.create( new Book( DAOBook.id, arr[0], arr[1], arr[2], Integer.parseInt( arr[3] ) ) );
                });

        return true;
    }

    public String toString(){
        return id + " " + isbn + " " + author + " " + title + " " + count + " Borrow: [" + borrowDate + "]  Return: [" + returnDate + "] ";
    }

    public static Book get(long id){
        return new DAOBook().read(id);
    }

    public static boolean set(Book book) {

        long index = book.getId();
        Book existingBook = get(index);

        if (existingBook != null) {
            return new DAOBook().update(book);
        } else {
            long newId = new DAOBook().create(book);
            if (newId > 0)
                return true;
        }

        return false;
    }

    public static boolean remove(Book book) {
        return new DAOBook().delete(book.getId());
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
