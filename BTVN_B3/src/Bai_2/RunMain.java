package Bai_2;

public class RunMain {
    public static void main(String[] args) {
        int[] a = {1, 6, 7, 2, 8};
        Array arr1 = new Array(a.length, a);
        
        Array arr2 = new Array();
        arr2.inputData();

        System.out.print("Mang arr1: ");
        arr1.show();
        System.out.print("Mang arr2: ");
        arr1.show();

        float res = (float)arr1.sum() / arr1.getN() - (float)arr2.sum()/arr2.getN(); ;

        if (res < 0) {
            System.out.println("Mang arr2 lon hon");
        } else if (res > 0) {
            System.out.println("Mang arr1 lon hon");
        } else {
            System.out.println("Byte");

        }
    }
}
