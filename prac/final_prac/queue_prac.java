class QueueArr {
    int MAX_SIZE = 100;
    int head = 0; int tail = 0;
    int[] data = new int[MAX_SIZE];
    QueueArr() {}
    public void enqueue(int num) {
        data[tail] = num;
        tail = (tail+1)%MAX_SIZE;
    }
    public int dequeue() {
        int front = data[head];
        head = (head + 1) % MAX_SIZE;
        return front;
    }
    public int front() {
        return data[head];
    }
    public boolean isFull() {
        int temp = (tail+1) % MAX_SIZE;
        return temp == head;
    }
    public boolean isEmpty() {
        return head == tail;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("head->");
        int i = head;
        while(i != tail) {
            sb.append(data[i]);
            sb.append("->");
            i = (i+1)%MAX_SIZE;
        }
        sb.append("tail");
        return sb.toString();
    }
}

class Node {
    int data; 
    Node next;
    public Node(int num) {
        data = num;
    }
}

class QueueL {
    Node head = null;
    Node tail;

    public void enqueue(int data) {
        Node p = new Node(data);
        if(isEmpty()) {
            head = tail = p;
        } else {
            tail.next = p;
            tail = tail.next;
        }
    }
    public int dequeue() {
        int data = head.data;
        head = head.next;
        return data;
    }
    public int front() {
        return head.data;
    }
    public boolean isFull() {
        return false;
    }
    public boolean isEmpty() {
        return head == null;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("head->");
        Node p = head;
        while(p != null) {
            sb.append(p.data);
            sb.append("->");
            p = p.next;
        }
        sb.append("tail");
        return sb.toString();
    }
}  

public class queue_prac {
    public static void main(String[] args) {
        QueueArr q1 = new QueueArr();
        q1.enqueue(1); q1.enqueue(2);  q1.enqueue(3);  q1.enqueue(4); 
        System.out.println(q1);
        q1.dequeue();
        System.out.println(q1);
        int temp = q1.front();
        System.out.println(temp);

        QueueL q2 = new QueueL();
        q2.enqueue(1); q2.enqueue(2);  q2.enqueue(3);  q2.enqueue(4); 
        System.out.println(q2);
        q2.dequeue();
        System.out.println(q2);
        int temp2 = q2.front();
        System.out.println(temp2);
    }
    
}
