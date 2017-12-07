package generics.dao;

import generics.model.Book;
import generics.model.GenericStorage;
import generics.model.User;

import java.util.List;

/**
 * Created by student on 11/21/2015.
 */
public class DAOBook implements interfaceDAO<Book> {

    public static        int                        id      = 0;
    final private static GenericStorage<Long, Book> storage = new GenericStorage<>();

    @Override
    public long create( Book newItem ) {

        if( storage.put( newItem.getId(), newItem ) )
            return newItem.getId();

        return 0;
    }

    @Override
    public Book read( long id ) {
        return storage.get( id );
    }

    @Override
    public boolean update( Book item ) {

        long index = item.getId();
        Book existingBook = read(index);

        if (existingBook != null) {

            delete(index);

            if (create(item) > 0)
                return true;

            create(existingBook);
        }

        return false;
    }

    @Override
    public boolean delete(long id) {
        return storage.delete(id);
    }

    @Override
    public List<Book> readAll() {
        return storage.getAll();
    }

    public static List<Book> readAllStatic(){
        return storage.getAll();
    }
}
