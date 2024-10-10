package solutions.pack9_Heap;

public class MyPriorityQueue_661606 implements MyQueueInterface{
    MyMinHeap_661606 heap = new MyMinHeap_661606();

    @Override
    public void enqueue(int d) {
        if (!heap.isFull())
            heap.insert(d);
    }

    @Override
    public int dequeue() {
        if (!heap.isEmpty())
            return heap.remove();
        return -1;
    }

    @Override
    public int front() {
        if (!heap.isEmpty())
            return heap.peek();
        return -1;
    }

    @Override
    public boolean isFull() {
        return heap.isFull();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}