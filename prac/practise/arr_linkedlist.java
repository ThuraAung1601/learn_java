// ----------- Array ------------------
class MyArray {
    int size = 0;
    int MAX_SIZE = 100;
    int[] data = new int[MAX_SIZE];

    MyArray() {}
    MyArray(int... a) {
        if(a.length > size) {
            expand(2);
        }
        for(int i: a) {
            add(i);
        }
    }
    public void add(int num) {
        data[size++] = num;
    }
    public int getAt(int index) {
        return data[index];
    }
    public void setAt(int index, int num) {
        data[index] = num;
    }
    public void expand(int k) {
        MAX_SIZE *= k;
        int[] temp = new int[MAX_SIZE];
        for(int i = 0; i < size; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }
    public boolean find(int num) {
        for(int a: data) {
            if(a == num) {
                return true;
            }
        }
        return false;
    }
    public int findIndex(int num) {
        for(int i = 0; i < size; i++) {
            if(data[i] == num) {
                return i;
            }
        }
        return -1;
    }
    public void deleteAt(int index) {
        data[index] = data[size-1];
        size--;
    }
    public void delete(int num) {
        int index = findIndex(num);
        deleteAt(index);
    }

    public void rotateLeft(int r) {
        if (r > size) {
            r = r % size;
        }
        
        reverseArray(0, size - 1);
        reverseArray(0, r - 1);
        reverseArray(r, size - 1);
    }

    public void rotateRight(int r) {
        if (r > size) {
            r = r % size;
        }

        reverseArray(0, size - 1);
        reverseArray(size - r, size - 1);
        reverseArray(0, size - r - 1);
    }

    public void reverseArray(int start, int end) {
        while (start < end) {
            int temp = data[start];
            data[start] = data[end];
            data[end] = temp;
            start++;
            end--;
        }
    }

    public MyArray clone() {
        MyArray clone = new MyArray();
        for(int i = 0; i < size; i++) {
            clone.add(data[i]);
            // System.out.println(temp[i]);
        }
        // System.out.println(clone.size());
        return clone;
    }

    public int size() {
        return size;
    }

    public boolean isFull() {
        return size == MAX_SIZE;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for(int i = 0; i < size-1; i++) {
            sb.append(data[i]);
            sb.append(", ");
        }
        sb.append(data[size-1]);
        sb.append("]");

        return sb.toString();
    }
}


// ----------- LinkedList ------------------

class MyLinkedList {
    class Node {
        int data;
        Node next;
        Node(int num) {
            data = num;
        }
    }

    Node head = null;
    
    public int getAt(int index) {
        int i = 0;
        Node p = head;
        while(p != null) {
            if(i == index) {
                return p.data;
            }
            p = p.next; i++;
        }
        return -1;
    }

    public void setAt(int index, int num) {
        if(index >= this.size()) {
            return;
        }
        int i = 0;
        Node p = head;
        while(p != null) {
            if(i == index) {
                p.data = num;
            }
            p = p.next; i++;
        }
        return ;
    }

    public int size() {
        int size = 0;
        Node p = head;
        while(p != null) {
            size++;
            p = p.next;
        }
        return size;
    }

    public void addAtFirst(int num) {
        Node newNode = new Node(num);
        newNode.next = head;
        head = newNode;
    }

    public void addAtLast(int num) {
        Node p = head;
        if(p != null) {
            while(p.next != null) {
                p = p.next;
            }
            p.next = new Node(num);
        } else {
            addAtFirst(num);
        }
    }

    public void deleteAt(int index) {
        if(index > this.size()) {
            return;
        }
        Node p = head;
        int i = 0;
        while(p != null && i < index-1) {
            p = p.next; i++;
        }

        if (p == null) {
            System.out.print("hello");
            return;
        }
        p.next = p.next.next;
    }

