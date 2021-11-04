package Bai_2;

import java.util.Scanner;

public class Date {
    private int day; 
    private int month;
    private int year;

    public Scanner sc = new Scanner(System.in);

    public void nhap() {
        System.out.print("Nhap ngay/thang/nam: ");
        day = sc.nextInt();
        month = sc.nextInt();
        year = sc.nextInt();
    }

    public void xuat() {
        System.out.println(day + "/" + month + "/" + year);
    }
}
