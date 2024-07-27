package solutions.pack3_ArrAndList;

import java.util.ArrayList;
import java.util.HashSet;

public class MyLinkedListTricky_661606 {
    public class Node {
        int data;
        Node next;
        Node(int d) {
            data = d;
        }
    }

    Node head = null;
    public void add(int num) {
        Node p = new Node(num);
        p.next = head;
        head = p;
    }

    public void add(int[] arr) {
        for(int i = arr.length-1; i >= 0; i--) {
            this.add(arr[i]);
        }
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


    public void q1_rotate_clockwise(int k) {
        if (!(k < 1 || k > size())) {
            Node p = head;
            while (p.next != null) {
                p = p.next;
            }

            for(int i = 0; i < k; i++) {
                p.next = head;
                head = head.next;
                p = p.next;
                p.next = null;
            }
        }
    }

    public void q2_reverse() {
        Node p = null;
        while (head != null) {
            Node q = new Node(head.data);
            q.next = p;
            p = q;
            head = head.next;
        }
        head = p;
    }

    public void q3_remove_dup() {
        if (head == null || head.next == null)
            return;
        HashSet<Integer> myset = new HashSet<Integer>();
        Node p = new Node(0);
        p.next = head;
        while (p.next != null) {
            if (!(myset.contains(p.next.data))) {
                myset.add(p.next.data);
                p = p.next;
                continue;
            }
            p.next = p.next.next;
        }
    }

    public void q4_add_one() {
        q2_reverse();
        Node current = head;
        int carry = 1;
        while (current != null) {
            if (carry == 1) {
                current.data += 1;
                carry = 0;
            }
            if (current.data >= 10){
                carry = 1;
                current.data = 0;
            }
            current = current.next;
        }
        q2_reverse();

        if (carry == 1) {
            Node q = new Node(1);
            q.next = head;
            head = q;
        }
    }

    public boolean q5_isPalindrome() {
        ArrayList<Integer> mid = new ArrayList<Integer>();
        Node p = head;
        int len = size() / 2;
        
        for (int i = 0; i < len; i++) {
            mid.add(p.data);
            p = p.next;
        }
        if (size() % 2 != 0) {
            p = p.next;
        }
        for (int i = len - 1; i >= 0; i--) {
            if (p.data != mid.get(i)) {
                return false;
            }
            p = p.next;
        }
        return true;
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
