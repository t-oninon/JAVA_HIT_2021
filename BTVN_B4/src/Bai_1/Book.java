package Bai_1;

import java.util.Scanner;
public class Book {
    private String maSach;
    private String tenSach;
    private String nxb;
    private int soTrang;
    private double giaTien;

    Scanner sc = new Scanner(System.in);
    public void nhap() {
        System.out.print("Nhap ma sach: ");
        maSach = sc.nextLine();
        System.out.print("Nhap ten sach: ");
        tenSach = sc.nextLine();
        System.out.print("Nhap nha xuat ban: ");
        nxb = sc.nextLine();
        System.out.print("Nhap nha so trang: ");
        soTrang = sc.nextInt();
        System.out.print("Nhap gia tien: ");
        giaTien = sc.nextDouble();
    }

    public void xuat() {
        System.out.printf(" %-10s| %-20s| %-20s| %-10d| %-10.3f%n", maSach, tenSach, nxb, soTrang, giaTien);
    }
    
}
