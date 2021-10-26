package Bai_2;

import java.util.Scanner;
public class Array {
    public int n;
    private int[] a;

    public static Scanner sc = new Scanner(System.in);

    public Array() {
        this.n = 0;
        this.a = null;
    }

    public Array(int n, int[] a) {
        this.n = n;
        this.a = a;
    }

    public void inputData() {
        System.out.print("Nhap n: ");
        n = sc.nextInt();
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
    }

    public void show() {
        for (int element : a) 
            System.out.print(element + " ");
        System.out.println();
    }

    public int sum() {
        int sum = 0;
        for (int element : a) 
            sum += element;
        return sum;
    }

    public void Filter(boolean flag) {
        if (flag) {
            for (int element : a) 
                if(element % 2 == 0)
                    System.out.print(element + " ");
        } else {
            for (int element : a) 
                if(element % 2 == 1)
                    System.out.print(element + " ");
        }
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int[] getA() {
        return a;
    }

    public void setA(int[] a) {
        this.a = a;
    }
}
