import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class sorting_prac {

    // consecutive compare
    // // arr.length - 1 - i
    public static void bubbleSort(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    // i to i+... - two pointer like
    // // 1. cur 2. search min to right 3. swap min and cur 4. cur+1
    public static void selectionSort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            int min = i;
            for(int j = i+1; j < arr.length; j++) {
                if(arr[j] <= arr[min]) {
                    min = j;
                }
            }
            if(min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }

    // move the comparison to the left and compare until smallest
    public static void insertionSort(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            int j; int temp = arr[i];
            for(j = i-1; j >= 0; j--) {
                if(arr[j] <= temp) {
                    break;
                }
                arr[j+1] = arr[j];
            }
            arr[j+1] = temp;
        }
    }

    public static void insertionSort2(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            int temp = arr[i]; 
            int j = i - 1;
            while(j >= 0 && arr[j] > temp) {
                arr[j+1] = arr[j]; j--;
            }   
            arr[j+1] = temp;
        }
    }

    // shell sort
    // 1. make a gap (half of arr.length) 
    // 2. gap /= 2 3. compare gap elements
    // average complexity depends on the gap
    public static void shellSort(int[] arr) {
        for(int gap = arr.length/2; gap > 0; gap /= 2) {
            for(int i = gap; i < arr.length; i++) {
                int temp = arr[i]; int j;
                for(j = i; j>= gap && arr[j-gap] > temp; j -= gap) {
                    arr[j] = arr[j-gap];
                }
                arr[j] = temp;
            }
        }
    }

    // merge sort
    public static void merge(int[]arr, int start, int mid, int end) {
        int leftLen = mid - start + 1; 
        int rightLen = end - mid;

        int[] left = new int[leftLen]; 
        int[] right = new int[rightLen];

        for(int i = 0; i < leftLen; i++) {
            left[i] = arr[start + i];
        }
        for(int j = 0; j < rightLen; j++) {
            right[j] = arr[mid+1 + j];
        }

        int i = 0; // left arr
        int j = 0; // right arr
        int k = start; // result arr
        while (i < leftLen && j < rightLen) {
            if(left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while(i < leftLen) {
            arr[k++] = left[i++];
        }

        while(j < rightLen) {
            arr[k++] = right[j++];
        }

    }
    
    public static void mergeSort(int[] arr, int start, int end) {
        if(start < end) {
            int mid = (start+end)/2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid+1, end);
            merge(arr, start, mid, end);
        }
    }

    // quick sort
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int left = low - 1; int right = high + 1;
        while (true) { 
            do { 
                left++;
            } while (arr[left] < pivot);
            do {
                right--;
            } while (arr[right] > pivot);
    
            if(left >= right) {
                // partition index
                return right;
            }
    
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if(low < high) {
            int j = partition(arr, low, high);
            quickSort(arr, low, j);
            quickSort(arr, j+1, high);
        }
    }

    public static void countSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();  // Find the maximum value
        int min = Arrays.stream(arr).min().getAsInt();  // Find the minimum value
        int range = max - min + 1;  // Range of values

        // Step 1: Create and populate the count array
        int[] count = new int[range];
        int[] output = new int[arr.length];  // Output array to store sorted elements

        // Count the occurrences of each element
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }

        // Step 2: Modify the count array (cumulative sum)
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Step 3: Build the output array
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        // Step 4: Copy the output array to the original array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }

    public static void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];  // Output array to store sorted numbers
        int[] count = new int[10];  // There are 10 digits (0-9)

        // Initialize count array as 0
        Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        // Change count[i] so that count[i] contains the position of this digit in output[]
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array by placing numbers in the correct sorted position
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Copy the output array to arr[], so that arr[] now contains sorted numbers based on the current digit
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // radix sorts
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    // The main function that sorts the array using Radix Sort
    public static void radixSort(int[] arr) {
        // Find the maximum number to determine the number of digits
        int max = getMax(arr);

        // Do counting sort for every digit, starting from the least significant digit (units place)
        // exp is 10^i where i is the current digit number (1 for units, 10 for tens, etc.)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    // Bucket Sort function
    public static void bucketSort(float[] arr) {
        int n = arr.length;

        if (n <= 0)
            return;

        // Create n empty buckets
        ArrayList<Float>[] buckets = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<Float>();
        }

        // Put array elements in different buckets
        for (int i = 0; i < n; i++) {
            int bucketIndex = (int) arr[i] * n; // Index in bucket
            buckets[bucketIndex].add(arr[i]);
        }

        // Sort individual buckets
        for (int i = 0; i < n; i++) {
            Collections.sort(buckets[i]);
        }

        // Concatenate all buckets into arr[]
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j);
            }
        }
    }    

    public static void main(String args[]) {
        int[] arr ={1, 11, 2, -1, 3, 4, 100, 5, 0};
        for(int i : arr) {
            System.out.print(i + ", ");
        }
        System.out.println();

        // bubbleSort(arr);
        // for(int i : arr) {
        //     System.out.print(i + ", ");
        // }

        // selectionSort(arr);
        // for(int i : arr) {
        //     System.out.print(i + ", ");
        // }

        // insertionSort(arr);
        // for(int i : arr) {
        //     System.out.print(i + ", ");
        // }

        // mergeSort(arr, 0, arr.length-1);
        // for(int i : arr) {
        //     System.out.print(i + ", ");
        // }

        quickSort(arr, 0, arr.length-1);
        for(int i : arr) {
            System.out.print(i + ", ");
        }
    }
}