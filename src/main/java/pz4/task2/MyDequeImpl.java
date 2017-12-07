package pz4.task2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Deque
 * Created by babagay on 29.11.15.
 * todo обратный итератор
 * FIXME Не смотря на кастинг arr = ( E[] ) objects;, метод toArray() возвращает массив Object'ов
 */
public class MyDequeImpl<E> implements MyDeque, Iterable {


    //private List<E> que;

    private Node<E>      first;
    private Node<E>      last;
    public Iter<Node<E>> iterator;

    private Optional<Node<E>> firstOptional;

    private static int nodeCount = 0;

    public Optional<Node<E>> getFirstOptional() {

        setFirstOptional( first );

        return firstOptional;
    }

    /**
     *
     if( getFirstOptional().equals( Optional.empty() ) ){
     return ""
     } else {
     return getFirstOptional().get()
     }


     * @param firstOptional
     */
    public void setFirstOptional(Node<E> firstOptional) {
        this.firstOptional = Optional.ofNullable(firstOptional);
    }

    @Override
    public void addFirst( Object o ) {
        E newNode = ( E ) o;

        if ( nodeCount == 0 ) {
            first = new Node<E>( newNode, null, null );
            last = first;
        }

        if(nodeCount == 1){
            first = new Node<E>( newNode, null, last);
            last.prev = first;
        }

        if(nodeCount > 1){
            Node<E> tmp = first;
            first = new Node<E>( newNode, null, first);
            tmp.prev = first;
        }

        nodeCount++;
    }

    @Override
    public void addLast(Object o) {

        E newNode = (E) o;

        if ( nodeCount == 0 ) {
            first = new Node<E>(newNode, null, null);
            last = first;
        }

        if ( nodeCount == 1 ) {
            last = new Node<E>(newNode, first, null);
            first.next = last;
        }

        if(nodeCount > 1){
            Node<E> tmp = last;
            last = new Node<>(newNode, last, null);
            tmp.next = last;
        }

        nodeCount++;
    }

    @Override
    public Object removeFirst() {

        Node<E> firstOld = first;

        first = first.next;

        nodeCount--;

        return firstOld.getElement();
    }

    @Override
    public Object removeLast() {

        Node<E> lastOld = last;

        last = last.prev;

        nodeCount--;

        return lastOld.element;
    }

    @Override
    public E getFirst() {

        if(first == null)
            return null;

        return first.getElement();
    }

    @Override
    public Object getLast() {

        return (last == null) ? null : last.getElement();
    }

    @Override
    public boolean contains(Object o) {

        E elem = (E) o;

        clearIterator();

        //FIXME так работает, но поток надо закрыть. Или не надо?
        //return toStream().filter(e -> e.equals(elem)).count() > 0;

        Stream<E> stream = toStream();

        boolean r = stream.filter( e -> e.equals(elem) ).count() > 0;

        stream.close();

        return r;
    }

    @Override
    //TODO надо ли обнулять последовательно все объекты или только ссылку на очередь
    public void clear() {

       Node<E>  current = last;

       while (current != null) {
           last = current;
           current = current.getPrev();
           last = null;
           nodeCount--;
       }

        first = null;
    }


    public void clearIterator(){
        iterator.clear();
    }

    @Override
    public E[] toArray() {

        Stream<E> stream = ( Stream<E> ) toStream();

        clearIterator();

        ArrayList<E> arrayList = stream.collect( ArrayList::new, ArrayList::add, ArrayList::addAll );

        E[] arr;

        Object[] objects = arrayList.toArray( new Object[arrayList.size()] );

        arr = ( E[] ) objects;

        clearIterator();
        stream.close();

        return arr;
    }

    @Override
    public int size() {
        return nodeCount;
    }

    @Override
    public boolean containsAll( List list ) {

        boolean yes = true;

        for ( Object o : list ) {
            Stream<E> stream = toStream();
            yes = stream.anyMatch( e ->  e.equals( o ) );
            if(yes == false) {
                stream.close();
                return yes;
            }
            stream.close();
        }

        return yes;
    }

    private Stream<E> toStream(){
        clearIterator();
        return Stream.generate( () -> {
                    Node node = (Node) this.iterator.next();
                    return (E) node.getElement();
                }
        ).limit( nodeCount );
    }

    public List<E> toList()  {

        Stream<E> stream = toStream();

        clearIterator();

        List<E> nodeList = stream.collect( Collectors.toList() );

        stream.close();

        return nodeList;
    }

    /**
     * Конструктор
     */
    public MyDequeImpl() {
        iterator = new MyDequeImpl.Iter ();
    }

    @Override
    public Iterator iterator() {

        clearIterator();

        return iterator;
    }


    /**
     * Node nested class
     * Could get static fields of Deque only
     * @param <E>
     */
    private static class Node<E> {

        E       element;
        Node<E> next;
        Node<E> prev;

        Node( E element, Node<E> prev, Node<E> next ) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        public E getElement(){
            return element;
        }

        public String toString() {
            return element + "";
        }

        public Node getPrev(){
            return prev;
        }

        public Node getNext(){
            return next;
        }
    }

    private class Iter<Node> implements Iterator  {

        private int              cursor;
        private MyDequeImpl.Node current;
        private Node             next;

        @Override
        public boolean hasNext() {
            return cursor != nodeCount;
        }

        @Override
        public Object next() {

            if ( cursor > nodeCount ) throw new NoSuchElementException( "No such element" );

            if(cursor == 0) {
                current = first;
            }

            if(cursor > 0){
                current = current.getNext();
            }

            cursor++;

            return current;
        }

        Iter() {
            clear();
        }

        public void clear() {
            cursor = 0;
        }
    }

    public String toString() {

        String s = "[";

        for ( Object o : this ) {
            s += o + "; ";
        }

        s = s.replaceAll( "(?<start>.*)(?<tail>; )$", "${start}" );

        return s + "]";
    }
}

