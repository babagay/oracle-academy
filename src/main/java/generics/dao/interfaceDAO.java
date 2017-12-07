package generics.dao;

import java.util.List;

/**
 * CRUD
 */
public interface interfaceDAO<T> {

    long create(T newItem);
    T read(long id);
    boolean update(T item);
    boolean delete(long id);

    List<T> readAll();

}
