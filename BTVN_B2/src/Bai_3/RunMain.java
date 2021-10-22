package Bai_3;

import java.util.Scanner;
public class RunMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		System.out.print("Nhap mang bat ky: ");
		String str = sc.nextLine();
		int count = 0;
		float res = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				count++;
				res += ((int) str.charAt(i) - 48);
			}
		}
		if (count > 0) {
			System.out.println("true");
			System.out.print("Trung binh cong cac so la: " + res / count);
		} else {
			System.out.println("false");
		}
    }
}
