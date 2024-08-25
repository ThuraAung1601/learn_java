// ----------- Array ------------------
class MyArray {
    private int MAX_SIZE = 1;
    private int size = 0;
    private int[] data;

    MyArray() {
        data = new int[MAX_SIZE];
    }

    MyArray(int... arr) {
        if(arr.length > MAX_SIZE) {
            expand(2);
        }
        for(int i: arr) {
            this.add(i);
        }
    }
    
    public void add(int num) {
        if(size >= MAX_SIZE) {
            expand(2);
        }
        // data[size] = num;
        // size++;
        data[size++] = num;
    }

    public int getAt(int index) {
        if(index >= size) {
            return -1;
        }
        return data[index];
    }

    public void setAt(int index, int num) {
        if(index >= size) {
            return;
        }
        data[index] = num;
    }

    public boolean isFull() {
        return size == MAX_SIZE;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void expand(int k) {
        MAX_SIZE = MAX_SIZE * k;
        int[] temp = new int[MAX_SIZE];
        for(int i = 0; i < size; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    public int[] cloner() {
        int[] clone = new int[size];
        for (int i = 0; i < size; i++) {
            clone[i] = data[i];
        }
        return clone;
    }

    public MyArray clone() {
        MyArray clone = new MyArray();
        for (int i = 0; i < size; i++) {
            clone.add(data[i]);
        }
        return clone;
    }

    // brute-force search O(n)
    public boolean find(int num) {
        for(int i: data) {
            if(i == num) {
                return true;
            }
        }
        return false;
    }

    public void reverse() {
        int start = 0; int end = size - 1;
        while(start < end) {
            int temp = data[start];
            data[start] = data[end];
            data[end] = temp;
            start++; end--;
        }
    }

    public void reverse(int start, int end) {
        while(start < end) {
            int temp = data[start];
            data[start] = data[end];
            data[end] = temp;
            start++; end--;
        }
    }

    public void rotate(int r) {
        if (r < 0 || r >= size) {
            // Invalid rotation, do nothing or throw an error
            return;
        }
    
        r = r % size; // In case r is greater than size
        reverse(0, size - 1);
        reverse(0, r - 1);
        reverse(r, size - 1);
    }

    public void delete(int index) {
        // data[index] = data[size-1];
        // size--;
        for(int i=index; i < size-1; i++) {
            data[i] = data[i+1];
        }
        size--;
    } 

    // // binary search O(logn)
    // public boolean find(int num) {
    //     int[] clone = cloner();
    //     Arrays.sort(clone);
    //     // System.out.println("Sorted array: " + Arrays.toString(clone));
    //     int left = 0; int right = size - 1;
    //     while (left <= right) {
    //         int mid = (right+left)/2;
    //         if (num == clone[mid]) {
    //             return true;
    //         } else if (num > clone[mid]) {
    //             left = mid + 1;
    //         } else {
    //             right = mid - 1;
    //         }
    //     }
    //     return false;
    // }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for(int i=0; i < size-1; i++) {
            sb.append(data[i]);
            sb.append(", ");
        }
        if (size > 0) {
            sb.append(data[size-1]);
        }
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
        arr3.rotate(2);
        System.out.println("Rotate by 2 is " + arr3);
        arr3.delete(1);
        System.out.println("Deleted index 1 is " + arr3);
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
