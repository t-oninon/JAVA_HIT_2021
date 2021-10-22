package Bai_2;

import java.util.Scanner;

public class RunMain {
    static Scanner sc = new Scanner(System.in);
    static int n = 0;
    static int[] a = new int[1000];

    public static void Nhap() {
        do {
            System.out.print("Nhap do dai mang: ");
            n = sc.nextInt();
        } while (n < 0);
        for (int i = 0; i < n; i++) {
            System.out.print("a[" + i + "] = ");
            a[i] = sc.nextInt();
        }
    }

    public static void Xuat() {
        if (n == 0) {
            System.out.println("Mang a khong co phan tu nao");
        } else {
            System.out.print("Mang a gom " + n + "phan tu la: ");
            for (int i = 0; i < n; i++)
                System.out.print(a[i] + " ");
            System.out.println();
        }
    }

    public static void ThemPt() {
        System.out.print("Nhap so can them: ");
        int m = sc.nextInt();
        System.out.print("Nhap vi tri k can chen: ");
        int k = sc.nextInt();
        // chuan hoa neu k ko nam trong vi tri 1 den n + 1
        if (k < 1)
            k = 1;
        if (k > n + 1)
            k = n + 1;
        // Co vi tri = chi so + 1 nen de lay chi so t phai tru k đi 1
        k--;
        n++;
        for (int i = n - 1; i > k; i--)
            a[i] = a[i - 1];
        a[k] = m;
    }

    public static void XoaPt() {
        if (n == 0) {
            System.out.println("Mang a khong co phan tu nao");
        } else {
            System.out.print("Nhap vi tri can xoa: ");
            int k = sc.nextInt();
            // chuan hoa neu k ko nam trong vi tri 1 den n + 1
            if (k < 1)
                k = 1;
            if (k > n)
                k = n;
            // Thuc hien xoa vi tri k bang cach xoa chi so k - 1
            for (int i = k - 1; i < n - 1; i++)
                a[i] = a[i + 1];
            n--;
        }
    }

    public static void MangDao() {
        if (n == 0) {
            System.out.println("Mang a khong co phan tu nao");
        } else {
            System.out.println("Mang dao nguoc cua a la: ");
            for (int i = n - 1; i >= 0; i--)
                System.out.print(a[i] + " ");
            System.out.println();
        }
    }

    public static void HienThiSo() {
        if (n < 2) {
            System.out.println("Khong co phan tu a[1]");
        } else {
            System.out.println("a[1] = " + a[1]);
            System.out.println("Cac so chia het cho " + a[1] + "la: ");
            for (int i = 0; i < n; i++)
                if (a[i] % a[1] == 0)
                    System.out.print(a[i] + " ");
            System.out.println();
        }
    }

    public static boolean IsPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return n > 1;
    }

    public static void TongNguyenTo() {
        if (n == 0) {
            System.out.println("Mang a khong co phan tu nao");
        } else {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (IsPrime(a[i])) {
                    sum += a[i];
                }
            }
            System.out.println("Tong so nguyen to cua mang la: " + sum);
        }
    }

    public static void main(String[] args) {

        int choose;
        do {
            System.out.println("-------------MENU--------------");
            System.out.println("\t1. Tao mang n so nguyen");
            System.out.println("\t2. Hien thi mang ra man hinh");
            System.out.println("\t3. Them phan tu vao vi tri k");
            System.out.println("\t4. Xoa mot phan tu o vi tri k");
            System.out.println("\t5. Dao nguoc mang");
            System.out.println("\t6. Hien thi phan tu a[i] va cac so chia hiet cho no");
            System.out.println("\t7. Tinh tong cac so nguyen to");
            System.out.println("\t8. Thoat chuong trinh");

            do {
                System.out.print("Nhap lua chon: ");
                choose = sc.nextInt();
            } while (choose < 0 || choose > 8);

            switch (choose) {
            case 1:
                Nhap();
                break;
            case 2:
                Xuat();
                break;
            case 3:
                ThemPt();
                break;
            case 4:
                XoaPt();
                break;
            case 5:
                MangDao();
                break;
            case 6:
                HienThiSo();
                break;
            case 7:
                TongNguyenTo();
                break;
            }
        } while (choose != 8);
        System.out.println("KET THUC");
    }
}
