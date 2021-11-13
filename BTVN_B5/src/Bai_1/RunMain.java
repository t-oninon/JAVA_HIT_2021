package Bai_1;

import java.util.ArrayList;
import java.util.Scanner;

public class RunMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.print("Nhap so dieu hoa: ");
        n = sc.nextInt();
        ArrayList <DieuHoa> dieuHoa = new ArrayList<>();
        for (int i = 0; i < n; i++){
            DieuHoa a = new DieuHoa();
            System.out.println("Nhap dieu hoa thu " + (i + 1) + ": ");
            a.nhap();
            dieuHoa.add(a);
        }
        int dem = 0;
        for (int i = 0; i < n; i++){
            if(dieuHoa.get(i).getHangSX().compareToIgnoreCase("Electrolux") == 0) {
                dem++;
            }
        }
        if (dem > 0){
            System.out.println("Danh sach dieu hoa co hang san xuat Electrolux : ");
            System.out.printf("| %-20s| %-20s| %-20s| %-20s| %-20s| %-20s \n", "MaSP", "TenSP", "TenHangSX", "NgayNhap", "CongXuat", "GiaBan");
            for (int i = 0; i < n; i++){
                if(dieuHoa.get(i).getHangSX().compareToIgnoreCase("Electrolux") == 0) {
                    dieuHoa.get(i).xuat();
                }
            }
        } else {
            System.out.println("Trong danh sach khong co hang Electrolux");
        }

        DieuHoa giaMin = dieuHoa.get(0);
        for (int i = 0; i < n; i++){
            if (dieuHoa.get(i).getGiaBan() < giaMin.getGiaBan()){
                giaMin = dieuHoa.get(i);
            }
        }

        System.out.println("--------------Cac san pham co gia ban thap nhat--------------");
        System.out.printf("| %-20s| %-20s| %-20s| %-20s| %-20s| %-20s \n", "MaSP", "TenSP", "TenHangSX", "NgayNhap", "CongXuat", "GiaBan");
        dieuHoa.get(0).xuat();
    }
}
