package Bai_5;

public class RunMain {
    public static void main(String[] args) {
        System.out.println("123 la so nguyen to: " + isPrime(123));
        System.out.println("997L la so nguyen to: " + isPrime(997L));
        System.out.println("12.0f la so nguyen to: " + isPrime(12.0f));
        System.out.println("32.0d la so nguyen to: " + isPrime(23.0));
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0)
                return false;
        return n > 1;
    }

    public static boolean isPrime(long n) {
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0)
                return false;
        return n > 1;
    }

    public static boolean isPrime(float n) {
        return false;
    }

    public static boolean isPrime(double n) {
        return false;
    }
}
