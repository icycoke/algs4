import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 2;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (isFull()) {
            resize(2 * capacity);
        }
        items[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size * 4 == capacity) {
            resize(capacity / 2);
        }
        int randomIndex = StdRandom.uniform(size);
        Item res = items[randomIndex];
        items[randomIndex] = items[size - 1];
        items[size - 1] = null;
        size--;
        return res;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return items[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private boolean isFull() {
        return size == capacity;
    }

    private void resize(int resizedCapacity) {
        Item[] resizedItems = (Item[]) new Object[resizedCapacity];
        for (int i = 0; i < size; i++) {
            resizedItems[i] = items[i];
        }
        capacity = resizedCapacity;
        items = resizedItems;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private final int[] randomIndexes;
        private int curIndex;

        public RandomizedQueueIterator() {
            randomIndexes = new int[size];
            for (int i = 0; i < size; i++) {
                randomIndexes[i] = i;
            }
            StdRandom.shuffle(randomIndexes);
        }

        @Override
        public boolean hasNext() {
            return curIndex < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return items[randomIndexes[curIndex++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        System.out.println(randomizedQueue.isEmpty());
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        randomizedQueue.enqueue(4);
        System.out.println(randomizedQueue.size);
        for (int num : randomizedQueue) {
            System.out.print(num);
        }
        System.out.println();
        System.out.println(randomizedQueue.sample());
        randomizedQueue.dequeue();
        for (int num : randomizedQueue) {
            System.out.print(num);
        }
    }
}