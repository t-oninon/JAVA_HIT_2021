package Bai_1;
import java.util.Scanner;

public class RunMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap a: ");
        int a = sc.nextInt();
        System.out.print("Nhap b: ");
        int b = sc.nextInt();
        System.out.print("Nhap c: ");
        int c = sc.nextInt();

        System.out.println("So lon nhat trong ba so " + a + ", " + b + " va " + c + " la: " + maxThird(a, b, c));
    }

    public static int maxThird(int a, int b, int c) {
        if (a > b && a > c) 
            return a;
        else 
            return ((b > c) ? b : c);
    }
}
