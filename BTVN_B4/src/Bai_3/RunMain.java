package Bai_3;

public class RunMain {
    public static void main(String[] args) {
        Phieu p = new Phieu();
        p.nhap();
        System.out.println();
        p.xuat();
        System.out.println("Tong tien cua phieu: " + sum(p));
    }

    public static double sum(Phieu p) {
        double sum = 0;
        for (int i = 0; i < p.getN(); i++) 
            sum += (p.getX()[i].getDonGia());
        return sum;
    }
}
