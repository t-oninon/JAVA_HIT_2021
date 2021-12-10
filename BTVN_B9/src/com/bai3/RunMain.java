package com.bai3;

import java.util.ArrayList;
import java.util.Scanner;

public class RunMain {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<News> newsList = new ArrayList<>();
    public static void main(String[] args) {
        int choose;
        do {
            System.out.println("-----------------MENU-----------------");
            System.out.println("\t1. Insert news");
            System.out.println("\t2. View list news");
            System.out.println("\t3. Average rate");
            System.out.println("\t4. Exit");
            do {
                System.out.print("Enter your choose: ");
                choose = sc.nextInt();
            } while (choose > 4 || choose < 1);
            switch (choose) {
                case 1:
                    insertNews();
                    break;
                case 2:
                    viewList();
                    break;
                case 3:
                    viewAverangeRate();
                    break;
                case 4:
                    System.out.println("EXIT");
                    break;
            }
        } while (choose != 4);
    }

    public static void insertNews() {
        System.out.println("INSERT NEWS");
        News item = new News();
        sc.nextLine();
        System.out.print("Enter title: ");
        item.setTitle(sc.nextLine());
        System.out.print("Enter publish date: ");
        item.setPulishDate(sc.nextLine());
        System.out.print("Enter author: ");
        item.setAuthor(sc.nextLine());
        System.out.print("Enter content: ");
        item.setContent(sc.nextLine());
        newsList.add(item);
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter rate for news: ");
            item.getRateList().add(sc.nextInt());
        }
    }

    public static void viewList() {
        System.out.println("VIEW LIST NEWS");
        if (newsList.size() == 0) {
            System.out.println("The list hasn't news");
        } else {
            newsList.forEach(item -> item.display());
        }
    }

    public static void viewAverangeRate() {
        System.out.println("VIEW AVERANGE RATE");
        if (newsList.size() == 0) {
            System.out.println("The list hasn't news");
        } else {
            newsList.forEach((item) -> {
                item.caculate();
                item.display();
            });
        }
    }
}
