package Bai_3;

public class RunMain {
    public static void main(String[] args) {
        LopHoc a = new LopHoc();
        a.nhap();
        System.out.println("----------------------------------Xuat----------------------------------");
        a.xuat();
        System.out.println("------------------------------------------------------------------------");
        System.out.println("So sinh vien khoa 15: " + tim_k15(a));

        System.out.println("-------------------------Lop hoc sau khi sap xep-------------------------");
        sapXep(a);
        a.xuat();
    }

    public static int tim_k15(LopHoc a) {
        int dem = 0;
        for(int i = 0; i < a.getN(); i++) {
            if (a.getX().get(i).getKhoaHoc() == 15) {
                dem++;
            }
        }
        return dem;
    }

    public static void sapXep(LopHoc a) {
        for(int i = 0; i < a.getN()-1; i++) {
            for (int j = i+1; j < a.getN(); j++) {
                if(a.getX().get(i).getKhoaHoc() > a.getX().get(j).getKhoaHoc()) {
                    SinhVien tmp = a.getX().get(i);
                    a.getX().set(i, a.getX().get(j));
                    a.getX().set(j, tmp);
                }
            }
        }
    }
}
