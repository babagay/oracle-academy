package generics.model;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GenericStorage<K, V> {

    private List<K> keys;
    private List<V> values;

    public GenericStorage(K key,V val){
        setKeys(new ArrayList<K>((Collection<? extends K>) key));
        setValues(new ArrayList<V>((Collection<? extends V>) val));
    }

    public GenericStorage(){
        setKeys(new ArrayList<K>());
        setValues(new ArrayList<V>());
    }

    Report buildReport(){

        Report report = new Report();

        return report;
    }

    public boolean put(K key, V val){

        if( !getKeys().contains(key) ){
            keys.add(key);
            values.add(val);

            return true;
        }

        return false;
    }

    public V get(K key){
        if( keys.contains(key) ){
            return getValues().get( getKeys().indexOf(key) );
        }

        return null;
    }

    public boolean delete(K id){
        int index = getKeys().indexOf(id);

        if(index != -1){
            getKeys().remove(index);
            getValues().remove(index);
            return true;
        }

        return false;
    }

    /**
     * Возвращаем другой список вместо values,
     * чтобы не менять исходный список, но объекты внутри этого нового списка (айтемы) менять Можно
     * @return
     */
    public List<V> getAll() {

        return new ArrayList<>( getValues() );
    }

    public List<K> getKeys() {
        return keys;
    }

    public void setKeys(List<K> keys) {
        this.keys = keys;
    }

    public List<V> getValues() {
        return values;
    }

    public void setValues(List<V> values) {
        this.values = values;
    }
}
