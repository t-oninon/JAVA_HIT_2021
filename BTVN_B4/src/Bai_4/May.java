package Bai_4;

import java.util.Scanner;
public class May {
    private String maMay;
    private String kieuMay;
    private String tinhTrang;

    Scanner sc = new Scanner(System.in);
    public void nhap() {
        System.out.print("Nhap ma may: ");
        maMay = sc.nextLine();
        System.out.print("Nhap kieu may: ");
        kieuMay = sc.nextLine();
        System.out.print("Nhap tinh trang: ");
        tinhTrang = sc.nextLine();
    }

    public void xuat() {
        System.out.printf(" %-20s| %-20s| %-30s\n", maMay, kieuMay, tinhTrang);
    }
}
