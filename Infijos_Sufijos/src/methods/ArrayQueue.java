package methods;

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
    private int first;
    private int last;

    /**
     * Constructor de la cola
     */
    public ArrayQueue() {
        arr = (Item[]) new Object[1];
        count = 0;
        first = 0;
        last = 0;
    }

    /**
     * Encolador, añade items a la cola
     *
     * @param item
     */
    public void enqueue(Item item) {
        if (count == arr.length) {
            resize(arr.length * 2);
        }
        arr[last] = item;
        last = (last + 1) % arr.length; // Actualizar last después de asignar el elemento al arreglo
        count++;
    }

    /**
     * Desencolador, retira items de la cola
     *
     * @return
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Dequeue: Queue está vacío");
        }
        Item temp = arr[first];
        count--;
        first = (first + 1) % arr.length; // Actualiza índice del frente
        if (count <= arr.length / 4 && count > 0) {
            resize(arr.length / 2);
        }
        return temp;
    }

    /**
     * Verifica si la cola tiene elementos
     *
     * @return
     */
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Retorna el tamaño de la cola
     *
     * @return
     */
    public int size() {
        return count;
    }

    /**
     * Redimensiona el tamaño de la cola
     *
     * @param maxCap
     */
    private void resize(int maxCap) {
        if (count == 0) {
            arr = (Item[]) new Object[maxCap];
            first = 0;
            last = 0;
            return;
        }
        Item[] temp = (Item[]) new Object[maxCap];
        int current = first;
        for (int i = 0; i < count; i++) {
            temp[i] = arr[current];
            current = (current + 1) % arr.length;
        }
        arr = temp;
        first = 0;
        last = count % arr.length;
    }

    /**
     * Obtiene el iterador de la cola
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

        private int i = count - 1; // Inicializa i apuntando al último elemento

        /**
         * Retorna si existen elmentos en la cola
         *
         * @return
         */
        @Override
        public boolean hasNext() {
            return i >= 0; // Devuelve true si hay más elementos por iterar
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
            return arr[i--]; // Devuelve el elemento actual y decrementa i
        }

    }

}