    public void reverse() {
        Node p = head;
        MyLinkedList temp = new MyLinkedList();
        while(p.next != null) {
            temp.addAtFirst(p.data);
            p = p.next;
        }
        temp.addAtFirst(p.data);
        head = temp.head;

        // Note: looping can be done using index
        // while (index < size()) {
        //      int value = this.getAt(index); index++; 
        // }
    }

    public void rotate(int r) {
        if(r > this.size()) {
            r = r % 3;
        }
        Node current = head;
        Node tail = head;
        int index = 0;
        while (index < r - 1) {
            // move the current node pointer til/to the move point
            current = current.next;
            index++;
        }
        tail = current;
        while(tail.next != null) {
            tail = tail.next;
        }
        tail.next = head;
        head = current.next;
        current.next = null;
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
        return sb.toString();
    }
}

public class arr_linkedlist {
    public static void MyArrayTest() {
        MyArray arr = new MyArray();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        System.out.println(arr);

        arr.getAt(2);
        System.out.println(arr);
        arr.setAt(2, 5);
        System.out.println(arr);

        MyArray arr2 = new MyArray(1, 2, 3, 100, 4, 5, 10, 11, -1);
        System.out.println("Array 2 is " + arr2);

        System.out.println("Is 3 in our arr2: " + arr2.find(3));
        System.out.println("Is 4 in our arr2: " + arr2.find(4));
        System.out.println("Is 10 in our arr2: " + arr2.find(10));
        System.out.println("Is 20 in our arr2: " + arr2.find(20));
        MyArray arr3 = arr2.clone();
        System.out.println("Array 2 Clone is " + arr3);
        // arr3.reverse();
        // System.out.println("Reversed is " + arr3);
        arr3.rotateRight(2);
        System.out.println("Rotate right by 2 is " + arr3);
        arr3.delete(1);
        System.out.println("Deleted index 1 is " + arr3);

        MyArray arr4 = arr1.clone();
        System.out.println("Clone of arr1 is " + arr4);
    }

    public static void MyLinkedListTest() {
        MyLinkedList l1 = new MyLinkedList();
        l1.addAtFirst(1); l1.addAtFirst(2); l1.addAtFirst(3);

        System.out.println("Linked List 1 is " + l1);

        MyLinkedList l2 = new MyLinkedList();
        l2.addAtLast(1); l2.addAtLast(2); l2.addAtLast(3);

        System.out.println("Linked List 2 is " + l2);

        System.out.println("Linked List 2 3rd index is " + l2.getAt(2));
        System.out.println("Linked List 2 1st index is " + l2.getAt(0));

        l2.setAt(0, 10);
        System.out.println("Linked List 2 set 1st index 10 " + l2);

        l2.setAt(2, 12);
        System.out.println("Linked List 2 set 3rd index 12 " + l2);

        l2.setAt(5, 12);
        System.out.println("Linked List 2 set 5th index 12 " + l2);

        System.out.println("Size of Linked List 2 is " + l2.size());

        l2.deleteAt(1);
        System.out.println("Linked List 2 delete 2nd index " + l2);

        System.out.println("Reverse of Linked List 1 BEFORE is " + l1);
        l1.reverse();
        System.out.println("Reverse of Linked List 1 AFTER is " + l1);

        System.out.println("Reverse of Linked List 1 BEFORE is " + l2);
        l2.reverse();
        System.out.println("Reverse of Linked List 1 AFTER is " + l2);

        l2.addAtFirst(1); l2.addAtFirst(2); l2.addAtFirst(3);
        l2.addAtFirst(4); l2.addAtFirst(5); l2.addAtFirst(6);
        System.out.println("L2: " + l2);
        l2.rotate(3);
        System.out.println("Rotate 3 rount!: " + l2);
    }
    public static void main(String[] args) {
        System.out.println("<--------------- Array ------------------>");
        MyArrayTest();
        System.out.println("<------------- Linked List ----------------->");
        MyLinkedListTest();
    }
}
