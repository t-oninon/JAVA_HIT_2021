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
        FileWriter fileWriter = new FileWriter("Account.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);

        int choose = 0;
        do {
            System.out.println("\tMENU");
            System.out.println("\t1. Create a new account");
            System.out.println("\t2. Log in your acount");
            System.out.println("\t3. Sort acountlist by username");
            System.out.println("\t4. Exit");
            do {
                System.out.print("Enter your choose: ");
                choose = sc.nextInt();
            } while (choose < 1 && choose > 4);

            switch (choose) {
                case 1:
                    createNew();
                    printWriter.println(accountList.get(accountList.size()-1).convertToFile());
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        } while (choose == 4);
        printWriter.close();
        bufferedWriter.close();
        fileWriter.close();
    }

    static void createNew() {
        Account account = new Account();
        do {
            account.setId();
            account.setFullName();
            account.setUserName();
            account.setPassword();
            account.setEmail();
//            account.setPhone();
            account.setCreateAt(formatter.format(new Date()));
        } while (accountList.contains(account));
    }

    static void showInfor() {
        System.out.printf("%10s|%20s|%10s|%15s|%20s|%15s|%10s"
                , "Id", "FullName", "UserName", "Password",
                "Email", "Phone", "CreateAt");
        accountList.forEach((account) -> {
            System.out.println(account);
        });
    }
}
