package generics.dao;

import generics.model.GenericStorage;
import generics.model.User;

import java.util.List;

/**
 * Created by student on 11/21/2015.
 */
public class DAOUser implements interfaceDAO<User>  {

    final private static GenericStorage<Long,User> storage = new GenericStorage<>();

    public static long id = 0;

    {
        /**
         * [!] Если добавить эту замолодь, то при каждом вызове класса id будет реинициализироваться, т.е. механизм автоинкремента отвалится
         */
        // id = 1;
    }

    @Override
    public long create(User newItem) {

        if( storage.put(newItem.getId(), newItem) )
            return newItem.getId();

        return 0;
    }

    @Override
    public User read(long id) {
        return storage.get( new Long( id ) );
    }

    @Override
    public boolean update(User item) {

        if( read(item.getId()) != null ){
            /**
             * TODO
             * Создать временного юзера
             * Удалить юзера по индексу из стораджа
             * Записать юзера в сторадж под тем же индексом
             * В случае неудачи записать в сторадж временного юзера
             */
        }

        return false;
    }

    @Override
    public boolean delete(long id) {

        return storage.delete(id);

    }

    public boolean delete(User user) {

        return storage.delete(user.getId());

    }

    @Override
    public List<User> readAll() {
        return storage.getAll();
    }

    public static List<User> readAllStatic(){
        return storage.getAll();
    }

}
