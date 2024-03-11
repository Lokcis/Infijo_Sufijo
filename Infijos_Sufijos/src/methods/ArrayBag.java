package methods;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author lokci
 * @param <Item>
 */
public class ArrayBag<Item> implements Iterable<Item> {

    private Item[] arr;
    private int count;

    public ArrayBag() {
        arr = (Item[]) new Object[1];
        count = 0;
    }

    public void add(Item item) {
        if (count == arr.length) {
            resize(arr.length * 2);
        }
        arr[count++] = item;
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
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {

        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < count;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No hay más elementos para iterar");
            }
            return arr[i++];
        }
    }

}
