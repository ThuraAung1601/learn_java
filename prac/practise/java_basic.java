import java.util.ArrayList;
import java.util.Arrays;

public class java_basic {
    public static boolean isPalindrome(ArrayList ls) {
        System.out.println("ArrayList is " + ls);
        int left = 0; int right = ls.size() - 1;
        while(left < right) {
            if(ls.get(left++) == ls.get(right--)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        System.out.print("Give me a number: ");
        Scanner sn = new Scanner(System.in);
        int num = sn.nextInt();
        System.out.println(num);

        System.out.print("Give me a string: ");
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        System.out.println(str);

        System.out.println("Index 0 is " + str.charAt(0));

        for(int i = 0; i < str.length(); i++) {
            System.out.println("Index " + i + " is " + str.charAt(i));
        }

        char[] chrArr = str.toCharArray();
        // address of the array
        System.out.println("Char Array is " + chrArr);
        // print each element
        for(char ch: chrArr) {
            System.out.print(ch + " ");
        }

        System.out.print("");

        String nums = "12344321";
        System.out.println(nums);
        char[] numsCharArr = nums.toCharArray();
        for(char ch: numsCharArr) {
            int temp = Integer.parseUnsignedInt(Character.toString(ch));
            if(temp % 2 == 0) {
                System.out.println(ch + " is Even");
            } else {
                System.out.println(ch + " is Odd");
            }
        }

        ArrayList<Integer> ls = new ArrayList<Integer>();
        ls.add(1); ls.add(2); ls.add(3); ls.add(2); ls.add(1);
        System.out.println("ArrayList is " + ls);
        int left = 0; int right = ls.size() - 1;
        while(left < right) {
            if(ls.get(left++) != ls.get(right--)) {
                System.out.println("Not Palindrome!");
                break;
            } else {
                System.out.println("Is palindrome!");
                break;
            }
        }

        ArrayList<Integer> ls2 = new ArrayList<Integer>();
        ArrayList<Integer> ls3 = new ArrayList<Integer>();

        ls2.add(1); ls2.add(2); ls2.add(2); ls2.add(1);
        ls3.add(1); ls3.add(2); ls3.add(3); ls3.add(2); ls3.add(1); ls3.add(3);

        System.out.println("Is ls2 palindrome?: " + isPalindrome(ls2));
        System.out.println("Is ls3 palindrome?: " + isPalindrome(ls3));

        int[] l1 = {100, 9, -1, 1002, 4, 5};
        System.out.print("Unsorted: ");
        for(int i: l1) {
            System.out.print(i + " ");
        }
        Arrays.sort(l1); 
        System.out.println("");
        System.out.print("Sorted: ");
        for(int i: l1) {
            System.out.print(i + " ");
        }
    }
}
