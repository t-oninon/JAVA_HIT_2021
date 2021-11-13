package Bai_1;

import java.util.Scanner;

public class DieuHoa extends SanPham {
    private double congSuat;
    private long giaBan;

    public DieuHoa() {
    }

    public DieuHoa(String maSP, String tenSP, String hangSX, String ngayNhap, double congSuat, long giaBan) {
        super(maSP, tenSP, hangSX, ngayNhap);
        this.congSuat = congSuat;
        this.giaBan = giaBan;
    }

    public double getCongSuat() {
        return congSuat;
    }

    public void setCongSuat(double congSuat) {
        this.congSuat = congSuat;
    }

    public long getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(long giaBan) {
        this.giaBan = giaBan;
    }

    @Override
    public void nhap(){
        Scanner sc = new Scanner(System.in);
        super.nhap();
        System.out.print("Nhap cong suat: ");
        congSuat = sc.nextDouble();
        System.out.print("Nhap gia ban: ");
        giaBan = sc.nextLong();
    }

    @Override
    public void xuat(){
        super.xuat();
        System.out.printf("| %-20.3f| %-20d \n", congSuat, giaBan);
    }
}
