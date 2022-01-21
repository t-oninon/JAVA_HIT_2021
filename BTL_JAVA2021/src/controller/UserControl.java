package controller;

import data.Base;
import data.Constants;
import model.Account;
import model.Bill;
import model.Course;
import run.RunMain;
import java.util.List;

public class UserControl {
    public static boolean passwordViewable = false;

    // Hàm xử lý đối với tài khoản đăng nhập là người dùng
    public static void userProcess() {
        System.out.println("\nTrang chủ > Người dùng");
        System.out.println("\t\t ╔════  USER  ═════╦═════════════    USER    ══════════╗");
        System.out.println("\t\t║                  ║   0. Đăng xuất                     ║");
        System.out.println("\t\t║     ,___,        ║   1. Xem thông báo                 ║");
        System.out.println("\t\t║     [O.o]  ?     ║   2. Xem thông tin tài khoản       ║");
        System.out.println("\t\t║     /)__)>/      ║   3. Chỉnh sửa thông tin           ║");
        System.out.println("\t\t║------″--″--      ║   4. Xem danh sách khóa học        ║");
        System.out.println("\t\t║                  ║   5. Xem lịch sử mua các khóa học  ║");
        int choose;
        if(RunMain.adminJoin == null) {
            System.out.println("\t\t║------=-          ║   6. Nạp tiền vào tài khoản        ║");
            System.out.println("\t\t ╚════  USER  ═════╩═════════════    USER    ══════════╝");
            choose = Base.chooseIn(0, 6);
        }
        else {
            System.out.println("\t\t ╚════  USER  ═════╩═════════════    USER    ══════════╝");
            choose = Base.chooseIn(0, 5);
        }
        switch (choose) {
            case 1:
                UserControl.viewNotify();
                break;
            case 2:
                UserControl.viewAccountInfor(false);
                break;
            case 3:
                UserControl.updateAccountInfor();
                break;
            case 4:
                UserControl.viewCourseInAccount();
                break;
            case 5:
                UserControl.viewBillInAccount();
                break;
            case 6:
                UserControl.rechargeIntoAccount();
                break;
        }
        if (choose == 0)
            Base.logOut();
        else
            userProcess();
    }

    // Hàm xem thông tin người dùng dưới dạng môt bảng và ẩn đi mật khẩu nếu passwordViewable = true
    public static void viewAccountInfor(boolean passwordViewable) {
        Account account = RunMain.accountJoin;
        String password = (passwordViewable || RunMain.adminJoin != null) ? account.getPassword()
                : account.getPassword().replaceAll("[A-Za-z0-9@$!%*?&_]", "*");
        System.out.println("\nTrang chủ > Người dùng > Xem thông tin tài khoản");
        System.out.println(Constants.accountHR);
        System.out.println(Constants.accountTitle);
        System.out.println(Constants.accountHR);
        System.out.printf("|  %-10s  |  %-15s  |  %-15s  |  %-5s  |  %-25s  |  %10.2f  |%n",
                account.getId(), account.getUserName(),
                password,
                account.getAdmin(), account.getFullName(), account.getBalance());
        System.out.println(Constants.accountHR);
        passwordViewable = false;
        System.out.print("Nhấn Enter để trở về: ");
        Base.sc.nextLine();
    }

    // Hàm xem thông báo của người dùng, sau mỗi lần xem thì thông báo sẽ bị xóa
    public static void viewNotify() {
        Account account = RunMain.accountJoin;
        System.out.println("\nTrang chủ > Người dùng > Xem thông báo");
        if(account.getNotify().length() == 0) {
            System.out.println("\tHiện tại bạn chưa có thông báo nào!");
        } else {
            System.out.println(account.getNotify());
            if(RunMain.adminJoin == null)
                account.setNotify("");
        }
        System.out.print("Nhấn Enter để trở về: ");
        Base.sc.nextLine();
        AccountControl.updateRecord(account);
    }

