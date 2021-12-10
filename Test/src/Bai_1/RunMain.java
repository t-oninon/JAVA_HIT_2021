package Bai_1;


import java.util.ArrayList;
import java.util.Scanner;

public class RunMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập chuỗi muốn kiểm tra: ");
        String str = sc.next();

        ArrayList<Integer> numbers = new ArrayList<>();

        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            if(Character.isDigit(str.charAt(i))) {
                numbers.add(Character.getNumericValue(str.charAt(i)));
                sum += Character.getNumericValue(str.charAt(i));
            }
        }

        int res = 1;
        for(Integer number : numbers) {
            if(number != 0 && sum % number == 0)
                res *= number;
        }

        System.out.println("Số các chữ số: " + numbers.size());
        System.out.println("Tích các chữ số đó là: " + res);


    }
}
