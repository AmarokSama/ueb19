import java.util.Collection;
import java.util.Iterator;
import java.util.*;

/**
 * Write a description of class MinHeap here.
 *
 * @author Nico Gruschke
 * @version 1.0
 */
public class MinHeap<E> implements Queue<E>
{

    private LinkedList<E> List;
    
    /**
     * The standard Constructor how create a queue
     *
     */
    public MinHeap(){
        List = new LinkedList<E>();
    }
    
    /**methode offer
     * 
     * @return null if the queue is empty
     *          or the Head of this queue
     */
    @Override
    public boolean offer(E e) {
        // TODO Auto-generated method stub
        return true;
    }
    
    /**Methode peek
     * 
     * @return null if the queue is empty
     *          or the Head of this queue
     */
    @Override
    public E peek() {
        if(List.isEmpty()){
            return null;
        }else{
            return List.peek();
        }
    }

    /**methode poll
     * 
     * @return null if the queue is empty
     *          or the Head of this queue
     */
    @Override
    public E poll() {
        if(List.isEmpty()){
            throw new NoSuchElementException();
        }else{
            return List.poll();
        }
    }

    
    //function where is not needed
    
    @Override
    public boolean addAll(Collection<? extends E> arg0) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new java.lang.UnsupportedOperationException();
        
    }

    @Override
    public boolean contains(Object arg0) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> arg0) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object arg0) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> arg0) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> arg0) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public int size() {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] arg0) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public boolean add(E arg0) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public E element() {
        throw new java.lang.UnsupportedOperationException();
    }
    
    @Override
    public E remove() {
		throw new java.lang.UnsupportedOperationException();
	}

}
