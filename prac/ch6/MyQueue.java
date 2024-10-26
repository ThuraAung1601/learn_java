class QueueArr {
    // First in First out
    int MAX_SIZE = 100;
    int[] data = new int[MAX_SIZE];
    int head = 0; int tail = 0;

    public void enqueue(int num) {
        if(isFull()) {
            data = expand(2);
        }
        data[tail] = num;
        tail = (tail+1)%MAX_SIZE;
    }

    public int dequeue() {
        int front = data[head];
        head = (head+1)%MAX_SIZE;
        return front;
    }

    public int front() {
        return data[head];
    }

    public boolean isFull() {
        int temp = (tail+1)%MAX_SIZE;
        return temp == head;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public int size() {
        if (tail >= head) {
            return tail - head;
        }
        return MAX_SIZE - head + tail;
    }

    public void clear() {
        // pointers reset
        head = 0; tail = 0;
    }

    // last element - tail
    public int rear() {
        if(isEmpty()) {
            return -1;
        } else {
            int i = (tail - 1 + MAX_SIZE) % MAX_SIZE; 
            return data[i];
        }
    }

    public boolean isIn(int num) {
        int i = head;
        while(i != tail) {
            if(data[i] == num) {
                return true;
            }
            i = (i+1)%MAX_SIZE;
        }
        return false;
    }

    public int[] expand(int k) {
        int newSize = MAX_SIZE * k;
        int[] newData = new int[newSize];
    
        // Copy elements from the current circular queue
        if (tail >= head) {
            for (int i = head; i < tail; i++) {
                newData[i - head] = data[i];
            }
            tail = tail - head;
        } else {
            int i = 0;
            for (int j = head; j < MAX_SIZE; j++, i++) {
                newData[i] = data[j];
            }
            for (int j = 0; j < tail; j++, i++) {
                newData[i] = data[j];
            }
            tail = i;
        }
        
        head = 0; // Reset head to 0 after copying
        MAX_SIZE = newSize; // Update the size of the queue
        return newData;
    }    

    @Override
    public String toString() {
        if(isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("head->");
        int i = head;
        while(i != tail) {
            sb.append(data[i] + "->");
            i = (i+1)%MAX_SIZE;
        }
        sb.append("tail");
        return sb.toString();
    }
}

class Node {
    int value;
    Node next;

    public Node(int v) {
        value = v;
    }
}

class QueueL {
    private Node head, tail;
    
    public QueueL() {
        head = tail = null;
    }

    public void enqueue(int num) {
        Node n = new Node(num);
        if(isEmpty()) {
            head = tail = n;
        } else {
            tail.next = n;
            tail = tail.next;
        }
    }

    public int dequeue() {
        if(isEmpty()) {
            return -1;
        }
        int front = head.value;
        head = head.next;
        if(isEmpty()) {
            tail = null;
        }
        return front;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean isFull() {
        return false;
    }

    public int top() {
        return head.value;
    }

    public int rear() {
        return tail.value;
    }

    public int size() {
        Node p = head;
        int count = 0;
        while(p != null) {
            p = p.next;
            count++;
        }
        return count;
    }

    public boolean isIn(int num) {
        Node p = head;
        while(p != null) {
            if (p.value == num) {
                return true;
            }
            p = p.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("head->");
        Node p = head;
        while(p != null) {
            sb.append(p.value + "->");
            p = p.next;
        }
        sb.append("tail");
        return sb.toString();
    }
}

public class MyQueue {
    public static void main(String args[]) {
        // QueueArr q1 = new QueueArr();
        QueueL q1 = new QueueL();
        q1.enqueue(1);
        q1.enqueue(2); 
        q1.enqueue(3);
        q1.enqueue(4);
        q1.enqueue(5);
        System.out.println(q1);

        int front = q1.dequeue();
        System.out.println("Front: " + front);
        System.out.println(q1);

        System.out.println(q1.size());
        System.out.println(q1.rear());
        System.out.println(q1.isIn(5));
    }
}