class StackA {
    int MAX_SIZE = 100;
    int[] stack = new int[MAX_SIZE];
    int top = 0; // top is like size

    StackA() {}

    // push is like add in array at the back
    // pop is like get last item in array

    public void push(int num) {
        if(isFull()) {
            expand(2);
        }
        stack[top] = num;
        top++;
    }

    public int pop() {
        int p = stack[top-1];
        top--;
        return p;
        // return stack[--top];
    }

    public int top() {
        return stack[top-1];
    }

    public boolean isFull() {
        return top == MAX_SIZE;
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public void expand(int k) {
        MAX_SIZE *= k;
        int[] temp = new int[MAX_SIZE];
        for(int i = 0; i < top; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Stack: [");
        for(int i = 0; i < top-1; i++) {
            sb.append(stack[i]);
            sb.append(", ");
        }
        sb.append(stack[top-1]);
        sb.append("]");
        return sb.toString();
    }

}

class StackL {
    public class Node {
        int data;
        Node next;
        public Node(int num) {
            data = num;
        }
    }

    StackL() {}

    Node top = null;
    public void push(int num) {
        Node p = new Node(num);
        p.next = top;
        top = p;
    }
    public int pop() {
        if(isEmpty()) {
            System.out.println("Underflow"); 
            return -1;
        }
        int d = top.data;
        top = top.next;
        return d;
    }
    public int top() {
        return top.data;
    }

    public boolean isFull() {
        return false;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("top");
        Node p = top;
        while(p != null) {
            sb.append("-->[");
            sb.append(p.data);
            sb.append("]");
            p = p.next;
        }
        sb.append("-->null");
        return new String(sb);
    }
}

public class stack_prac {
    public static void main(String[] args) {
        StackA sA1 = new StackA();
        sA1.push(1);
        sA1.push(2);
        sA1.push(3);
        sA1.push(4);
        System.out.println(sA1);
        System.out.println(sA1.pop());
        System.out.println(sA1);
        System.out.println(sA1.top());

        StackL sL1 = new StackL();
        sL1.push(1);
        sL1.push(2);
        sL1.push(3);
        sL1.push(4);
        System.out.println(sL1);
    }
}
