package data;

import controller.*;
import model.Account;
import model.Bill;
import model.Course;
import run.RunMain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Base {
    public static String nameAcademy = "Code++";
    public static String author = "Nguyễn Trung Thành";
    public static String dateEstablish = "01/01/2022";
    public static String address = "Mọi nơi khi bạn kết nối internet";
    public static String hotline = "0987 112 345";
    public static String email = "t.onion.2002@gmail.com";
    public static List<Course> courses = CourseControl.readAllRecord();
    public static List<Account> accounts = AccountControl.readAllRecord();
    public static List<Bill> bills = BillControl.readAllRecord();
    public static Scanner sc = new Scanner(System.in);

    // List ra toàn bộ các khóa học trong base
    public static void viewCoureList() {
        courses.forEach(item -> {
            System.out.printf("|  %-5s  |  %-35s  |%n", item.getId(), item.getName());
        });
    }

    // List ra toàn bộ các account trong base
    public static void viewAccountList() {
        accounts.forEach(item -> {
            System.out.printf("|  %-10s  |  %-15s  |  %-15s  |  %-5s  |  %-25s  |  %10.2f  |%n",
                    item.getId(), item.getUserName(), item.getPassword(), item.getAdmin(), item.getFullName(), item.getBalance());
        });
    }

    public static void viewAccountListWith(Account account, String sort, String by) {
        accounts.forEach(item -> {
            System.out.printf("|  %-10s  |  %-15s  |  %-15s  |  %-5s  |  %-25s  |  %10.2f  |%n",
                    item.getId(), item.getUserName(), item.getPassword(), item.getAdmin(), item.getFullName(), item.getBalance());
        });
    }

    // List ra toàn bộ các bill trong base
    public static void viewBillList() {
        accounts.forEach(item -> {
            System.out.printf("|  %-10s  |  %-15s  |  %-15s  |  %-5s  |  %-25s  |  %10.2f  |%n",
                    item.getId(), item.getUserName(), item.getPassword(), item.getAdmin(), item.getFullName(), item.getBalance());
        });
    }

    // Hàm ràng buộc lựa chọn nằm trong đoạn [start, end]
    public static int chooseIn(int start, int end) {
        int choose;
        while (true) {
            System.out.print("Lựa chọn: ");
            choose = sc.nextInt();
            sc.nextLine();
            if (choose >= start && choose <= end)
                break;
            System.out.println("Sai rồi! ");
        }
        return choose;
    }

    public static String stringRequest(int n) {
        String result;
        while(true) {
            result = sc.nextLine();
            if(result.length() > n) {
                System.out.println("\tSai rồi! Chuỗi nhập vào tối đa " + n + " kí tự");
                System.out.println("\tNhập lại: ");
            } else {
                break;
            }
        }
        return result;
    }

    public static boolean ynQuestion() {
        return sc.nextLine().equalsIgnoreCase("y");
    }

    public static String toParagraph(String str) {
        int i = 0;
        int lineBreak = 80;
        if (str.length() < lineBreak)
            return str;
        String newString = "";
        while (i + lineBreak < str.length()) {
            i += lineBreak;
            while (str.charAt(i) != ' ')
                i--;
            newString += str.substring(newString.length(), i) + "\n";
        }
        newString += str.substring(i+1, str.length());
        return newString;
    }



    // Hàm đăng nhập
    public static void logIn() {
        System.out.println("\nGiao diện > Đăng nhập");
        String userName, password;
        System.out.print("\tNhập tên tài khoản: ");
        userName = sc.nextLine();
        System.out.print("\tNhập mật khẩu: ");
        password = sc.nextLine();
        String id = AccountControl.userNameExist(userName);
        if(id == null) {
            System.out.println("\t\tTên tài khoản không tồn tại ☹");
            RunMain.home();
        } else {
            Account target = AccountControl.findRecord(id);
            if(!target.getPassword().equals(password)) {
                System.out.println("\t\tSai mật khẩu ☹");
                RunMain.home();
            } else {
                System.out.println("\t\tĐăng nhập thành công ☺");
                RunMain.accountJoin = target;
                if(target.getAdmin())
                    RunMain.adminProcess(target);
                else
                    RunMain.userProcess(target);
            }
        }
    }

    public static void logOut() {
        System.out.println("\t\tĐã đăng xuất ☺");
        RunMain.accountJoin = null;
        RunMain.home();
    }

    // Hàm đăng ký
    public static void register() {
        System.out.println("\nGiao diện > Đăng ký");
        String userName = AccountControl.requestUserName();
        String password = AccountControl.requestPassword();
        String fullName;
        System.out.print("\tNhập họ tên[0-25]: ");
        fullName = stringRequest(25);
        if(AccountControl.userNameExist(userName) != null) {
            System.out.println("\t\tTài khoản đã tồn tại ☹");
            RunMain.home();
        } else {
            String lastId = accounts.get(accounts.size()-1).getId();
            String autoId = "AC" + String.format("%08d", Integer.parseInt(lastId.substring(2, 10))+1);
            Account account = new Account(autoId, userName, password, false, fullName, 0);
            AccountControl.insertRecord(account);
            accounts.add(account);
            System.out.println("\t\tĐăng ký hoàn tất ☺");
            System.out.println("\t1. Đi đến đăng nhập");
            System.out.println("\t2. Trở về phần giao diện");
            int choose = chooseIn(1,2);
            if (choose == 1)
                logIn();
            else
                RunMain.home();
        }
    }

    public static int viewCourseWithList(List<Course> courseList, boolean isLogIn, int type) {
        System.out.println(Constants.courseHR + "\n" + Constants.courseTitle);
        for (int i = 0; i < courseList.size(); i++) {
            System.out.printf("| %3d |  %-5s  |  %-30s  |  %-8s  |  %7.2f$  |\n",
                    i+1, courseList.get(i).getId(), courseList.get(i).getName(),
                    RateControl.rateAVG(courseList.get(i).getId()), courseList.get(i).getPrice());
        }
        System.out.println(Constants.courseHR);
        if(!isLogIn) {
            System.out.println("\t\tBạn chưa đăng nhập ☹");
            System.out.println("\t1. Đi đến đăng nhập");
            System.out.println("\t2. Trở về phần giao diện");
            int choose = chooseIn(1,2);
            if (choose == 1)
                logIn();
            else
                RunMain.home();
            return 0;
        }
        else {
            System.out.print("Nhập Enter để tiếp tục: ");
            Base.sc.nextLine();
            System.out.println("|------------------| 1. Trở lại");
            System.out.println("|------------------| 2. Vào xem chi tiết");
            System.out.println("|                  | 3. Sắp xếp tăng dần theo tên khóa học");
            System.out.println("|    ,_♥_,   ~     | 4. Sắp xếp tăng dần theo vote");
            System.out.println("|    [O.o]  /      | 5. Sắp xếp tăng dần theo giá");
            System.out.println("|    /)__)>/       | 6. Sắp xếp giảm dần theo tên khóa học");
            System.out.println("|----\"--\"-         | 7. Sắp xếp giảm dần theo vote");
            System.out.println("|------------------| 8. Sắp xếp giảm dần theo giá");
            if(type != 2)
                System.out.println("|------------------| 9. Mua khóa học");
            int saveChoose = (type != 2) ? chooseIn(1, 9) : chooseIn(1, 8);
            if (type == 1)
                System.out.print("\nTrang chủ > ... toàn bộ khóa học");
            if (type == 2)
                System.out.print("\nTrang chủ > ... khóa học của bạn");
            if (type == 3)
                System.out.print("\nTrang chủ > ... khóa học chưa mua");
            if (saveChoose == 2) {
                System.out.println(" > Xem chi tiết");
                System.out.println("\tBạn muốn xem khóa học số? ");
                int courseIndex = Base.chooseIn(1, courseList.size()+1);
                RunMain.courseJoin = courseList.get(courseIndex-1);
                courseList.get(courseIndex-1).join();
                return 0;
            }
            if (saveChoose == 3) {
                System.out.println(" > Tên khóa tăng dần");
                courseList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
                viewCourseWithList(courseList, true, type);
                return 0;
            }
            if (saveChoose == 4) {
                System.out.println(" > Vote tăng dần");
                courseList.sort((o1, o2) -> RateControl.rateAVG(o1.getId()).compareTo(RateControl.rateAVG(o2.getId())));
                viewCourseWithList(courseList, true, type);
                return 0;
            }
            if (saveChoose == 5) {
                System.out.println(" > Giá tăng dần");
                courseList.sort((o1, o2) -> (o1.getPrice()>o2.getPrice() ? 1 : -1));
                viewCourseWithList(courseList, true, type);
                return 0;
            }
            if (saveChoose == 6) {
                System.out.println(" > Tên khóa giảm dần");
                courseList.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
                viewCourseWithList(courseList, true, type);
                return 0;
            }
            if (saveChoose == 7) {
                System.out.println(" > Vote giảm dần");
                courseList.sort((o1, o2) -> RateControl.rateAVG(o2.getId()).compareTo(RateControl.rateAVG(o1.getId())));
                viewCourseWithList(courseList, true, type);
                return 0;
            }
            if (saveChoose == 8) {
                System.out.println(" > Giá giảm dần");
                courseList.sort((o1, o2) -> (o2.getPrice()>o1.getPrice() ? 1 : -1));
                viewCourseWithList(courseList, true, type);
                return 0;
            }
            if (saveChoose == 9) {
                System.out.println(" > Xem các khóa học với");
                System.out.println("\tBạn muốn mua khóa học số? ");
                int courseWantBuy = Base.chooseIn(1, courseList.size()+1);
                RunMain.courseJoin = courseList.get(courseWantBuy-1);
                System.out.println(RunMain.courseJoin.getId());
                System.out.println("Số dư của bạn là: " + RunMain.accountJoin.getBalance());
                System.out.print("Bạn có muốn mua khóa học này với giá " +
                        RunMain.courseJoin.getPrice() + "$ hay không[y/n]: ");
                if(Base.ynQuestion())
                    Base.buyCourse();
                viewCourseWithList(courseList, true, type);
                return 0;
            }
        }
        return 0;
    }

    public static void buyCourse() {
        if (isBought()) {
            System.out.println("\t\tBạn đã mua khóa học này rồi!");
        } else {
            if(RunMain.courseJoin.getPrice() > RunMain.accountJoin.getBalance()) {
                System.out.println("\tSố dư của bạn không đủ để thanh toán ☹");
                System.out.print("Đi đến nạp tiền [y/n]? ");
                if(Base.ynQuestion())
                    UserControl.rechargeIntoAccount(RunMain.accountJoin);
            } else {
                double beforeBalane = RunMain.accountJoin.getBalance();
                double balance = beforeBalane - RunMain.courseJoin.getPrice();
                RunMain.accountJoin.setBalance(balance);
                String lastId = bills.get(bills.size()-1).getId();
                String autoId = "B" + String.format("%04d", Integer.parseInt(lastId.substring(1, 5))+1);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Bill bill = new Bill(autoId, RunMain.courseJoin.getId(), RunMain.accountJoin.getId(),
                        formatter.format(new Date()));
                BillControl.insertRecord(bill);
                System.out.println("\t\tĐã mua thành công ☺");
                System.out.printf("\tSố dư hiện tại là: " + beforeBalane + " - " + RunMain.courseJoin.getPrice()
                        + " = .2f", balance);
            }
        }
    }

    public static Boolean isBought()  {
        String courseID = RunMain.courseJoin.getId();
        String accountID = RunMain.accountJoin.getId();
        try {
            ResultSet resultSet = JDBC.statement.executeQuery("select * from Bills" +
                    " where courseID = '"+courseID+"' and accountID = '"+accountID+"'");
            if(resultSet.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
