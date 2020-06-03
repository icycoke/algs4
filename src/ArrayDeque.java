import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<Item> implements Iterable<Item> {

    private int capacity;
    private int size;
    private Item[] items;

    // construct an empty deque
    public ArrayDeque() {
        capacity = 10;
        size = 0;
        items = (Item[]) new Object[10];
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (isFull()) {
            grow();
        }
        for (int i = size - 1; i >= 0; i--) {
            items[i + 1] = items[i];
        }
        items[0] = item;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (isFull()) {
            grow();
        }
        items[size] = item;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = items[0];
        for (int i = 0; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        size--;
        return items[size];
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                index++;
                return items[index - 1];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private boolean isFull() {
        return capacity == size;
    }

    private void grow() {
        Object[] grownItems = new Object[capacity * 2];
        for (int i = 0; i < size; i++) {
            grownItems[i] = (Item) items[i];
        }
        items = (Item[]) grownItems;
    }

    // unit testing (required)
    public static void main(String[] args) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int num : arrayDeque) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println(arrayDeque.isEmpty());
        System.out.println(arrayDeque.size());
        arrayDeque.addFirst(1);
        for (int num : arrayDeque) {
            System.out.print(num + " ");
        }
        System.out.println();
        arrayDeque.addFirst(2);
        for (int num : arrayDeque) {
            System.out.print(num + " ");
        }
        System.out.println();
        arrayDeque.addLast(3);
        for (int num : arrayDeque) {
            System.out.print(num + " ");
        }
        System.out.println();
        arrayDeque.removeFirst();
        for (int num : arrayDeque) {
            System.out.print(num + " ");
        }
        System.out.println();
        arrayDeque.removeLast();
        for (int num : arrayDeque) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}