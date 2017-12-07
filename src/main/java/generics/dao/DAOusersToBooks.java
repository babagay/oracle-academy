package generics.dao;

import generics.model.Book;
import generics.model.GenericStorage;
import generics.model.User;
import generics.model.UsersToBooks;

import java.util.List;

/**
 * FIXME
 * Здесь надо наследоваться от другого интерфейса
 * Например, create() будет принимать 2 параметра
 */
public class DAOusersToBooks implements interfaceDAO  {

    final private static UsersToBooks<User,Book> storage = new UsersToBooks();

    @Override
    public long create( Object newItem ) {


        return 0;
    }

    @Override
    public Object read( long id ) {
        return null;
    }

    @Override
    public boolean update( Object item ) {
        return false;
    }

    @Override
    public boolean delete( long id ) {
        return false;
    }

    @Override
    public List readAll() {
        return null;
    }
}
