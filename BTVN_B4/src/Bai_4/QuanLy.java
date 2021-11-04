package Bai_4;

import java.util.Scanner;
public class QuanLy {
    private String maQl;
    private String hoTen;

    Scanner sc = new Scanner(System.in);
    
    public void nhap() {
        System.out.print("Nhap ma quan ly: ");
        maQl = sc.nextLine();
        System.out.print("Nhap ho ten: ");
        hoTen = sc.nextLine();
    }

    public void xuat() {
        System.out.println("Ma quan ly: " + maQl);
        System.out.println("Ho ten: " + hoTen);
    }
}
