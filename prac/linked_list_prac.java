class Node {
        int data;
        Node next;
        Node prev;
    public Node(int d) {
        data = d;
    }
}

class Linked_list {
    Node head = null;

    public int size() {
        Node p = head;
        int size = 0;
        while(p != null) {
            p = p.next;
            size++;
        }
        return size;
    }

    public int getAt(int index) {
        if (index > this.size()) {
            System.out.println("Index out of bound!");
            return -1;
        }
        Node p = head;
        while(index > 0) {
            p = p.next;
            index--;
        }
        return p.data;
    }

    public void setAt(int num, int index) {
        if (index > this.size()) {
            System.out.println("Index out of bound!");
            return ;
        }
        // if (index >= this.size() || index < 0) {
        //     throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        // }
        Node p = head;
        while(index > 0) {
            p = p.next;
            index--;
        }
        p.data = num;
    }

    public boolean isEmpty() {
        return head==null;
    }

    public void add(int num) {
        Node p = new Node(num);
        p.next = head;
        head = p;
    }
    
    public void append(int num) {
        Node p = head;
        while(p.next != null) {
            p = p.next;
        }
        p.next = new Node(num);
        // p.next.next = null;
    }

    public void insert(int num, Node n) {
        Node temp = new Node(num);
        temp.next = n.next;
        n.next = temp;
    }

    public void insert(int num) {
        if(this.size() == 0) {
            this.add(num);
            return ;
        }
        Node p = head;
        while(p != null && p.data < num) {
            p = p.next;
        }
        if (p == null) this.append(num);
        else this.insert(num, p);
    }

    public void delete(int num) {
        Node p = head;
        while (p.next != null && p.next.data != num) {
            p = p.next;
        }
        if (p.next != null) p.next = p.next.next;
    }

    public void delete(Node t) {
        Node p = head;
        while (p.next != null && p.next.data != t.data) {
            p = p.next;
        }
        if (p.next != null) p.next = p.next.next;
    }

    public int find(int num) {
        Node p = head; int index = 0;
        while(p != null && p.data != num) {
            p = p.next;
            index++;
        }
        if(p != null) return index;
        else return -1;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("head");
        Node p = head;
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

class Doubly_linked_list {
    Node head = null; 
    Node tail = null;

    // insert at tail
    public void append(int num) {
        Node p = new Node(num);
        if(tail == null) {
            head = p;
            tail = p;
        } else {
            tail.next = p;
            p.prev = tail;
            tail = p;
        }
    }

    // insert at head
    public void add(int num) {
        Node newNode = new Node(num);
        if(head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public Node find(int num) {
        Node p = head;
        while(p != null && p.data != num) {
            p = p.next;   
        }
        if(p != null) return p;
        else return null;
    }

    public void delete(int num) {
        Node p = find(num);

        if (p == null) {
            return;
        }
    
        // If p is the head node
        if (p.prev == null) {
            head = p.next;
        } else {
            p.prev.next = p.next;
        }
    
        // If p is the tail node
        if (p.next == null) {
            tail = p.prev;
        } else {
            p.next.prev = p.prev;
        }
    
        p.next = null;
        p.prev = null;
    }    

    public String toString() {
        StringBuffer sb = new StringBuffer("head");
        Node p = head;
        while (p != null) {
            sb.append("<-->");
            sb.append("[");
            sb.append(p.data);
            sb.append("]");
            p = p.next;
        }
        sb.append("<-->null");
        return new String(sb);
    }

}

public class linked_list_prac {
    public static void main(String[] args) {
        Linked_list l1 = new Linked_list();
        System.out.println(l1);

        l1.add(1);
        l1.add(2);
        l1.add(3);
        l1.add(4);
        l1.add(5);
        l1.add(6);
        System.out.println(l1);

        System.out.println(l1.size());

        System.out.println(l1.getAt(10));
        System.out.println(l1.getAt(2));

        l1.append(10);
        System.out.println(l1);

        Linked_list l2 = new Linked_list();
        l2.insert(1);
        l2.insert(2);
        l2.insert(3);
        l2.insert(2);
        l2.insert(5);
        l2.insert(3);
        l2.insert(6);
        System.out.println(l2);

        l2.delete(5);
        System.out.println(l2);

        l2.delete(15);
        System.out.println(l2);

        l2.setAt(10, 2);
        System.out.println(l2);

        int index = l2.find(10);
        System.out.println(index);

        int index2 = l2.find(5);
        System.out.println(index2);

        int index3 = l2.find(6);
        System.out.println(index3);

        System.out.println(l2.isEmpty());
        Linked_list l3 = new Linked_list();
        System.out.println(l3.isEmpty());

        Doubly_linked_list dl1 = new Doubly_linked_list();
        dl1.add(1); dl1.add(2);
        dl1.append(5);

        System.out.println(dl1);

        dl1.delete(2);
        System.out.println(dl1);
    }
}

// head-->null
// head-->[6]-->[5]-->[4]-->[3]-->[2]-->[1]-->null
// 6
// Index out of bound!
// -1
// 4
// head-->[6]-->[5]-->[4]-->[3]-->[2]-->[1]-->[10]-->null
// head-->[1]-->[2]-->[2]-->[3]-->[3]-->[5]-->[6]-->null
// head-->[1]-->[2]-->[2]-->[3]-->[3]-->[6]-->null
// head-->[1]-->[2]-->[2]-->[3]-->[3]-->[6]-->null
// head-->[1]-->[2]-->[10]-->[3]-->[3]-->[6]-->null
// 2
// -1
// 5
// false
// true
// head<-->[2]<-->[1]<-->[5]<-->null
// head<-->[1]<-->[5]<-->null
