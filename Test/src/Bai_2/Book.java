package Bai_2;

import java.util.Scanner;
public class Book extends Document {
    private int numberOfPages;
    private String author;

    public Book() {
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Name of book: ");
        name = sc.nextLine();

        System.out.print("Name of pulisher: ");
        publisher = sc.nextLine();

        System.out.print("Price of book: ");
        price = sc.nextDouble();

        System.out.print("Number of page: ");
        numberOfPages = sc.nextInt();

        System.out.print("Name of author: ");
        sc.nextLine();
        author = sc.nextLine();
    }

    public void output() {
        System.out.printf("| %-10d| %-20s| %-20s| %-15.3f| %-15d| %-20s| \n", id, name, publisher, price, numberOfPages, author);
    }

}