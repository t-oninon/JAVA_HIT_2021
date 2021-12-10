package Bai_1;

import java.util.Scanner;
public class RunMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int dem = 0;
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                dem++;
                sum += str.codePointAt(i) - 48;
            }     
        }
        int res = 1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) > '0' && str.charAt(i) <= '9') {
                if (sum % (str.codePointAt(i) - 48) == 0) {
                    res *= (str.codePointAt(i) - 48);
                }
            }     
        }

        System.out.println("Chuoi " + str + " co: " + dem + " chu so");
        System.out.println("Tich: " + res);
    }
}
