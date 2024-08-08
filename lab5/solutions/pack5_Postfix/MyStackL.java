package solutions.pack5_Postfix;

public class MyStackL {

    Node top = null;

    public void push(String d) {
        Node p = new Node(d);
        p.next = top;
        top = p;
    }

    public String pop() {
        String d = top.value;
        top = top.next;
        return d;
    }

    public String peek() {
        return top.value;
    }

    public boolean isFull() {
        return false;
    }

    public boolean isEmpty() {
        return top == null;
    }
}