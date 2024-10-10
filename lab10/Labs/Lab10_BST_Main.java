package Labs;
import solutions.pack10_bst.*;;

public class Lab10_BST_Main {
	public static void main(String[] args) {
		System.out.println("Thura Aung 66011606");
		System.out.println("Example output:");
		task1();
		task2();
		task3();
	}
	public static void task1() {
		System.out.println("--- task 1 ---");
		int data[] = { 15, 20, 10, 18, 16, 12, 8, 25, 19, 30 };
		
		BST_6601606 BST_6601606 = new BST_6601606();
		for (int i=0; i<data.length; i++)
			BST_6601606.insert(data[i]);

		BST_6601606.printInOrder();
		System.out.println(BST_6601606.search(20));
		System.out.println(BST_6601606.search(25));
		System.out.println(BST_6601606.search(12));
		System.out.println(BST_6601606.search(1));
		BST_6601606.delete(12);
		BST_6601606.delete(25);
		BST_6601606.delete(100);
		System.out.println(BST_6601606.search(12));
		System.out.println(BST_6601606.search(20));
	}
	public static void task2() {
		System.out.println("--- task 2 ---");
		int data[] = {60, 41, 65, 63, 70, 53,
					  16, 46, 55, 62, 64, 42};
		
		BST_6601606 BST_6601606 = new BST_6601606();
		for (int i=0; i<data.length; i++)
			BST_6601606.insert(data[i]);

		BST_6601606.printPreOrder();
		BST_6601606.printInOrder();
		BST_6601606.printPostOrder();
		System.out.println(BST_6601606.findMin());
		System.out.println(BST_6601606.findMax());
		System.out.println(BST_6601606.height());
		System.out.println(BST_6601606.count());
		BST_6601606.delete(53);
		System.out.println(BST_6601606.height());
		System.out.println(BST_6601606.count());
	}
	public static void task3() {
		System.out.println("--- task 3 ---");
		median();
		// closest();
		rank();
	}
	public static void median() {
		System.out.println("-- test find median --");
		int data[] = { 20, 22, 8, 12, 4, 10, 14 };
		
		BST_6601606 BST_6601606 = new BST_6601606();
		for (int i=0; i<data.length; i++)
			BST_6601606.insert(data[i]);
		System.out.println(BST_6601606.findMedian()); // 12.0
		BST_6601606.insert(16);
		System.out.println(BST_6601606.findMedian()); // 13.0
		BST_6601606.insert(25);
		System.out.println(BST_6601606.findMedian()); // 14.0
	}
	// public static void closest() {
	// 	System.out.println("-- test find closest --");
	// 	int data[] = { 9, 4, 17, 3, 6, 22, 5, 7, 20 };
		
	// 	BST_6601606 BST_6601606 = new BST_6601606();
	// 	for (int i=0; i<data.length; i++)
	// 		BST_6601606.insert(data[i]);
	// 	System.out.println(BST_6601606.findClosest(4)); // 4
	// 	System.out.println(BST_6601606.findClosest(18)); // 17
	// 	System.out.println(BST_6601606.findClosest(12)); // 9
	// 	System.out.println(BST_6601606.findClosest(2)); // 3
	// }
	public static void rank() {
		System.out.println("-- test find rank --");
		int data[] = { 15, 20, 10, 18, 16, 12, 8, 25, 19, 30 };
		
		BST_6601606 BST_6601606 = new BST_6601606();
		for (int i=0; i<data.length; i++)
			BST_6601606.insert(data[i]);
		System.out.println(BST_6601606.findRank(12)); // 3
		System.out.println(BST_6601606.findRank(20)); // 8
		System.out.println(BST_6601606.findRank(13)); // -1
		System.out.println(BST_6601606.findRank(31)); // -1
	}
}