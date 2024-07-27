package Labs;

import solutions.pack3_ArrAndList.MyLinkedList_661606;
// import solutions.pack3_ArrAndList.MyLinkedListTricky;

public class Lab4a_LinkedList {
    public static void main(String[] args) {
        System.out.println("\n-demo1--------");
        demo1();
        System.out.println("-demo2--------");
        demo2();
    }
    static void demo1() {
        MyLinkedList_661606 list = new MyLinkedList_661606();
        list.add(5);
        System.out.println(list);
        list.add(1);
        System.out.println(list);
        list.insert(4);
        System.out.println(list);
        list.insert(3);
        System.out.println(list);
        list.delete(2);
        System.out.println("5 is at " + list.find(5));
        System.out.println(list);
        System.out.println("Size of list is " + list.size());
    }
    static void demo2() {
        int [] arr = {50,40,30,20,10};
        MyLinkedList_661606 mList = new MyLinkedList_661606();
        mList.insert(arr);
        System.out.println(mList);
        MyLinkedList_661606 mList2 = new MyLinkedList_661606();
        mList2.add(arr);
        System.out.println(mList2);
    }
}