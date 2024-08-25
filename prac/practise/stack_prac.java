// Stack
// Last in First Out

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

class MyStackArray {
    int MAX_SIZE = 10;
    int size = 0;
    int[] data;

    MyStackArray() {
        data = new int[MAX_SIZE];
    }

    public void push(int num) {
        data[size++] = num;
    }

    public int pop() {
        return data[--size];
    }

    public int top() {
        return data[size-1];
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

// in linkedlist top is head !
class MyStackLinkedList {
    class Node {
        int data;
        Node next;
        Node(int num) {
            data = num;
        }
    }
    Node top = null;

    public void push(int num) {
        Node newNode = new Node(num);
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        int topValue = top.data;
        top = top.next;
        return topValue;
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
        return sb.toString();
    }
}

public class stack_prac {
    public static void MyStackArrayTest() {
        MyStackArray stackArr = new MyStackArray();
        stackArr.push(1);
        stackArr.push(2);
        stackArr.push(3);
        System.out.println(stackArr);
        int val = stackArr.pop();
        System.out.println("After pop " + val + " is " + stackArr);
    }

    public static void MyStackLinkedListTest() {
        MyStackLinkedList stackLinkedList = new MyStackLinkedList();
        stackLinkedList.push(1);
        stackLinkedList.push(2);
        stackLinkedList.push(3);
        System.out.println(stackLinkedList);
        int val2 = stackLinkedList.pop();
        System.out.println("After pop " + val2 + " is " + stackLinkedList);
    }

    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    public static boolean isNumber(String strNum) {
        if (strNum == null) {
            return false;
        } 
        return pattern.matcher(strNum).matches();
    }

    public static int ComputeRPN(String rpn) {
        MyStackArray stack = new MyStackArray();
        StringTokenizer st = new StringTokenizer(rpn);
        while(st.hasMoreTokens()) {
            String t = st.nextToken();
            // System.out.println(t);
            // System.out.println(isNumber(t));
            if (isNumber(t)) {
                stack.push(Integer.parseInt(t));
            } else {
                int num1, num2;
                switch (t) {
                    case "-":
                        num2 = stack.pop();
                        num1 = stack.pop();
                        stack.push(num1 - num2);
                        break;
                    case "+":
                        num2 = stack.pop();
                        num1 = stack.pop();
                        stack.push(num1 + num2);
                        break;
                    case "*":
                        num2 = stack.pop();
                        num1 = stack.pop();
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        num2 = stack.pop();
                        num1 = stack.pop();
                        stack.push(num1 / num2);
                        break;
                    default:
                        continue;
                }
            }
        }
        return stack.pop();
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String rpn = in.nextLine();

        // String rpn = "5 8 3 1 + - *";

        System.out.println(ComputeRPN(rpn));
    }
}
