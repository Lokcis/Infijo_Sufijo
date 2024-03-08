//OK -> No es genérica, solo permite almacenar cadenas de caracteres
//OK -> Es de tamaño fijo
//No es iterable
//Desperdicia espacio (Loitering)
package arrays;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Juan Carlos Navarro
 * @param <Item>
 */
public class ArrayStack<Item> implements Iterable<Item> {

    private Item[] arr;
    private int count;

    public ArrayStack() {
        arr = (Item[]) new Object[1];
        count = 0;
    }

    public void push(Item item) {
        if (count == arr.length) {
            resize(arr.length * 2);
        }
        arr[count++] = item;
    }

    /**
     *
     * @return
     */
    public Item pop() {
        Item temp = arr[--count];
        arr[count] = null;
        if (count <= arr.length / 4 && count > 0) {
            resize(arr.length / 2);
        }
        return temp;
    }

    public boolean isEmpty() {
        return (count == 0);
    }

    public int size() {
        return count;
    }

    private void resize(int maxCap) {
        Item[] temp = (Item[]) new Object[maxCap];
        for (int i = 0; i < count; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {

        private int i = count - 1;

        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No hay más elementos para iterar");
            }
            return arr[i--];
        }

    }

}
