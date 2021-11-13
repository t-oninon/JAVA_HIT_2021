package Bai_2;

import java.util.Scanner;

public class SanPham {
    private String maSP;
    private String tenSP;
    private int sl;
    private double donGia;

    public void InputSP() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma san pham: ");
        maSP = sc.nextLine();
        System.out.print("Nhap ten san pham: ");
        tenSP = sc.nextLine();
        System.out.print("Nhap so luong: ");
        sl = sc.nextInt();
        System.out.print("Nhap don gia: ");
        donGia = sc.nextDouble();
    }

    public void OutputSP() {
        System.out.printf("| %-20s| %-20s| %-10d| %-15.3f| %-15.3f|\n", maSP, tenSP, sl, donGia, sl*donGia);
    }
}
