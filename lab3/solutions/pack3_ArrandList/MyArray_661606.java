package solutions.pack3_ArrandList;

public class MyArray_661606 extends MyArrayBasic{
    public MyArray_661606() {
        MAX_SIZE = 100_000;
        data = new int[MAX_SIZE];
    }
    public MyArray_661606(int max) {
        MAX_SIZE = max;
        data = new int[MAX_SIZE];
    }

    boolean isFull() {
        return size == MAX_SIZE;
    }

    boolean isEmpty() {
        return size != MAX_SIZE;
    }

    public int[] expandByK(int k){
        MAX_SIZE = k * MAX_SIZE;
        int newData[] = new int[MAX_SIZE];
        System.arraycopy(data,0,newData,0,size); 
        data = newData;

        return data;
    }

    @Override
    public void add(int num) {
        if(isFull()) {
            expandByK(2);
        }  
        data[size++] = num;
    }

    @Override
    public void delete(int index) {
        if (!isEmpty()){
            for(int i=index; i<size-1; i++)
                data[i] = data[i+1]; 
            size--;
        }
    }

    public static void main(String args[]) {

    }
}
