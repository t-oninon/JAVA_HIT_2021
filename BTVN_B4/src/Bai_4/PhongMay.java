package Bai_4;

import java.util.Scanner;
public class PhongMay {
    private String maPhong;
    private String tenPhong;
    private double dienTich;
    private QuanLy x;
    private May[] y;
    private int n;

    Scanner sc = new Scanner(System.in);
    
    public void nhap() {
        System.out.print("Nhap ma phong: ");
        maPhong = sc.nextLine();
        System.out.print("Nhap ten phong: ");
        tenPhong = sc.nextLine();
        System.out.print("Nhap dien tich: ");
        x = new QuanLy();
        dienTich = sc.nextDouble();
        x.nhap();
        System.out.print("Nhap so may: ");
        n = sc.nextInt();
        y = new May[n];
        for (int i = 0; i < n; i++) {
            System.out.println("May thu " + (i + 1));
            y[i] = new May();
            y[i].nhap();
        }
    }

    public void xuat() {
        System.out.println("Ma phong: " + maPhong);
        System.out.println("Ten phong: " + tenPhong);
        System.out.println("Dien tich: " + dienTich);
        x.xuat();
        System.out.printf(" %-20s| %-20s| %-30s\n", "Ma may", "Kieu may", "Tinh trang");
        for (int i = 0; i < n; i++) 
            y[i].xuat();
    }
}