    // Hàm sửa đổi một số thông tin của tài khoản người dùng
    public static void updateAccountInfor() {
        boolean isAdmin = (RunMain.adminJoin != null);
        Account account = RunMain.accountJoin;
        int choose = 1;
        while (choose != 0) {
            if(RunMain.accountJoin.getAdmin()) {
                System.out.println("\nTrang chủ > Quản trị > Cập nhật thông tin");
            } else {
                System.out.println("\nTrang chủ > Người dùng > Cập nhật thông tin");
            }
            System.out.println("╔══════════════════╗");
            System.out.println("║     ,___,        ║  0. Thoát");
            System.out.println("║     [O.o]        ║  1. Tên tài khoản");
            System.out.println("║   (Cập nhật)     ║  2. Mật khẩu");
            System.out.println("║-----″--″--       ║  3. Họ tên");

            if(isAdmin && !RunMain.accountJoin.getAdmin()) {
                System.out.println("╠══════════════════╣  4. Cấp tiền cho tài khoản");
                System.out.println("╠══════════════════╣  5. Xóa toàn bộ thông báo");
                System.out.println("╚══════════════════╝");
                choose = Base.chooseIn(0, 5);
            } else {
                System.out.println("╚══════════════════╝");
                choose = Base.chooseIn(0, 3);
            }
            switch (choose) {
                case 1:
                    System.out.println("\nTrang chủ > .. > Cập nhật tên tài khoản");
                    System.out.println("Tên người dùng hiện tại: " + account.getUserName());
                    System.out.println("\t\tHoàn thành các bước để cập nhật tên tài khoản");
                    String userName = AccountControl.requestUserName();
                    if (AccountControl.userNameExist(userName) == null) {
                        account.setUserName(userName);
                        System.out.println("\t\tCập nhật thành công ☺");
                    } else {
                        System.out.println("\t\tTên đăng nhập đã tồn tại ☹");
                    }
                    break;
                case 2:
                    System.out.println("\nTrang chủ > .. > Cập nhật mật khẩu");
                    System.out.print("Nhập mật khẩu hiện tại: " );
                    String password = Base.sc.nextLine();
                    if (account.getPassword().equals(password)) {
                        System.out.println("\t\tHoàn thành các bước để cập nhật mật khẩu");
                        password = AccountControl.requestPassword();
                        account.setPassword(password);
                        System.out.println("\t\tCập nhật thành công ☺");
                        passwordViewable = true;
                    }
                    else {
                        System.out.println("\t\tNhập sai mật khẩu ☹");
                    }
                    break;
                case 3:
                    System.out.println("\nTrang chủ > .. > Cập nhật họ tên");
                    System.out.println("Họ tên hiện tại: " + account.getFullName());
                    System.out.println("\t\tHoàn thành các bước để cập nhật họ tên");
                    String fullName;
                    System.out.print("\tNhập họ tên[0-25]: ");
                    fullName = Base.stringRequest(25);
                    account.setFullName(fullName);
                    System.out.println("\t\tCập nhật thành công ☺");
                    break;
                case 4:
                    System.out.println("\nTrang chủ > .. > Cấp tiền cho tài khoản");
                    System.out.print("Nhập số tiền cộng thêm: ");
                    int money = Base.sc.nextInt();
                    Base.sc.nextLine();
                    System.out.println("Số dư tài khoản sau đó là: " + account.getBalance() + " + " + money + " = " +
                            (account.getBalance() + money));
                    System.out.print("Bạn vẫn tiếp tục? [y/n]: ");
                    if(Base.ynQuestion()) {
                        account.setBalance(account.getBalance() + money);
                        System.out.println("\t\tCập nhật thành công ☺");
                    }
                    break;
                case 5:
                    System.out.println("\nTrang chủ > .. > Xóa toàn bộ thông báo");
                    if(account.getNotify().length() == 0) {
                        System.out.println("\tHiện tại bạn chưa có thông báo nào!");
                    } else {
                        account.setNotify("");
                    }
                    System.out.println("\t\tCập nhật thành công ☺");
                    break;
            }
            if (choose != 0) {
                AccountControl.updateRecord(account);
                Base.accounts = AccountControl.readAllRecord();
                System.out.println("Nhấn: 0. Thoát");
                System.out.println("      1. Tiếp tục cập nhật");
                System.out.println("      2. Xem thông tin sau khi cập nhật");
                choose = Base.chooseIn(0, 2);
                if(choose == 2) {
                    viewAccountInfor(passwordViewable);
                    choose = 0;
                }
                if(choose == 0)
                    choose = 0;
            }
        }
    }

