package Bai_3;

import java.util.Scanner;

public class SinhVien extends Nguoi {
    private String maSV;
    private String nganh;
    private int khoaHoc;

    public SinhVien() {
    }

    public SinhVien(String hoTen, String ngaySinh, String queQuan, String maSV, String nganh, int khoaHoc) {
        super(hoTen, ngaySinh, queQuan);
        this.maSV = maSV;
        this.nganh = nganh;
        this.khoaHoc = khoaHoc;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public int getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(int khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        super.nhap();
        System.out.print("Nhap ma sinh vien: ");
        maSV = sc.nextLine();
        System.out.print("Nhap nganh: ");
        nganh = sc.nextLine();
        System.out.print("Nhap khoa hoc: ");
        khoaHoc = sc.nextInt();
    }

    public void xuat() {
        super.xuat();
        System.out.printf("| %-15s| %-15s| %-15d|%n", maSV, nganh, khoaHoc);
    }
}
