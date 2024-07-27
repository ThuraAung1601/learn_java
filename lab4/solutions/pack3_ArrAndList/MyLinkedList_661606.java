package solutions.pack3_ArrAndList;

public class MyLinkedList_661606 {
    public class Node {
        int data;
        Node next;

        public Node(int d) {
            data = d;
        }
    }

    Node head = null;

    // add at the top
    public void add(int d) {
        // pointer
        Node p = new Node(d);
        p.next = head;
        head = p;
    }

    public void insert(int d) {
        Node p = new Node(d);
        if(head == null || head.data >= d) {
            p.next = head;
            head = p;
        } else {
            Node current = head;
            while (current.next != null && current.next.data <= d) {
                current = current.next;
            }
            p.next = current.next;
            current.next = p;
        }
    }

    public Node _find(int d) {
        Node p = head;
        while (p!=null) {
            if(p.data == d) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    public int find(int d) {
        int i = 0;
        Node p = head;
        while (p!=null) {
            if(p.data == d) {
                return i;
            }
            p = p.next; i++;
        }
        return -1;
    }

    public void delete(int d) {
        Node n = new Node(d);
        n.next = head;
        Node p = n;
        while (p.next != null && p.next.data != d) {
            // p.next point to each node
            p = p.next;
            // loop will stop when p.next is null
        }
        // if not found p.next will be null
        if (p.next != null) {
            p.next = p.next.next;
        }
        // point to original head
        head = n.next;
    }

    public int size() {
        int i = 0;
        Node p = new Node(0);
        p.next = head;
        while(p.next != null) {
            p = p.next;
            i++;
        }
        return i;
    }

    public void add(int[] arr) {
        for(int i = arr.length-1; i >= 0; i--) {
            this.add(arr[i]);
        }
    }

    public void insert(int[] arr) {
        for(int i: arr) {
            this.insert(i);
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Node p = head;
        sb.append("head");
        while(p!=null) {
            sb.append("->(");
            sb.append(p.data);
            sb.append(")");
            p = p.next;
        }
        sb.append("->null");
        return new String(sb);
    }
}
