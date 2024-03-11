//OK -> No es genérica, solo permite almacenar cadenas de caracteres
//OK -> Es de tamaño fijo
//No es iterable
//Desperdicia espacio (Loitering)
package methods;

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
    private Item top;

    public ArrayStack() {
        arr = (Item[]) new Object[1];
        count = 0;
        top = null;
    }

    public void push(Item item) {
        if (count == arr.length) {
            resize(arr.length * 2);
        }
        arr[count++] = item;
        top = item;
    }

    /**
     *
     * @return
     */
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("La cola está vacía");
        } else {
            Item temp = arr[--count];
            arr[count] = null;
            if (count > 0 && count <= arr.length / 4) {
                resize(arr.length / 2);
            }

            if (isEmpty()) {
                top = null; // Si la pila está vacía, actualizar la referencia a null
            } else {
                top = arr[count - 1]; // Actualizar la referencia al nuevo elemento en la cima
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
        for (int i = 0; i < count; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("La pila está vacía");
        }
        return top;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {

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
