package Bai_3;

import java.util.Scanner;
public class Hang {
    private String maHang;
    private String tenHang;
    private double donGia;

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    Scanner sc = new Scanner(System.in);
    public void nhap() {
        System.out.print("Nhap ma hang: ");
        maHang = sc.nextLine();
        System.out.print("Nhap ten hang: ");
        tenHang = sc.nextLine();
        System.out.print("Nhap don gia: ");
        donGia = sc.nextDouble();
    }

    public void xuat() {
        System.out.printf(" %-20s| %-20s| %-20s\n", maHang, tenHang, donGia);
    }
}
