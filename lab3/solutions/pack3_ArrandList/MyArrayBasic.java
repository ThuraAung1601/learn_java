package solutions.pack3_ArrandList;

public class MyArrayBasic {
    int MAX_SIZE = 6;
    int[] data = new int[MAX_SIZE];
    int size = 0;
    public MyArrayBasic() {}
    public MyArrayBasic(int ... a) {
        if (a == null){
            return;       
        }
        for(int i=0; i < a.length; i++) {
            data[i] = a[i];
        }
        size = a.length;
    }

    public void insert(int num, int index) {
        data[size++] = data[index];
        data[index] = num;
    }

    public void add(int num) {
        data[size++] = num;
    }

    public void delete(int index) {
        for(int i=index; i<size-1; i++) {
            data[i] = data[i+1];
        }
        size--;
    }

    public int find(int num) {
        for (int i = 0; i < 10; i++) {
            if (data[i] == num) {
                return i;
            }
        }
        return -1;
    }

    public int binarySearch(int d){
        int a = 0, b = size-1;
        while(a <= b) {
            int middle = (a+b)/2;
            if(data[middle]==d) 
                return middle;
            if(d<data[middle]) b = middle-1;
            else a = middle+1; // d>data[m]
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for(int i=0; i<size-1; i++) {
                sb.append(data[i]);
                sb.append(",");
        }
        if(size>0) sb.append(data[size-1]); sb.append("]");
        return sb.toString();
    } 
    public static void main(String[] args) {
        // MyArrayBasic arr1 = new MyArrayBasic();
        // MyArrayBasic arr2 = new MyArrayBasic(1, 2, 3, 4, 5);
        // System.out.println(arr1);
        // System.out.println(arr2);
    }
}