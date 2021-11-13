package Bai_2;

import java.util.ArrayList;
import java.util.Scanner;

public class Phieu {
    private String maPhieu;
    private String tenPhieu;
    DateTime a = new DateTime();
    private int n;
    ArrayList<SanPham> x = new ArrayList<>(n);

    public void InputPhieu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ma phieu: ");
        maPhieu = sc.nextLine();
        System.out.print("Ten phieu: ");
        tenPhieu = sc.nextLine();
        a.InputDate();
        System.out.print("Nhap so luong san pham: ");
        n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            SanPham sp = new SanPham();
            System.out.println("Thong tin san pham " + (i + 1) + ": ");
            sp.InputSP();
            x.add(sp);
        }
    }
    public void OutputPhieu() {
        System.out.println("Ma phieu: " + maPhieu);
        System.out.println("Ten phieu: " + tenPhieu);
        a.OutputDate();
        System.out.println("Thong tin cac san pham:");
        System.out.printf("| %-20s| %-20s| %-10s| %-15s| %-15s|\n", "Ma san pham", "Ten san pham", "So luong", "Don gia", "Thanh tien");
        for (int i = 0; i < n; i++)
            x.get(i).OutputSP();

    }
}
