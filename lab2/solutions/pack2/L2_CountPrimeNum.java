package solutions.pack2;

public class L2_CountPrimeNum {
    private static boolean isPrime0(int n) {
        if (n == 1)
            return false;
        if (n <= 3)
            return true;
        int m = n / 2;
        for (int i = 2; i <= m; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    private static boolean isPrime1(int n) {
        if (n == 1)
            return false;
        if (n <= 3)
            return true;
        int m = (int) Math.sqrt(n);
        for (int i = 2; i <= m; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    private static boolean isPrime2(int n) {
        if (n == 1)
            return false;
        if (n <= 3)
            return true;
        if ((n % 2 == 0) || (n % 3 == 0))
            return false;
        int m = (int) Math.sqrt(n);
        for (int i = 5; i <= m; i += 6) {
            if (n % i == 0)
                return false;
            if (n % (i + 2) == 0)
                return false;
        }
        return true;
    }

    private static void testIsPrime012() {
        int N = 100;
        int count = 0;
        for (int n = 1; n < N; n++) {
            if (isPrime0(n))
                count++;
        }
        System.out.println("Pi (" + N + ")= " + count);
        count = 0;
        for (int n = 1; n < N; n++) {
            if (isPrime1(n))
                count++;
        }
        System.out.println("Pi (" + N + ")= " + count);
        count = 0;
        for (int n = 1; n < N; n++) {
            if (isPrime2(n))
                count++;
        }
        System.out.println("Pi (" + N + ")= " + count);
    }

    public static void main(String[] args) {
        int count = 0;
        int N = 100;
        testIsPrime012();
        for (N = 100_000; N <= 1_000_000; N+= 100_000) {
            long start = System.currentTimeMillis();
            for (int n = 1; n < N; n++) {
                if (isPrime2(n)) count++;
            }
            long time = (System.currentTimeMillis() - start);
            System.out.println(N + "\t" + count + "\t" + time);
        }   
    }
}
