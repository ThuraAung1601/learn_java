// Disclaimer: I used ChatGPT to solve this specific task!!!

package solutions.pack5_Postfix;

import java.util.Iterator;
import java.util.LinkedList;

public class MyQueueListWrap_661606<T> implements Iterable<T> {
    private LinkedList<T> items = new LinkedList<>(); // Internal LinkedList to store queue elements

    // Enqueue an element to the rear of the queue
    public void enqueue(T d) {
        items.add(d);
    }

    // Dequeue an element from the front of the queue
    public T dequeue() {
        return items.poll();
    }

    // Return the front element without removing it
    public T top() {
        return items.peek();
    }

    // Return the last element in the queue without removing it
    public T getLast() {
        return items.peekLast();
    }

    // Provide a string representation of the queue's elements
    public String dumpToString() {
        StringBuilder sb = new StringBuilder();
        for (T item : items) {
            sb.append(item).append(" ");
        }
        return sb.toString().trim();
    }

    // Implementing the Iterable interface
    @Override
    public Iterator<T> iterator() {
        return new MyQueueIterator();
    }

    // Override toString to provide a string representation of the queue
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<T> iter = items.iterator();
        while (iter.hasNext()) {
            sb.append(iter.next());
            if (iter.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Inner class to implement the Iterator interface
    private class MyQueueIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < items.size();
        }

        @Override
        public T next() {
            return items.get(currentIndex++);
        }
    }
}
