package Bai_4;

import java.util.Scanner;
public class RunMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Nhap chuoi can chuan hoa: ");
        String str =scan.nextLine();
        str = str.toLowerCase();
        str = str.trim();
        str = str.replaceAll("[0-9]","");
        str = str.replaceAll("\\s+", " ");

        String s[] = str.split(" ");
        str = "";
        for (int i = 0; i < s.length; i++) {
            str += String.valueOf(s[i].charAt(0)).toUpperCase() + s[i].substring(1);
            if (i < s.length - 1)
                str += " ";
        }
        System.out.println("String: " + str);
    }
}