    // Hàm xem các khóa học
    public static int viewCourseInAccount() {
        boolean isLogIn = RunMain.accountJoin != null;
        List<Course> courseList = Base.courses;
        int choose = 1;
        if (isLogIn) {
            System.out.println("\nTrang chủ > Người dùng > Danh sách khóa học");
            System.out.println("\t╔═════════════╗");
            System.out.println("\t║   ,___,     ║  1. Xem toàn bộ các khóa học");
            System.out.println("\t║   [O.o]  /  ║  2. Xem khóa học của bạn");
            System.out.println("\t║   /)__)>/   ║  3. Xem khóa học chưa mua");
            System.out.println("\t╚═--″--″---═══╝");
            choose = Base.chooseIn(1,3);
            if(choose == 2) {
                System.out.println("\nTrang chủ > Người dùng > ... > Xem khoá học của bạn");
                courseList = CourseControl.readByCondition(RunMain.accountJoin.getId(), true);
                if(courseList.size() == 0) {
                    System.out.println("\t\tRất tiếc! Bạn chưa mua khóa học nào ☹");
                    return 0;
                }
            } else if (choose == 3) {
                System.out.println("\nTrang chủ > Người dùng > ... > Xem khoá học chưa mua");
                courseList = CourseControl.readByCondition(RunMain.accountJoin.getId(), false);
                if(courseList.size() == 0) {
                    System.out.println("\t\tBạn đã mua toàn bộ khóa học ☺");
                    return 0;
                }
            }
            else {
                System.out.println("\nTrang chủ > Người dùng > ... > Xem toàn bộ các khóa học");
            }
        } else
            System.out.println("\nGiao diện > Xem danh sách khóa học");

        Base.viewCourseWithList(courseList, isLogIn, choose);
        return 0;
    }

    // Hàm xem lịch sử giao dịch
    public static void viewBillInAccount() {
        System.out.println("\nTrang chủ > Người dùng > Xem lịch sử mua các khóa học");
        List<Bill> bills = BillControl.readAllByAccountID(RunMain.accountJoin.getId());
        System.out.println(Constants.billHR + "\n" + Constants.billTitle + "\n" + Constants.billHR);
        for (int i = 0; i < bills.size(); i++) {
            System.out.printf("| %3s ", (i + 1));
            System.out.println(bills.get(i).show());
        }
        System.out.println(Constants.billHR);
        System.out.print("Nhấn Enter để tiếp tục");
        Base.sc.nextLine();
        System.out.println("Nhấn   0. Thoát");
        System.out.println("       1. Xóa bill");
        int choose = Base.chooseIn(0, 1);
        if (choose == 1) {
            System.out.println("\nTrang chủ > ...lịch sử mua các khóa học > Xóa bill");
            System.out.print("\tBạn muốn xóa bill số? ");
            int billIndex = Base.chooseInIndex(1, bills.size()+1);
            if(billIndex < 0) {
                System.out.println("\t\tRất tiếc! Không có bill nào như vậy ☹");
            }
            else {
                Bill billDelete = bills.get(billIndex-1);
                System.out.println("\tNếu xác nhận xóa thì bạn sẽ không học được khóa \"" +
                        billDelete.getCourseId() + "\" và không được hoàn tiền ☹");
                System.out.print("Bạn thực sự muốn xóa nó? [y/n]: ");
                if(Base.ynQuestion()) {
                    BillControl.deleteRecord(billDelete.getCourseId(), billDelete.getAccountId());
                    System.out.println("\t\tBill đã được xóa thành công ☺");
                }
            }
        }
        System.out.print("Nhấn Enter để trở về");
        Base.sc.nextLine();
    }

    // Hàm nạp tiền vào tài khoản
    public static void rechargeIntoAccount() {
        Account account = RunMain.accountJoin;
        System.out.println("\nTrang chủ > Người dùng > Nạp tiền");
        System.out.println("Các loại mệnh giá được chấp nhận");
        int choose = 1;
        String cardCode = "";
        System.out.println("\t╔═════════════╗ ");
        System.out.println("\t║   ,___,     ║  1.  10$ [010*******]");
        System.out.println("\t║   [O.o]     ║  2.  20$ [020*******]");
        System.out.println("\t║   /)__)>$   ║  3.  50$ [050*******]");
        System.out.println("\t║---″--″----- ║  4. 100$ [100*******]\n");
        System.out.println("\t╚═════════════╝");

        System.out.print("Nhập mã thẻ: ");
        cardCode = Base.sc.nextLine();
        if (Constants.card.matcher(cardCode).find()) {
            int value = Integer.parseInt(cardCode.substring(0,3));
            System.out.println("\tĐã nạp thành công " + value + "$ vào tài khoản ☺");
            account.setBalance(account.getBalance()+value);
            AccountControl.updateRecord(account);
            System.out.print("Bạn có muốn xem số dư hay không? [y/n]: ");
            if(Base.ynQuestion())
                viewAccountInfor(passwordViewable);
        }
        else {
            System.out.println("\tNạp tiền thất bại do mã thẻ không đúng ☹");
        }
        System.out.print("Bạn có muốn tiếp tục nạp hay không? [y/n]: ");
        if(Base.ynQuestion())
            rechargeIntoAccount();
    }
}
