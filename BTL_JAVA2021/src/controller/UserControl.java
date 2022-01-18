package controller;

import data.Base;
import data.Constants;
import model.Account;
import model.Course;
import run.RunMain;

import java.util.List;

public class UserControl {
    public static boolean passwordViewable = false;

    public static void viewAccountInfor(Account account, boolean passwordViewable) {
        String password = (passwordViewable) ? account.getPassword()
                : account.getPassword().replaceAll("[A-Za-z0-9@$!%*?&_]", "*");
        System.out.println("\nTrang chủ > Người dùng > Xem thông tin tài khoản");
        System.out.println(Constants.accountHR);
        System.out.println(Constants.accountTitle);
        System.out.printf("|  %-10s  |  %-15s  |  %-15s  |  %-5s  |  %-25s  |  %10.2f  |%n",
                account.getId(), account.getUserName(),
                password,
                account.getAdmin(), account.getFullName(), account.getBalance());
        System.out.println(Constants.accountHR);
        passwordViewable = false;
        System.out.print("Nhập Enter để trở về: ");
        Base.sc.nextLine();
    }

    public static void viewNotify(Account account) {
        System.out.println("\nTrang chủ > Người dùng > Xem thông báo");
        if(account.getNotify().length() == 0) {
            System.out.println("\tHiện tại bạn chưa có thông báo nào!");
        } else {
            System.out.println(account.getNotify());
            account.setNotify("");
        }
        System.out.print("Nhập Enter để trở về: ");
        Base.sc.nextLine();
        AccountControl.updateRecord(account);
    }

    public static void updateAccountInfor(Account account) {
        int choose = 1;
        while (choose != 0) {
            System.out.println("\nTrang chủ > Người dùng > Cập nhật thông tin");
            System.out.println("    ,___,       0. Thoát");
            System.out.println("    [O.o]       1. Tên người dùng");
            System.out.println("  (Cập nhật)    2. Mật khẩu");
            System.out.println("----\"--\"-       3. Họ tên");
            choose = Base.chooseIn(0, 3);
            switch (choose) {
                case 1:
                    System.out.println("\nTrang chủ > .. > Cập nhật tên người dùng");
                    System.out.println("Tên người dùng hiện tại: " + account.getUserName());
                    System.out.println("\t\tHoàn thành các bước để cập nhật tên người dùng");
                    String userName = AccountControl.requestUserName();
                    if (AccountControl.userNameExist(userName) == null) {
                        account.setUserName(userName);
                        System.out.println("\t\tCập nhật thành công ☺");
                    } else {
                        System.out.println("\t\tTên người dùng đã tồn tại ☹");
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
            }
            if (choose != 0) {
                AccountControl.updateRecord(account);
                Base.accounts = AccountControl.readAllRecord();
                System.out.println("Nhấn: 1. Tiếp tục cập nhật");
                System.out.println("      2. Xem thông tin sau khi cập nhật");
                System.out.println("      3. Thoát");
                choose = Base.chooseIn(0, 3);
                if(choose == 2) {
                    viewAccountInfor(account, passwordViewable);
                    choose = 0;
                }
                if(choose == 3)
                    choose = 0;
            }
        }
    }

    public static int viewCourseInAccount() {
        boolean isLogIn = RunMain.accountJoin != null;
        List<Course> courseList = Base.courses;
        int choose = 1;
        if (isLogIn) {
            System.out.println("\nTrang chủ > Người dùng > Danh sách khóa học");
            System.out.println("    ,___,     1. Xem toàn bộ các khóa học");
            System.out.println("    [O.o]  /  2. Xem khóa học của bạn");
            System.out.println("    /)__)>/   3. Xem khóa học chưa mua");
            System.out.println("----\"--\"-   ");

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

    public static void rechargeIntoAccount(Account account) {
        System.out.println("\nTrang chủ > Người dùng > Nạp tiền");
        System.out.println("Các loại mệnh giá được chấp nhận");
        int choose = 1;
        String cardCode = "";
        System.out.println("           ,___,");
        System.out.println("           [O.o]     1.  10$ [010*******]");
        System.out.println("   Nạp tiền<(__)>$   2.  20$ [020*******]");
        System.out.println("------ ----\"--\"-     3.  50$ [050*******]");
        System.out.println("-----------=--       5. 100$ [100*******]\n");
        System.out.print("Nhập mã thẻ: ");
        cardCode = Base.sc.nextLine();
        if (Constants.card.matcher(cardCode).find()) {
            int value = Integer.parseInt(cardCode.substring(0,3));
            System.out.println("\tĐã nạp thành công " + value + "$ vào tài khoản ☺");
            account.setBalance(account.getBalance()+value);
            AccountControl.updateRecord(account);
            System.out.print("Bạn có muốn xem số dư hay không? [y/n]: ");
            if(Base.ynQuestion())
                viewAccountInfor(account, passwordViewable);
        }
        else {
            System.out.println("\tNạp tiền thất bại do mã thẻ không đúng ☹");
        }
        System.out.print("Bạn có muốn tiếp tục nạp hay không? [y/n]: ");
        if(Base.ynQuestion())
            rechargeIntoAccount(account);
    }
}
