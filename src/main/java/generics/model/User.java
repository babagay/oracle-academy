package generics.model;

import generics.dao.DAOUser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by student on 11/21/2015.
 */
public class User extends ModelAbstract {

    private long id;
    private String name;
    private String login;
    private String password;
    private Date birthday;

    /**
     * @deprecated
     */
    private List<Book> books;

    public User(){
    }

    public User(long id,String name,String login,String password,Date birthday ){
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.birthday = birthday;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String toString(){
        return id + " " + name + " " + login + " " + birthday;
    }

    @Override
    public boolean deployTable() {

        reader.lines().forEach(s -> {
                    String[] arr = s.split("\\|");

                    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ROOT);

                    DAOUser daoUser = new DAOUser();

                    try {
                        daoUser.create( new User( ++DAOUser.id, arr[0], arr[1], arr[2], dateFormat.parse( arr[ 3 ] ) ) );
                    } catch ( ParseException e ) {
                        //e.printStackTrace();
                    }
                });

        return true;
    }

    /**
     * Возвращает запись из таблицы по заданному ID
     *
     * @param id
     * @return
     */
    public static User get(long id){
        return new DAOUser().read( id );
    }

    public static List<User> getAll(){
        return new DAOUser().readAll();
    }

    public static boolean set(User user) {

        long index = user.getId();
        User existinguser = get(index);

        if (existinguser != null) {
            return new DAOUser().update(user);
        } else {
            long newId = new DAOUser().create(user);
            if (newId > 0)
                return true;
        }

        return false;
    }
}
