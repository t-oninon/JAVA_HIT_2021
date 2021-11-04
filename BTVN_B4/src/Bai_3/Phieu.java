package Bai_3;

import java.util.Scanner;
public class Phieu {
    private String maPhieu;
    private Hang[] x;
    private int n;

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public Hang[] getX() {
        return x;
    }

    public void setX(Hang[] x) {
        this.x = x;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    Scanner sc = new Scanner(System.in);
    
    public void nhap() {
        System.out.print("Nhap ma phieu: ");
        maPhieu = sc.nextLine();
        System.out.print("Nhap so mat hang: ");
        n = sc.nextInt();
        x = new Hang[n];
        for (int i = 0; i < n; i++) {
            x[i] = new Hang();
            x[i].nhap();
        }
    }

    public void xuat() {
        System.out.println("Ma phieu: " + maPhieu);
        System.out.printf(" %-20s| %-20s| %-20s\n", "Ma hang", "Ten hang", "Don gia");
        for (int i = 0; i < n; i++) 
            x[i].xuat();
    }
}
