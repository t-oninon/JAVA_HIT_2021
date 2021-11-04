package Bai_2;

import java.util.Scanner;
public class NhanSu {
    private String maNhanSuName;
    private String hoTen;
    private Date ngaySinh;

    Scanner sc = new Scanner(System.in);
    public void nhap() {
        System.out.print("Nhap ma nhan su: ");
        maNhanSuName = sc.nextLine();
        System.out.print("Nhap ho ten: ");
        hoTen = sc.nextLine();
        ngaySinh = new Date();
        ngaySinh.nhap();
    }

    public void xuat() {
        System.out.println("Ma nhan su: " + maNhanSuName);
        System.out.println("Ho ten: " + hoTen);
        System.out.print("Ngay sinh: ");
        ngaySinh.xuat();
    }
}
