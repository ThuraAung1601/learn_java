class array {
    int MAX_SIZE = 2;
    int[] data = new int[MAX_SIZE];
    int size = 0;
    array() {}
    array(int ... a) {
        if(a.length > MAX_SIZE) {
            expand(a.length);
        }
        for(int i = 0; i < a.length; i++) {
            data[size++] = a[i];
        }
    }
    public void add(int num) {
        if (isFull()) {
            expand(2);
        }
        data[size] = num;
        size++;
    }
    public int getAt(int index) {
        return data[index];
    }
    public void setAt(int num, int index) {
        data[index] = num;
    }
    public boolean isFull() {
        return size == MAX_SIZE;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void expand(int k) {
        MAX_SIZE *= k;
        int[] temp = new int[MAX_SIZE];
        for(int i = 0; i < size; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }
    public void delete(int index) {
        for(int i = index; i < size-1; i++) {
            data[i] = data[i+1];
        }
        size--;
    }
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for(int i = 0; i < size-1; i++) {
            sb.append(data[i]);
            sb.append(", ");
        }
        sb.append(data[size-1]);
        sb.append("]");
        return sb.toString();
    }
}

public class array_prac {
    public static void main(String[] args) {
        array arr1 = new array();
        arr1.add(1);
        arr1.add(2);
        arr1.add(3);
        System.out.println(arr1);

        array arr2 = new array(3, 4, 5, 6, 7, 8, 9);
        System.out.println(arr2);

        arr2.delete(3);
        System.out.println(arr2);
    }
}
