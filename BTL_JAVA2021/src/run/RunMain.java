package run;


import controller.*;
import data.Base;
import model.*;

import java.sql.*;
import java.util.List;


public class RunMain {
    public static Account accountJoin = Base.accounts.get(4);
    public static Course courseJoin = Base.courses.get(0);
    public static Lesson lessonJoin = null;
    public static void main(String[] args) throws SQLException {
        home();
        JDBC.conn.close();
    }


    public static void home() {
        System.out.println();
        System.out.println("    ,___,          GIAO DIỆN");
        System.out.println("    [O.o]  /  1. Đăng nhập");
        System.out.println("    /)__)>/   2. Đăng ký");
        System.out.println("----\"--\"-     3. Xem danh sách các khóa học");
        System.out.println("----=----     4. Thoát khỏi chương trình");
        int choose = Base.chooseIn(1, 4);
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

    public static void userProcess (Account account) {
        System.out.println("\nTrang chủ > Người dùng");
        System.out.println("    ,___,     |  1. Xem thông tin");
        System.out.println("    [O.o]  ?  |  2. Chỉnh sửa thông tin");
        System.out.println("    /)__)>/   |  3. Xem danh sách khóa học");
        System.out.println("----\"--\"-     |  4. Nạp tiền vào tài khoản");
        System.out.println("-------=-     |  5. Đăng xuất");
        int choose = Base.chooseIn(1, 5);
        switch (choose) {
            case 1:
                UserControl.viewAccountInfor(account, false);
                break;
            case 2:
                UserControl.updateAccountInfor(account);
                break;
            case 3:
                UserControl.viewCourseInAccount();
                break;
            case 4:
                UserControl.rechargeIntoAccount(account);
                break;
        }
        AccountControl.updateRecord(account);
        if (choose == 5)
            Base.logOut();
        else
            userProcess(account);
    }

    public static void adminProcess (Account account) {
        System.out.println("\nTrang chủ > Quản trị");
        System.out.println("________________   /!\\  1. Xem thông tin tài khoản");
        System.out.println("|    ,___,      |  /!\\  2. Xem thông tin khóa học");
        System.out.println("|    [O.o]  ?   |  /!\\  3. Xem thông tin bài học");
        System.out.println("|    /)__)>/    |  /!\\  4. Xem thông tin vote");
        System.out.println("|----\"--\"-      |  /!\\  5. Xem thông tin giao dịch");
        System.out.println("________________|  /!\\  6. Đăng xuất");
        int choose = Base.chooseIn(1, 6);
        switch (choose) {
            case 1:
                List<Account> accounts = AccountControl.readAllRecord();
                AdminControl.viewInforAccount(Base.accounts);
                break;
            case 2:
                List<Course> courses = CourseControl.readAllRecord();
                AdminControl.viewInforCourse(Base.courses);
                break;
            case 3:
                List<Lesson> lessons = LessonControl.readAllRecord();
                AdminControl.viewInforLesson(lessons);
                break;
            case 4:
                List<Rate> rates = RateControl.readAllRecord();
                AdminControl.viewInforRate(rates);
                break;
            case 5:
                List<Bill> bills = BillControl.readAllRecord();
                AdminControl.viewInforBill(bills);
                break;
        }
        if (choose == 6)
            Base.logOut();
        else
            adminProcess(account);
    }

}
