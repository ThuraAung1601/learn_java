package solutions.pack2;

public class L2_IsPrime2 implements L2_IsPrimeInterface {
    public boolean isPrime(int n) {
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
}
