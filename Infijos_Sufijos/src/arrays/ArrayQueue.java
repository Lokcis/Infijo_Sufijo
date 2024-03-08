package arrays;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author lokci
 * @param <Item>
 */
public class ArrayQueue<Item> implements Iterable<Item> {

    private Item[] arr;
    private int count;
    private int first = 0;
    private int last = 0;

    public ArrayQueue() {
        arr = (Item[]) new Object[1];
        count = 0;
    }

    public void enqueue(Item item) {
        if (count == arr.length) {
            resize(arr.length * 2);
        }
        last = (last + 1) % arr.length;
        arr[last] = item;
        count++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Dequeue: Queue está vacío");
        } else {
            Item temp = arr[first];
            count--;
            for (int i = 1; i < count; i++) {
                arr[i-1] = arr[i];
            }
            if (count <= arr.length / 4 && count > 0) {
                resize(arr.length / 2);
            }
            return temp;
        }
    }

    public boolean isEmpty() {
        return (count == 0);
    }

    public int size() {
        return count;
    }

    private void resize(int maxCap) {
        Item[] temp = (Item[]) new Object[maxCap];
        int current = first;
        for (int i = 0; i < count; i++) {
            temp[i] = arr[current];
            current++;
        }
        arr = temp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {

        private int i = count;

        @Override
        public boolean hasNext() {            
            return count > 0;
        }

        @Override
        public Item next() {
            return arr[--count];
        }

    }

}
