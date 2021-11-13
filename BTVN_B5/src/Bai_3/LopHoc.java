package Bai_3;

import java.util.ArrayList;
import java.util.Scanner;

public class LopHoc {
    private String maLH;
    private String tenLH;
    private String ngayMo;
    private int n;
    ArrayList<SinhVien> x = new ArrayList<>(n);
    private String giaoVien;

    public LopHoc() {
    }

    public LopHoc(String maLH, String tenLH, String ngayMo, int n, ArrayList<SinhVien> x, String giaoVien) {
        this.maLH = maLH;
        this.tenLH = tenLH;
        this.ngayMo = ngayMo;
        this.n = n;
        this.x = x;
        this.giaoVien = giaoVien;
    }

    public String getMaLH() {
        return maLH;
    }

    public void setMaLH(String maLH) {
        this.maLH = maLH;
    }

    public String getTenLH() {
        return tenLH;
    }

    public void setTenLH(String tenLH) {
        this.tenLH = tenLH;
    }

    public String getNgayMo() {
        return ngayMo;
    }

    public void setNgayMo(String ngayMo) {
        this.ngayMo = ngayMo;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public ArrayList<SinhVien> getX() {
        return x;
    }

    public void setX(ArrayList<SinhVien> x) {
        this.x = x;
    }

    public String getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(String giaoVien) {
        this.giaoVien = giaoVien;
    }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma lop hoc: ");
        maLH = sc.nextLine();
        System.out.print("Nhap ten lop hoc: ");
        tenLH = sc.nextLine();
        System.out.print("Nhap ngay mo: ");
        ngayMo = sc.nextLine();

        System.out.print("So sinh vien: ");
        n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            SinhVien sv = new SinhVien();
            System.out.println("Thong tin sinh vien " + (i+1) + ": ");
            sv.nhap();
            x.add(sv);
        }
        sc.nextLine();
        System.out.print("Nhap ten giao vien: ");
        giaoVien = sc.nextLine();
    }

    public void xuat() {
        System.out.println("Ma lop hoc: " + maLH);
        System.out.println("Ten lop hoc: " + tenLH);
        System.out.println("Ngay mo: " + ngayMo);
        System.out.printf("| %-20s| %-15s| %-20s| %-15s| %-15s| %-15s|%n", "Ho ten", "Ngay sinh", "Que quan", "Ma sinh vien", "Nganh", "Khoa hoc");
        for(int i = 0; i < n; i++)
            x.get(i).xuat();
        System.out.println("Giao vien: " + giaoVien);
    }
}
