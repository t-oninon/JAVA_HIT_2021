package Bai_2;

import java.util.ArrayList;
import java.util.Scanner;
public class RunMain {
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in);
        int choose = 0;
        ArrayList<Book> books = new ArrayList<Book>();
        int n = 0;
        while (choose != 8) {
            System.out.println("----------------------MENU----------------------");
            System.out.println("\t1. Add book");
            System.out.println("\t2. Edit book by id");
            System.out.println("\t3. delete book by id");
            System.out.println("\t4. Sort book by name");
            System.out.println("\t5. Sort book by price");
            System.out.println("\t6. Show all book");
            System.out.println("\t7. Exit");
            do {
                System.out.print("Your choose: ");
                choose = sc.nextInt();
            } while (choose < 1 || choose > 7);

            switch (choose) {
                case 1: 
                    System.out.println("\tStart add book");
                    Book element = new Book();
                    element.input();
                    books.add(element);
                    n++;
                    break;
                case 2: 
                    System.out.println("\tStart edit book by id");
                    break;
                case 3: 
                    System.out.println("\tStart delete book by id");
                    break;
                case 4: 
                    System.out.println("\tSort book by name");
                    break;
                case 5: 
                    System.out.println("\tSort book by price");
                    break;
                case 6:
                    System.out.println("\tStart show book");
                    System.out.printf("| %-10s| %-20s| %-20s| %-15s| %-15s| %-20s| \n", "id", "name", "publisher", "price", "numberOfPages", "author");
                    for (int i = 0; i < n; i++) {
                        books.get(i).output();
                    }
                    break;
                case 7: 
                    System.out.println("\tEXIT");
                    break;
            }
        }
    }
}
