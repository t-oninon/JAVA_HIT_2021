package Excercise;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RunMain {
    static Scanner sc = new Scanner(System.in);
    static List<Account> accountList = new ArrayList<>();
    static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("ACC.DAT", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);

        FileWriter fileChange = new FileWriter("Account.DAT", true);
        BufferedWriter bufferedChange = new BufferedWriter(fileChange);
        PrintWriter printChange = new PrintWriter(bufferedChange);
        int choose = 0;
        do {
            System.out.println("\t\tMENU");
            System.out.println("\t1. Create a new account");
            System.out.println("\t2. Log in your acount");
            System.out.println("\t3. Sort acountlist by username");
            System.out.println("\t4. Exit");
            do {
                System.out.print("Enter your choose: ");
                choose = sc.nextInt();
                sc.nextLine();
            } while (choose < 1 && choose > 4);

            switch (choose) {
                case 1:
                    createNew();
                    printWriter.println(accountList.get(accountList.size()-1).convertToFile());
                    break;
                case 2:
                    logIn(printChange);
                    break;
                case 3:
                    sortByUser();
                    break;
            }
        } while (choose != 4);
        printWriter.close();
        bufferedWriter.close();
        fileWriter.close();

        printChange.close();
        bufferedChange.close();
        fileChange.close();
    }

    static void createNew() {
        System.out.println("\\\\  Create a new account");
        Account account = new Account();
        while (true) {
            account.setId();
            account.setFullName();
            account.setUserName();
            account.setPassword();
            account.setEmail();
            account.setPhone();
            account.setCreateAt(formatter.format(new Date()));
            if (accountList.contains(account) == false)
                break;
            System.out.println("Account already exists");
        }
        accountList.add(account);
    }

    static void logIn(PrintWriter printChange) {
        System.out.println("\\\\  Log in your account");
        String passwordInput;
        System.out.print("Enter your username: ");
        String userNameInput = sc.nextLine();
        System.out.print("Enter your password: ");
        passwordInput = sc.nextLine();
        int indexAccount = -1;
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).canLogIn(userNameInput, passwordInput)) {
                indexAccount = i;
                break;
            }
        }
        if(indexAccount != -1) {
            System.out.println("Logged in successfully");
            int choose;
            System.out.println(" - 1. Show infor.");
            System.out.println(" - 2. Change password.");
            while (true){
                System.out.print("You choose: ");
                choose = sc.nextInt();
                sc.nextLine();
                if (choose == 1) {
                    System.out.println("\\\\  Show infor");
                    System.out.printf("%10s|%20s|%10s|%15s|%20s|%15s|%10s%n"
                            , "Id", "FullName", "UserName", "Password",
                            "Email", "Phone", "CreateAt");
                    System.out.println(accountList.get(indexAccount));
                    break;
                }
                if (choose == 2) {
                    System.out.println("\\\\  Change password");
                    accountList.get(indexAccount).setPassword();
                    printChange.println(accountList.get(indexAccount).convertToFile());
                    System.out.println("Change password successfully");
                    break;
                }
            }
        }
        else {
            System.out.println("Logged in doesn't successfully");
        }
    }

    static void sortByUser() {
        // ASC
        accountList.sort((item, pre) -> item.getUserName().compareTo(pre.getUserName()));
        System.out.printf("%10s|%20s|%10s|%15s|%20s|%15s|%10s%n"
                , "Id", "FullName", "UserName", "Password",
                "Email", "Phone", "CreateAt");
        accountList.forEach((account) -> {
            System.out.println(account);
        });
    }
}
