package data;

import controller.*;
import model.Account;
import model.Bill;
import model.Course;
import model.Lesson;
import run.RunMain;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Base {
    public static String nameAcademy = "OWBcode";
    public static String author = "Nguyễn Trung Thành";
    public static String dateEstablish = "01/01/2022";
    public static String address = "Mọi nơi khi bạn kết nối internet";
    public static String hotline = "0987 112 345";
    public static String email = "t.onion.2002@gmail.com";

    public static List<Course> courses = CourseControl.readAllRecord();
    public static List<Account> accounts = AccountControl.readAllRecord();
    public static List<Lesson> lessons = LessonControl.readAllRecord();
    public static Scanner sc = new Scanner(System.in);


    // Hàm trả về giá trị ràng buộc lựa chọn nằm trong đoạn [start, end]
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

    // Hàm trả giá trị nếu thỏa mãn ràng buộc lựa chọn nằm trong đoạn [start, end]
    public static int chooseInIndex(int start, int end) {
        int choose = sc.nextInt();
        sc.nextLine();
        return (choose >= start && choose <= end) ? choose : -1;
    }

    // Hàm trả về một chuỗi thỏa mãn độ dài không vượt quá lenght
    public static String stringRequest(int lenght) {
        String result;
        while(true) {
            result = sc.nextLine();
            if(result.length() > lenght) {
                System.out.println("\tSai rồi! Chuỗi nhập vào tối đa " + lenght + " kí tự");
                System.out.println("\tNhập lại: ");
            } else {
                break;
            }
        }
        return result;
    }

    // Hàm trả về kiểu boolean cho câu hỏi yes / no
    public static boolean ynQuestion() {
        return sc.nextLine().equalsIgnoreCase("y");
    }

    // Hàm để ngắt một đoạn text không xuống dòng thành 1 đoạn văn bản có xuống dòng để tiện theo dõi
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

    // Hàm trang chủ
    public static void home() {
        System.out.println();
        System.out.println("\t\t╔═════════════════╦════════════  TRANG CHỦ  ════════════╗");
        System.out.println("\t\t║     ,___,       ║  0. Thoát khỏi chương trình         ║");
        System.out.println("\t\t║     [O.o]  /    ║  1. Đăng nhập                       ║");
        System.out.println("\t\t║     /)__)>/     ║  2. Đăng ký                         ║");
        System.out.println("\t\t║-----″--″--      ║  3. Xem danh sách các khóa học      ║");
        System.out.println("\t\t╚═════════════════╩═════════════════════════════════════╝");

        int choose = Base.chooseIn(0, 3);
        switch (choose) {
            case 1:
                Base.logIn();
                break;
            case 2:
                Base.register();
                break;
            case 3:
                UserControl.viewCourseInAccount();
                break;
            default:
                System.out.println();
                System.out.println("       ,___,   ");
                System.out.println("       [O.o]    OWBcode chào tạm biệt");
                System.out.println("      <(___)>♥       Hẹn gặp lại!");
                System.out.println("-------\"--\"- ");
                break;
        }
    }

    // Hàm đăng nhập
    public static void logIn() {
        System.out.println("\nTrang chủ > Đăng nhập");
        String userName, password;
        System.out.print("\tNhập tên tài khoản: ");
        userName = sc.nextLine();
        System.out.print("\tNhập mật khẩu: ");
        password = sc.nextLine();
        String id = AccountControl.userNameExist(userName);
        if(id == null) {
            System.out.println("\t\tTên tài khoản không tồn tại ☹");
            home();
        } else {
            Account target = AccountControl.findRecord(id);
            if(!target.getPassword().equals(password)) {
                System.out.println("\t\tSai mật khẩu ☹");
                home();
            } else {
                System.out.println("\t\tĐăng nhập thành công ☺");
                RunMain.accountJoin = target;
                if(target.getAdmin()) {
                    RunMain.adminJoin = target;
                    AdminControl.adminProcess();
                }
                else
                    UserControl.userProcess();
            }
        }
    }

    // Hàm đăng xuất
    public static void logOut() {
        // Nếu tài khoản yêu cầu đăng xuất là admin
        if(RunMain.accountJoin.getAdmin()) {
            RunMain.accountJoin = null;
            RunMain.adminJoin = null;
            System.out.println("\t\tBan quản trị đã đăng xuất ☺");
            home();
        }
        // Nếu tài khoản yêu cầu đăng xuất là người dùng thì có hai khả năng
        else {
            // Là admin nhưng vào tài khoản người dùng để quản lý
            if (RunMain.adminJoin != null) {
                System.out.println("\t\tThoát quyền truy cập tài khoản " + RunMain.accountJoin.getId() + " ☺");
                RunMain.accountJoin = RunMain.adminJoin;
            }
            // Là người dùng
            else {
                RunMain.accountJoin = null;
                System.out.println("\t\tNgười dùng đã đăng xuất ☺");
                home();
            }
        }
    }

    // Hàm đăng ký
    public static void register() {
        System.out.println("\nTrang chủ > Đăng ký");
        String userName = AccountControl.requestUserName();
        String password = AccountControl.requestPassword();
        String fullName;
        System.out.print("\tNhập họ tên[0-25]: ");
        fullName = stringRequest(25);
        if(AccountControl.userNameExist(userName) != null) {
            System.out.println("\t\tTài khoản đã tồn tại ☹");
            home();
        } else {
            String lastId = accounts.get(accounts.size()-1).getId();
            String autoId = "AC" + String.format("%08d", Integer.parseInt(lastId.substring(2, 10))+1);
            Account account = new Account(autoId, userName, password, false, fullName, 0, "");
            AccountControl.insertRecord(account);
            accounts.add(account);
            System.out.println("\t\tĐăng ký hoàn tất ☺");
            System.out.println("\t1. Đi đến đăng nhập");
            System.out.println("\t2. Trở về phần giao diện");
            int choose = chooseIn(1,2);
            if (choose == 1)
                logIn();
            else
                home();
        }
    }

    /* Hàm hiển thị danh sách khóa học với 1 số điều kiện (isLogIn : đăng nhập hay chưa, type kiểu xem danh sách
    type = 1: Tất cả các khóa học; type = 2: Khóa học đã sở hữu; type = 3: Khóa học chưa sở hữu
    Các loại type chỉ áp dụng cho trường hợp đã đăng nhập, nếu chưa mặc định là type = 1
    */
    public static int viewCourseWithList(List<Course> courseList, boolean isLogIn, int type) {
        if(courseList.size() == 0) {
            System.out.println("\t\tBạn đã mua toàn bộ khóa học ☺");
            return 0;
        }
        System.out.println(Constants.courseHR + "\n" + Constants.courseTitle + "\n" + Constants.courseHR);
        for (int i = 0; i < courseList.size(); i++) {
            System.out.printf("| %3d |  %-5s  |  %-30s  |  %-8s  |  %7.2f  |\n",
                    i+1, courseList.get(i).getId(), courseList.get(i).getName(),
                    RateControl.rateAVG(courseList.get(i).getId()), courseList.get(i).getPrice());
        }
        System.out.println(Constants.courseHR);
        if(!isLogIn) {
            System.out.println("\t\tBạn chưa đăng nhập ☹");
            System.out.println("\t1. Đi đến đăng nhập");
            System.out.println("\t2. Trở về phần trang chủ");
            int choose = chooseIn(1,2);
            if (choose == 1)
                logIn();
            else
                home();
            return 0;
        }
        else {
            System.out.print("Nhập Enter để tiếp tục: ");
            Base.sc.nextLine();
            System.out.println("\t╔════════════╗");
            System.out.println("\t║ 0. Trở lại ╚════════╗     MENU");
            System.out.println("\t║ 1. Vào xem chi tiết ╚═════════════════╗");
            System.out.println("\t║ 2. Sắp xếp tăng dần theo tên khóa học ║");
            System.out.println("\t║ 3. Sắp xếp tăng dần theo vote ╔═══════╝");
            System.out.println("\t║ 4. Sắp xếp tăng dần theo giá  ╚═══════╗");
            System.out.println("\t║ 5. Sắp xếp giảm dần theo tên khóa học ║");
            System.out.println("\t║ 6. Sắp xếp giảm dần theo vote ╔═══════╝");
            System.out.println("\t║ 7. Sắp xếp giảm dần theo giá  ║");


            if(type != 2) {
                System.out.println("\t║ 8. Mua một khóa học ở trên    ║");
            }
            System.out.println("\t╚═══════════════════════════════╝");
            int saveChoose = (type != 2) ? chooseIn(0, 8) : chooseIn(0, 7);
            if(saveChoose != 0) {
                if (type == 1)
                    System.out.print("\nTrang chủ > ... toàn bộ khóa học");
                if (type == 2)
                    System.out.print("\nTrang chủ > ... khóa học của bạn");
                if (type == 3)
                    System.out.print("\nTrang chủ > ... khóa học chưa mua");
            }
            if (saveChoose == 1) {
                System.out.println(" > Xem chi tiết");
                System.out.print("\tBạn muốn xem khóa học số? ");
                int courseIndexJoin = Base.chooseInIndex(1, courses.size() + 1);
                if (courseIndexJoin == -1) {
                    System.out.println("\t\tRất tiếc! Không có khóa học này ☹");
                    System.out.print("Nhấn Enter để quay trở lại");
                    Base.sc.nextLine();
                } else {
                    RunMain.courseJoin = courseList.get(courseIndexJoin-1);
                    courseList.get(courseIndexJoin-1).join();
                    RunMain.courseJoin = null;
                }
                return 0;
            }
            if (saveChoose == 2) {
                System.out.println(" > Tên khóa tăng dần");
                courseList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
                viewCourseWithList(courseList, true, type);
                return 0;
            }
            if (saveChoose == 3) {
                System.out.println(" > Vote tăng dần");
                courseList.sort((o1, o2) -> RateControl.rateAVG(o1.getId()).compareTo(RateControl.rateAVG(o2.getId())));
                viewCourseWithList(courseList, true, type);
                return 0;
            }
            if (saveChoose == 4) {
                System.out.println(" > Giá tăng dần");
                courseList.sort((o1, o2) -> (o1.getPrice()>o2.getPrice() ? 1 : -1));
                viewCourseWithList(courseList, true, type);
                return 0;
            }
            if (saveChoose == 5) {
                System.out.println(" > Tên khóa giảm dần");
                courseList.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
                viewCourseWithList(courseList, true, type);
                return 0;
            }
            if (saveChoose == 6) {
                System.out.println(" > Vote giảm dần");
                courseList.sort((o1, o2) -> RateControl.rateAVG(o2.getId()).compareTo(RateControl.rateAVG(o1.getId())));
                viewCourseWithList(courseList, true, type);
                return 0;
            }
            if (saveChoose == 7) {
                System.out.println(" > Giá giảm dần");
                courseList.sort((o1, o2) -> (o2.getPrice()>o1.getPrice() ? 1 : -1));
                viewCourseWithList(courseList, true, type);
                return 0;
            }
            if (saveChoose == 8) {
                System.out.println(" > Mua khóa học");
                System.out.print("\t\tBạn muốn mua khóa học số? ");
                int courseWantBuy = Base.chooseInIndex(1, courses.size() + 1);
                if (courseWantBuy == -1) {
                    System.out.println("\t\tRất tiếc! Không có khóa học này ☹");
                    System.out.print("Nhấn Enter để quay trở lại");
                    Base.sc.nextLine();
                } else {
                    RunMain.courseJoin = courseList.get(courseWantBuy-1);
                    System.out.println("\t\tKhóa học: " + RunMain.courseJoin.getName() + "(" + RunMain.courseJoin.getId() +")");
                    System.out.printf("Số dư của bạn là: %.2f\n", RunMain.accountJoin.getBalance());
                    System.out.print("Bạn có muốn mua khóa học này với giá " +
                            RunMain.courseJoin.getPrice() + "$ hay không[y/n]: ");
                    if(Base.ynQuestion())
                        Base.buyCourse();
                    if(type == 3) {
                        courseList = CourseControl.readByCondition(RunMain.accountJoin.getId(), false);
                        System.out.println("\nTrang chủ > Người dùng > ... > Xem khoá học chưa mua");
                    } else  {
                        System.out.println("\nTrang chủ > Người dùng > ... > Xem toàn bộ các khóa học");
                    }
                    RunMain.courseJoin = null;
                    viewCourseWithList(courseList, true, type);
                    return 0;
                }
            }
        }
        return 0;
    }

    // Hàm mua khóa RunMain.courseJoin nếu người dùng RunMain.accountJoin chưa mua
    public static void buyCourse() {
        if (isBought()) {
            System.out.println("\t\tBạn đã mua khóa học này rồi!");
        } else {
            if(RunMain.courseJoin.getPrice() > RunMain.accountJoin.getBalance()) {
                System.out.println("\tSố dư của bạn không đủ để thanh toán ☹");
                System.out.print("Đi đến nạp tiền [y/n]? ");
                if(Base.ynQuestion())
                    UserControl.rechargeIntoAccount();
            } else {
                double beforeBalane = RunMain.accountJoin.getBalance();
                double balance = beforeBalane - RunMain.courseJoin.getPrice();
                double turnOver = AccountControl.findRecord("AC00000001").getBalance()
                        + RunMain.courseJoin.getPrice();
                Account admin = AccountControl.findRecord("AC00000001");
                admin.setBalance(turnOver);
                RunMain.accountJoin.setBalance(balance);
                AccountControl.updateRecord(RunMain.accountJoin);
                AccountControl.updateRecord(admin);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Bill bill = new Bill(RunMain.courseJoin.getId(), RunMain.accountJoin.getId(),
                        formatter.format(new Date()));
                BillControl.insertRecord(bill);
                System.out.println("\t\tĐã mua thành công ☺");
                System.out.printf("\tSố dư hiện tại là: " + beforeBalane + " - " + RunMain.courseJoin.getPrice()
                        + " = %.2f$\n", balance);
            }
        }
    }

    // Check Xem người dùng RunMain.accountJoin đã mua khóa RunMain.courseJoin hay chưa
    public static Boolean isBought()  {
        return  (BillControl.findRecord(RunMain.courseJoin.getId(), RunMain.accountJoin.getId()) != null);
    }
}
