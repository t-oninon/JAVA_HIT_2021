package Bai_3;

import java.util.Random;
public class RunMain {
    public static void main(String[] args) {
        Random rd = new Random();
        Guns DiepBeDe = new Guns("AK");
        Guns DoanXinhGai = new Guns("M4A1");

        System.out.printf("| %-12s | %-15s | %-15s |%n", "Giai doan", "DiepBeDe", "DoanXinhGai");
        System.out.printf("| %-12s | %-15d | %-15d |%n", "Ban dau", 100 , 100);
        while(true) {
            System.out.println();
            // Ban
            for(int j = 1; j <= 5; j++) {
                String res1 = DiepBeDe.shoot(rd.nextInt(10) + 1);
                String res2 = DoanXinhGai.shoot(rd.nextInt(10) + 1);
                System.out.printf("| %-12s | %-15s | %-15s |%n", "Ban", res1 , res2);
                
                if (res1.equals("Het dan") && res2.equals("Het dan")) {
                    System.out.println("\nHai nguoi choi cung het dan");
                    System.exit(0);
                } 
                if (res1.equals("Het dan")) {
                    System.out.println("\nNguoi choi DoanXinhGai gianh chien thang");
                    System.exit(0);
                } 
                if (res2.equals("Het dan")) {
                    System.out.println("\nNguoi choi DiepBeDe gianh chien thang");
                    System.exit(0);
                }
            }

            //Bắn 5 lần rồi nạp đạn
            System.out.printf("| %-12s | %-15d | %-15d |%n", "Nap", DiepBeDe.load(rd.nextInt(10) + 1) , DoanXinhGai.load(rd.nextInt(10) + 1));
        }
    }
}
