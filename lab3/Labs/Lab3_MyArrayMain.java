package Labs;
import solutions.pack3_ArrandList.*;

public class Lab3_MyArrayMain {
    public static void main(String[] args) {
        System.out.println("-demo1--------");
        arrayBasic_demo1();
        System.out.println("-demo2--------");
        arrayBasic_demo2();
        System.out.println("-demo3--------");
        arrayBasic_demo3();
        System.out.println("-demo4--------");
        myArray_demo4();
        System.out.println("-1st run task2--------");
        task2();
        System.out.println("-2nd run task2--------");
        task2();
        System.out.println("-3rd run task2--------");
        task2();

    }
    static private void arrayBasic_demo1() {
        MyArrayBasic demo = new MyArrayBasic(7,6,8,1,2,3);
        System.out.println(demo);
    }
    static private void arrayBasic_demo2() {
        MyArrayBasic demo = new MyArrayBasic();
        demo.insert(9, 0);
        demo.insert(7,0);
        demo.insert(5,0);
        System.out.println(demo);
        System.out.println("5 is at " + demo.find(5));
        System.out.println("5 is at " + demo.binarySearch(5));
        demo.delete(1);
        System.out.println(demo);
    }
    static private void arrayBasic_demo3() {
        MyArrayBasic demo = new MyArrayBasic(null);
        try {
            demo.add(3);
            demo.add(7);
            demo.add(5);
            demo.add(4);
            demo.add(6);
            demo.add(6);
            //index out of bound due to overflow
            demo.add(1); 
        } catch(Exception e) {
            System.out.println("Index out of bound due to overflow");
        }
    }
    static private void myArray_demo4() {
        MyArray_661606 demo = new MyArray_661606(5);
        demo.delete(0); // not crashed
        demo.add(3);
        demo.add(7);
        demo.add(5);
        demo.add(4);
        demo.add(6);
        demo.add(1);        // not crashed
        System.out.println(demo);
    }
    static private void task2() {
        int initial = 1_000_000;
        int step = initial;
        for (int N = initial; N <= 10 * initial; N += step) {
            long start = System.currentTimeMillis();
            MyArray_661606 mArray = new MyArray_661606(N);
            for (int n = 1; n <N; n++)
                mArray.add((int)(Math.random()*1000));
            long time = (System.currentTimeMillis() - start);
            System.out.println(N + "\t\t" + time);
        }
        System.out.println("with expansion");
        for (int N = initial; N <= 10 * initial; N += step) {
            long start = System.currentTimeMillis();
            MyArray_661606 mArray = new MyArray_661606();
            for (int n = 1; n <N; n++)
                mArray.add((int)(Math.random()*1000));
            long time = (System.currentTimeMillis() - start);
            System.out.println(N + "\t\t" + time);
        }
    }
}