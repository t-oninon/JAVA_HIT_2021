package Bai_1;

import java.util.Scanner;

public class RunMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("m: ");
        int m = sc.nextInt();
        System.out.print("n: ");
        int n = sc.nextInt();   
        for (int i = 0; i < m; i++) 
			System.out.print("*");
        System.out.println();
        for (int i = 1; i < n - 1; i++){
            System.out.print("*");
            for (int j = 1; j < m - 1; j++) 
			    System.out.print(" "); 
            System.out.print("*\n");
        }
        for (int i = 0; i < m; i++) 
			System.out.print("*");       
    }
}
