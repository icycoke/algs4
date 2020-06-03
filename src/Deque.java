import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private final Node head;
    private final Node tail;
    private int size;

    private class Node {
        private Item item;
        private Node prev;
        private Node next;

        public Node() {
            item = null;
        }

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    // construct an empty deque
    public Deque() {
        size = 0;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
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
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node curNode = new Node(item, head, head.next);
        curNode.prev.next = curNode;
        curNode.next.prev = curNode;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node curNode = new Node(item, tail.prev, tail);
        curNode.prev.next = curNode;
        curNode.next.prev = curNode;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node curNode = head.next;
        curNode.prev.next = curNode.next;
        curNode.next.prev = curNode.prev;
        curNode.prev = null;
        curNode.next = null;
        size--;
        return curNode.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node curNode = tail.prev;
        curNode.prev.next = curNode.next;
        curNode.next.prev = curNode.prev;
        curNode.prev = null;
        curNode.next = null;
        size--;
        return curNode.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node curNode = head;

            @Override
            public boolean hasNext() {
                return curNode.next != tail;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                curNode = curNode.next;
                return curNode.item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        System.out.println(deque.isEmpty());
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(3);
        for (int num : deque) {
            System.out.print(num);
        }
        System.out.println();
        deque.removeFirst();
        for (int num : deque) {
            System.out.print(num);
        }
        System.out.println(deque.size());
        System.out.println();
        deque.removeLast();
        for (int num : deque) {
            System.out.print(num);
        }
    }

}