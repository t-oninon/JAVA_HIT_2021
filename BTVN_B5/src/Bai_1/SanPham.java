package Bai_1;

import java.util.Scanner;

public class SanPham {
    protected String maSP;
    protected String tenSP;
    protected String hangSX;
    protected String ngayNhap;
    
    public SanPham() {
    }

    public SanPham(String maSP, String tenSP, String hangSX, String ngayNhap) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.hangSX = hangSX;
        this.ngayNhap = ngayNhap;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getHangSX() {
        return hangSX;
    }

    public void setHangSX(String hangSX) {
        this.hangSX = hangSX;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma san pham: ");
        maSP = sc.nextLine();
        System.out.print("Nhap ten san pham: ");
        tenSP = sc.nextLine();
        System.out.print("Nhap hang san xuat: ");
        hangSX = sc.nextLine();
        System.out.print("Nhap ngay nhap: ");
        ngayNhap = sc.nextLine();
    }

    public void xuat(){
        System.out.printf("| %-20s| %-20s| %-20s| %-20s", maSP, tenSP, hangSX, ngayNhap);
    }
}
