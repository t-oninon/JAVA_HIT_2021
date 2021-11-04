package Bai_1;

import java.util.Scanner;
public class RunMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.print("Nhap so dau sach: ");
        n = sc.nextInt();
        Book[] books = new Book[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Cuon sach thu " + (i + 1));
            books[i] = new Book();
            books[i].nhap();
        }

        System.out.println();
        System.out.printf(" %-10s| %-20s| %-20s| %-10s| %-10s%n", "Ma sach", "Ten sach", "Nxb", "So trang", "Gia tien");
        for (int i = 0; i < n; i++)
            books[i].xuat();
    }

}

