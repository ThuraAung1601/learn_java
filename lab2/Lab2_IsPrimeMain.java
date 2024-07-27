import solutions.pack2.*;

public class Lab2_IsPrimeMain {
    public static void main(String[] args) {
        testIsPrime012();

        System.out.println("Prime 0 Benchmark");
        L2_IsPrimeInterface obj = new L2_IsPrime0();
        bench_isPrime(obj);

        System.out.println("Prime 1 Benchmark");
        obj = new L2_IsPrime1();
        bench_isPrime(obj);

        System.out.println("Prime 2 Benchmark");
        obj = new L2_IsPrime2();
        bench_isPrime(obj);

        // System.out.println("Analyzing time increase for Prime 0");
        // obj = new L2_IsPrime0();
        // analyzeTimeIncrease(obj);
    }
    private static void testIsPrime012() {
        int N = 100;
        int count = 0;
        L2_IsPrimeInterface obj = new L2_IsPrime0();
        for (int n = 1; n < N; n++) {
            if (obj.isPrime(n)) count++;
        }
        System.out.println("Pi ("+ N + ")= " + count);
        count = 0;
        obj = new L2_IsPrime1();
        for (int n = 1; n < N; n++) {
            if (obj.isPrime(n)) count++;
        }
        System.out.println("Pi ("+ N + ")= " + count);
        count = 0;
        obj = new L2_IsPrime2();
        for (int n = 1; n < N; n++) {
            if (obj.isPrime(n)) count++;
        }
        System.out.println("Pi ("+ N + ")= " + count);
    }
    public static void bench_isPrime(L2_IsPrimeInterface obj) { 
        int your_cpu_factor = 1; /* increase by 10 times */
        int N = 100;
        // int count = 0;
        for (N = 100_000; N <= 1_000_000 * your_cpu_factor; N+= 100_000 * your_cpu_factor) {
            int count = 0;
            long start = System.currentTimeMillis();
            for (int n = 1; n < N; n++) {
                if (obj.isPrime(n)) count++;
            }
            long time = (System.currentTimeMillis() - start);
            System.out.println(N + "\t" + count + "\t" + time);
        }
    }

    // public static void analyzeTimeIncrease(L2_IsPrimeInterface obj) {
    //     int your_cpu_factor = 1; /* increase by 10 times */
    //     long prevTime = 0;
    //     for (int N = 100_000; N <= 1_000_000 * your_cpu_factor; N += 100_000 * your_cpu_factor) {
    //         long start = System.currentTimeMillis();
    //         for (int n = 1; n < N; n++) {
    //             obj.isPrime(n);
    //         }
    //         long time = System.currentTimeMillis() - start;
    //         if (prevTime != 0) {
    //             double timeIncreasePercent = ((double) (time - prevTime) / prevTime) * 100;
    //             double timeIncreaseFactor = (double) time / prevTime;
    //             System.out.println("N = " + N + ": Time = " + time + "ms, Increase % = " + timeIncreasePercent + "%, Increase Factor = " + timeIncreaseFactor);
    //         } else {
    //             System.out.println("N = " + N + ": Time = " + time + "ms");
    //         }
    //         prevTime = time;
    //     }
    // }
}