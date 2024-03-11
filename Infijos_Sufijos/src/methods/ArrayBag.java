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

    /**
     * Constructor de la maleta
     */
    public ArrayBag() {
        arr = (Item[]) new Object[1];
        count = 0;
    }

    /**
     * Añade items a la maleta
     *
     * @param item
     */
    public void add(Item item) {
        if (count == arr.length) {
            resize(arr.length * 2);
        }
        arr[count++] = item;
    }

    /**
     * Verifica si hay elementos en la maleta
     *
     * @return
     */
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Retorna el tamaño de la maleta
     *
     * @return
     */
    public int size() {
        return count;
    }

    /**
     * Redimensiona el tamaño de la maleta
     *
     * @param maxCap
     */
    private void resize(int maxCap) {
        Item[] temp = (Item[]) new Object[maxCap];
        for (int i = 0; i < count; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

    /**
     * Obtiene el iterador de la maleta
     *
     * @return
     */
    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    /**
     * Implementa el iterador
     */
    private class ArrayIterator implements Iterator<Item> {

        private int i = 0;

        /**
         * Retorna si existen elmentos en la cola
         * 
         * @return 
         */
        @Override
        public boolean hasNext() {
            return i < count;
        }

        /**
         * Retorna el item actual y incrementa el iterador
         * 
         * @return 
         */
        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No hay más elementos para iterar");
            }
            return arr[i++];
        }
    }

}
