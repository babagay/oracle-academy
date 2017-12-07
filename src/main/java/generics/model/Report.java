package generics.model;

import java.util.Date;

/**
 *
 Отчет содержит
 в себе информацию по книге, по пользователю который взял данную книгу и даты
 аренды/возврата книги.

 кто взял
 что взял
 когда взял
 когда должен вернуть
 */
public class Report  {
    private long id;
    private Book book;
    private User user;
    private Date rent;
    private Date returns;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getRent() {
        return rent;
    }

    public void setRent(Date rent) {
        this.rent = rent;
    }

    public Date getReturns() {
        return returns;
    }

    public void setReturns(Date returns) {
        this.returns = returns;
    }
}

