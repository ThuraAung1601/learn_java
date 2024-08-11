// circular array
class QueueA {
    int MAX_SIZE = 100;
    int[] data = new int[MAX_SIZE];
    int head = 0; int tail = 0;

    public void enqueue(int num) {
        data[tail] = num;
        tail = (tail+1)%MAX_SIZE;
    }

    public int dequeue() {
        int deq = data[head];
        head = (head+1)%MAX_SIZE;
        return deq;
    }

    public int front() {
        return data[head];
    }

    public boolean isFull() {
        return ((tail+1)%MAX_SIZE) == head;
    }
    
    public boolean isEmpty() {
        return head == tail;
    }

}

class Node {
    int data;
    Node next;
    Node(int num) {
        data = num;
    }
}

class QueueL {
    Node head = null;
    Node tail;

    public void enqueue(int num) {
        Node p = new Node(num);
        if(head == null) {
            head = p;
            tail = p;
        } else {
            tail.next = p;
            tail = p;
        }
    }

    public int dequeue() {
        int front = head.data;
        head = head.next;
        return front;
    }

    public int front() {
        return head.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean isFull() {
        return false;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("Queue: head");
        Node p = head;
        while(p != null) {
            sb.append("-->[");
            sb.append(p.data);
            sb.append("]");
            p = p.next;
        }
        sb.append("<--tail");
        return new String(sb);
    }
}

public class queue_prac {
    public static void main(String[] args) {
        QueueL q1 = new QueueL();
        q1.enqueue(1);
        q1.enqueue(2);
        q1.enqueue(3);
        q1.enqueue(4);
        q1.enqueue(5);
        q1.enqueue(6);
        q1.enqueue(7);
        q1.enqueue(8);
        System.out.println(q1);
        System.out.println(q1.front());
        q1.dequeue();
        System.out.println(q1);
        System.out.println(q1.front());
    }
}
