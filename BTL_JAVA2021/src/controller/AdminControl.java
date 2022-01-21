package controller;

import data.Base;
import data.Constants;
import model.*;
import run.RunMain;
import java.util.List;
import java.util.stream.Collectors;

public class AdminControl {
    // Hàm xử lý đối với tài khoản đăng nhập là quản trị
    public static void adminProcess () {
        System.out.println("\nTrang chủ > Quản trị");
        System.out.println("\t\t ╔════  ADMIN  ═════╦════════════    ADMIN    ═══════════╗");
        System.out.println("\t\t║                   ║  ♕  0. Đăng xuất                   ║");
        System.out.println("\t\t║                   ║  ♕  1. Chỉnh sửa thông tin         ║");
        System.out.println("\t\t║      ,___,        ║  ♕  2. Xem danh sách tài khoản     ║");
        System.out.println("\t\t║      [O.o]        ║  ♕  3. Xem danh sách khóa học      ║");
        System.out.println("\t\t║      /)__)>@      ║  ♕  4. Xem danh sách bài học       ║");
        System.out.println("\t\t║-------″--″---     ║  ♕  5. Xem danh sách đánh giá      ║");
        System.out.println("\t\t║                   ║  ♕  6. Xem danh sách giao dịch     ║");
        System.out.println("\t\t ╚════  ADMIN  ═════╩════════════    ADMIN    ═══════════╝");
        int choose = Base.chooseIn(0, 6);
        switch (choose) {
            case 1:
                UserControl.updateAccountInfor();
                break;
            case 2:
                System.out.println("\nTrang chủ > Quản trị > Xem danh sách tài khoản");
                AdminControl.viewInforAccount(AccountControl.readAllRecord());
                break;
            case 3:
                System.out.println("\nTrang chủ > Quản trị > Xem danh sách khóa học");
                AdminControl.viewInforCourse(CourseControl.readAllRecord());
                break;
            case 4:
                System.out.println("\nTrang chủ > Quản trị > Xem danh sách bài học");
                AdminControl.viewInforLesson(LessonControl.readAllRecord());
                break;
            case 5:
                System.out.println("\nTrang chủ > Quản trị > Xem danh sách đánh giá");
                AdminControl.viewInforRate(RateControl.readAllRecord());
                break;
            case 6:
                System.out.println("\nTrang chủ > Quản trị > Xem danh sách giao dịch");
                AdminControl.viewInforBill(BillControl.readAllRecord());
                break;
        }
        if (choose == 0)
            Base.logOut();
        else
            adminProcess();
    }

    // Hàm hiển thị danh sách các tài khoản accounts dưới dạng bảng
    public static void viewAccountByAdmin(List<Account> accounts) {
        System.out.println(Constants.accountHRAd + "\n" + Constants.accountTitleAd + "\n" + Constants.accountHRAd);
        for (int i = 0; i < accounts.size(); i++) {
            System.out.printf("| %3d ", i+1);
            System.out.println(accounts.get(i));
        }
        System.out.println(Constants.accountHRAd);
    }

    // Hàm xem thông tin các tài khoản accounts và các lựa chọn xử lý dữ liệu
    public static int viewInforAccount(List<Account> accounts) {
        viewAccountByAdmin(accounts);
        System.out.print("Nhắn Enter để tiếp tục: ");
        Base.sc.nextLine();
        System.out.println("\t╔══════════════╗");
        System.out.println("\t║  0. Quay lại ╚═════╗      MENU");
        System.out.println("\t║  1. Thêm tài khoản ╚══════════════════╗");
        System.out.println("\t║  2. Truy cập vào tài khoản người dùng ╚══╗");
        System.out.println("\t║  3. Sắp xếp tăng dần theo tên người dùng ║");
        System.out.println("\t║  4. Sắp xếp tăng dần theo họ tên  ╔══════╝");
        System.out.println("\t║  5. Sắp xếp tăng dần theo số dư   ╚══════╗");
        System.out.println("\t║  6. Sắp xếp giảm dần theo tên người dùng ║");
        System.out.println("\t║  7. Sắp xếp giảm dần theo họ tên ╔═══════╝");
        System.out.println("\t║  8. Sắp xếp giảm dần theo số dư  ║");
        System.out.println("\t╚══════════════════════════════════╝");

        int chooseThis = Base.chooseIn(0,8);
        if (chooseThis == 0)
            return 0;
        System.out.print("\nTrang chủ > Quản trị > .... tài khoản");
        switch (chooseThis) {
            case 1:
                System.out.println(" > Thêm tài khoản");
                addAccount(accounts);
                break;
            case 2:
                System.out.println(" > Truy cập vào tài khoản người dùng");
                System.out.print("\tBạn muốn truy cập vào tài khoản người dùng số?: ");
                int accountIndexJoin = Base.chooseInIndex(1, accounts.size() + 1);
                if (accountIndexJoin == -1) {
                    System.out.println("\t\tRất tiếc! Không có tài khoản này ☹");
                    System.out.print("Nhấn Enter để quay lại");
                    Base.sc.nextLine();
                }
                else {
                    Account accountWantAccess = accounts.get(accountIndexJoin - 1);
                    if (accountWantAccess.getAdmin()) {
                        System.out.println("\t\tĐây là tài khoản của ban quản trị ☹");
                        System.out.print("Nhấn Enter để quay lại");
                        Base.sc.nextLine();
                    } else {
                        System.out.println("\tTài khoản có mã " + accountWantAccess.getId());
                        RunMain.accountJoin = accountWantAccess;
                        System.out.println("\t\tTruy cập và lấy dữ liệu thành công ☺");
                        System.out.print("Nhấn Enter để tiếp tục");
                        Base.sc.nextLine();
                        UserControl.userProcess();
                    }
                }
                break;
            case 3:
                System.out.println(" > Tên người dùng tăng dần");
                accounts.sort((o1, o2) -> o1.getUserName().compareTo(o2.getUserName()));
                viewInforAccount(accounts);
                break;
            case 4:
                System.out.println(" > Họ tên tăng dần");
                accounts.sort((o1, o2) -> o1.getFullName().compareTo(o2.getFullName()));
                viewInforAccount(accounts);
                break;
            case 5:
                System.out.println(" > Số dư tăng dần");
                accounts.sort((o1, o2) -> (o1.getBalance() > o2.getBalance() ? 1 : -1));
                viewInforAccount(accounts);
                break;
            case 6:
                System.out.println(" > Tên người dùng giảm dần");
                accounts.sort((o1, o2) -> o2.getUserName().compareTo(o1.getUserName()));
                viewInforAccount(accounts);
                break;
            case 7:
                System.out.println(" > Họ tên giảm dần");
                accounts.sort((o1, o2) -> o2.getFullName().compareTo(o1.getFullName()));
                viewInforAccount(accounts);
                break;
            case 8:
                System.out.println(" > Số dư giảm dần");
                accounts.sort((o1, o2) -> (o2.getBalance() > o1.getBalance() ? 1 : -1));
                viewInforAccount(accounts);
                break;
        }
        return 0;
    }

    // Hàm thêm một tài khoản mới
    public static void addAccount(List<Account> accounts) {
        String userName = AccountControl.requestUserName();
        String password = AccountControl.requestPassword();
        String fullName;
        System.out.print("\tNhập họ tên[0-25]: ");
        fullName = Base.stringRequest(25);
        if(AccountControl.userNameExist(userName) != null) {
            System.out.println("\t\tTài khoản đã tồn tại ☹");
        } else {
            String lastId = Base.accounts.get(Base.accounts.size()-1).getId();
            String autoId = "AC" + String.format("%08d", Integer.parseInt(lastId.substring(2, 10))+1);
            Account account = new Account(autoId, userName, password, false, fullName, 0, "");
            AccountControl.insertRecord(account);
            accounts.add(account);
            Base.accounts = AccountControl.readAllRecord();
            System.out.println("\t\tThêm tài khoản thành công ☺");
            System.out.println("Nhấn  0. Quay lại");
            System.out.println("      1. Truy nhập vào tài khoản này");
            System.out.println("      2. Xem tài khoản vừa thêm");
            int choose = Base.chooseIn(0,2);
            if (choose == 1) {
                System.out.println("\t\tTruy cập và lấy dữ liệu thành công ☺");
                System.out.print("Nhấn Enter để tiếp tục");
                Base.sc.nextLine();
                RunMain.accountJoin = account;
                UserControl.userProcess();
            }
            if (choose == 2) {
                System.out.println(Constants.accountHRAd + "\n" + Constants.accountTitleAd + "\n" + Constants.accountHRAd);
                System.out.printf("| %3d ", accounts.size());
                System.out.println(account);
                System.out.println(Constants.accountHRAd);
                System.out.print("Nhấn Enter để tiếp tục: ");
                Base.sc.nextLine();
             }
        }
    }

    // Hàm hiển thị danh sách các khóa học courses đưới dạng bảng
    public static void viewCoursesByAdmin(List<Course> courses) {
        System.out.println(Constants.courseHRAd + "\n" + Constants.courseTitleAd + "\n" + Constants.courseHRAd);
        for (int i = 0; i < courses.size(); i++) {
            System.out.printf("| %3d ", i+1);
            System.out.println(courses.get(i));
        }
        System.out.println(Constants.courseHRAd);
    }

    // Hàm xem thông tin các khóa học courses và các lựa chọn xử lý dữ liệu
    public static int viewInforCourse(List<Course> courses) {
        viewCoursesByAdmin(courses);
        System.out.print("Nhắn Enter để tiếp tục: ");
        Base.sc.nextLine();
        System.out.println("\t╔═════════════╗");
        System.out.println("\t║ 0. Quay lại ╚════╗   MENU");
        System.out.println("\t║ 1. Thêm khóa học ╚═══════════╗");
        System.out.println("\t║ 2. Vào xem chi tiết khóa học ╚════════╗");
        System.out.println("\t║ 3. Sắp xếp tăng dần theo tên khóa học ║");
        System.out.println("\t║ 4. Sắp xếp tăng dần theo vote ╔═══════╝");
        System.out.println("\t║ 5. Sắp xếp tăng dần theo giá  ╚═══════╗");
        System.out.println("\t║ 6. Sắp xếp giảm dần theo tên khóa học ║");
        System.out.println("\t║ 7. Sắp xếp giảm dần theo vote ╔═══════╝");
        System.out.println("\t║ 8. Sắp xếp giảm dần theo giá  ║");
        System.out.println("\t╚═══════════════════════════════╝");
        int chooseThis = Base.chooseIn(0, 8);
        if (chooseThis == 8)
            return 0;
        System.out.print("\nTrang chủ > Quản trị > .... khóa học");
        switch (chooseThis) {
            case 1:
                System.out.println(" > Thêm khóa học");
                addCourse(courses);
                break;
            case 2:
                System.out.println(" > Vào xem chi tiết khóa học");
                System.out.print("\tBạn muốn vào xem khóa học số?: ");
                int courseIndexJoin = Base.chooseInIndex(1, courses.size() + 1);
                if (courseIndexJoin == -1) {
                    System.out.println("\t\tRất tiếc! Không có khóa học này ☹");
                    System.out.print("Nhấn Enter để quay lại");
                    Base.sc.nextLine();
                }
                else {
                    RunMain.courseJoin = courses.get(courseIndexJoin - 1);
                    RunMain.courseJoin.join();
                    RunMain.courseJoin = null;
                }
                break;
            case 3:
                System.out.println(" > Tên khóa tăng dần");
                courses.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
                viewInforCourse(courses);
                break;
            case 4:
                System.out.println(" > Vote tăng dần");
                courses.sort((o1, o2) -> RateControl.rateAVG(o1.getId()).compareTo(RateControl.rateAVG(o2.getId())));
                viewInforCourse(courses);
                break;
            case 5:
                System.out.println(" > Giá tăng dần");
                courses.sort((o1, o2) -> (o1.getPrice() > o2.getPrice() ? 1 : -1));
                viewInforCourse(courses);
                break;
            case 6:
                System.out.println(" > Tên khóa giảm dần");
                courses.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
                viewInforCourse(courses);
                break;
            case 7:
                System.out.println(" > Vote giảm dần");
                courses.sort((o1, o2) -> RateControl.rateAVG(o2.getId()).compareTo(RateControl.rateAVG(o1.getId())));
                viewInforCourse(courses);
                break;
            case 8:
                System.out.println(" > Giá giảm dần");
                courses.sort((o1, o2) -> (o2.getPrice() > o1.getPrice() ? 1 : -1));
                viewInforCourse(courses);
                break;
        }
        return 0;
    }

    // Hàm thêm một khóa học mới và thông báo cho tất cả người dùng
    public static void addCourse(List<Course> courses) {
        String name, introduce;
        double price;
        System.out.print("\tNhập tên khóa học[0-30]: ");
        name = Base.stringRequest(30);
        System.out.print("\tNhập giá: ");
        price = Base.sc.nextDouble();
        Base.sc.nextLine();
        System.out.print("\tNhập phần giới thiệu[0-500]: ");
        introduce = Base.stringRequest(500);
        List<Course> courseData = CourseControl.readAllRecord();
        String lastId = courseData.get(courseData.size()-1).getId();
        String autoId = "C" + String.format("%04d", Integer.parseInt(lastId.substring(1, 5))+1);
        Course course = new Course(autoId, name, price, introduce);
        CourseControl.insertRecord(course);
        courses.add(course);
        Base.accounts.forEach(item -> {
            if (!item.getAdmin()) {
                item.setNotify(item.getNotify() + "\tMột khóa học mới có mã " + course.getId() + " đã được thêm bởi quản trị, hãy vào xem nó có gì mới nào!\n");
                AccountControl.updateRecord(item);
            }
        });
        System.out.print("Bạn có muốn xem khóa học đã tạo không[y/n]? ");
        if(Base.ynQuestion()) {
            System.out.println(Constants.courseHRAd + "\n" + Constants.courseTitleAd + "\n" + Constants.courseHRAd);
            System.out.printf("| %3d ", courses.size());
            System.out.println(course);
            System.out.println(Constants.courseHRAd);
            System.out.print("Nhấn Enter để tiếp tục: ");
            Base.sc.nextLine();
        }
        System.out.print("Bạn có muốn thêm một số bài học hay không[y/n]? ");
        if(Base.ynQuestion()) {
            addLesson(course.getId());
        }
    }

    // Hàm thay đổi thông tin của 1 khóa học và thông báo cho những người mua khoá học đó biết
    public static void editCourse(Course course) {
        int choose2 = 1;
        while (choose2 != 4) {
            System.out.println("Nhập:   0. Quay lại");
            System.out.println("        1. Sửa tên khóa học");
            System.out.println("        2. Sửa tên giá khóa học");
            System.out.println("        3. Sửa phần giới thiệu");
            choose2 = Base.chooseIn(0, 3);
            if (choose2 == 1) {
                System.out.print("\nTrang chủ > Quản trị > .... > Sửa thông tin khóa học > Tên");
                System.out.println("Tên khóa học hiện tại: " + course.getName());
                System.out.print("\tNhập tên khóa học[0-30]: ");
                String name = Base.stringRequest(30);
                course.setName(name);
            }
            if(choose2 == 2) {
                System.out.print("\nTrang chủ > Quản trị > .... > Sửa thông tin khóa học > Giá");
                System.out.println("Giá hiện tại: " + course.getPrice());
                System.out.print("\tNhập giá: ");
                Double price = Base.sc.nextDouble();
                Base.sc.nextLine();
                course.setPrice(price);
            }
            if (choose2 == 3) {
                System.out.print("\nTrang chủ > Quản trị > .... > Sửa thông tin khóa học > Giới thiệu");
                System.out.println("Phần giới thiệu hiện tại: \n"
                        + Base.toParagraph(course.getIntroduce()));
                System.out.print("\tNhập phần giới thiệu[0-500]: ");
                String introduce = Base.stringRequest(500);
                course.setIntroduce(introduce);
            }
            if(choose2 != 0) {
                System.out.println("\t\tCập nhật thành công ☺");
                CourseControl.updateRecord(course);
                Base.courses = CourseControl.readAllRecord();
                System.out.println("Nhấn: 0. Thoát");
                System.out.println("      1. Tiếp tục cập nhật");
                System.out.println("      2. Xem thông tin sau khi cập nhật");
                int chooseNext = Base.chooseIn(0, 2);
                if(chooseNext == 2) {
                    RunMain.courseJoin.show();
                    choose2 = 0;
                }
                if(chooseNext == 0)
                    choose2 = 4;
            }
            List<Bill> billOfCourseUpdate = BillControl.readAllByCourseID(course.getId());
            billOfCourseUpdate.forEach(item -> {
                Account accountNotfy = AccountControl.findRecord(item.getAccountId());
                accountNotfy.setNotify(accountNotfy.getNotify() + "\tKhóa học mã " + course.getId() + " có một số thông tin thay đổi, Bạn hãy vào xem để biết rõ hơn\n");
                AccountControl.updateRecord(accountNotfy);
            });
        }
        System.out.print("Nhập Enter để trở lại: ");
        Base.sc.nextLine();
    }

    /* Hàm xóa một khóa học, sau đó thông báo hoàn tiền cho những người mua khóa này, đồng thời
    thông báo và xóa những đánh giá của các người dùng cho khóa học*/
    public static void deleteCourse(Course course) {
        boolean willRemove = true;
        Course courseDelete = RunMain.courseJoin;
        System.out.println("Xóa khóa học " + courseDelete.getName() + "(" + courseDelete.getId() + ")");
        List<Bill> billOfCourseDelete = BillControl.readAllByCourseID(courseDelete.getId());
        if (billOfCourseDelete.size() != 0) {
            System.out.println("\tKhóa học này có người học! Nếu xóa bạn phải hoàn tiền khóa học cho họ");
            System.out.print("Bạn vẫn muốn tiếp tục xóa nó? [y/n]: ");
            if(!Base.ynQuestion())
                willRemove = false;
        }
        if (willRemove) {
            double money = courseDelete.getPrice();
            double turnOver = AccountControl.findRecord("AC00000001").getBalance()
                    - Math.round(money*billOfCourseDelete.size()*100.0/100.0);
            Account admin = AccountControl.findRecord("AC00000001");
            admin.setBalance(turnOver);
            AccountControl.updateRecord(admin);
            // Hoàn tiền và thông báo cho tất cả người dùng mua khóa học
            billOfCourseDelete.forEach(item -> {
                Account refund = AccountControl.findRecord(item.getAccountId());
                refund.setNotify(refund.getNotify() + "\tKhóa học mã \"" + courseDelete.getId() + "\" đã bị xóa, Bạn được bồi thường " + money + "$ (100% giá khóa học). Hãy vào kiểm tra lại số dư\n");
                refund.setBalance(refund.getBalance() + money);
                AccountControl.updateRecord(refund);
                BillControl.deleteRecord(item.getCourseId(), item.getAccountId());
            });
            List<Lesson> lessonsOfCourseDelete = LessonControl.readAllByCourseID(courseDelete.getId());
            lessonsOfCourseDelete.forEach(item -> LessonControl.deleteRecord(item.getId()));
            // Thông báo cho những người đã đánh giá bài học
            List<Rate> ratesOfCourseDelete = RateControl.readAllByCourseID(courseDelete.getId());
            ratesOfCourseDelete.forEach(item -> {
                Account notify = AccountControl.findRecord(item.getAccountId());
                if(!notify.getAdmin()) {
                    notify.setNotify(notify.getNotify() + "\tKhóa học mã \"" + courseDelete.getId() + "\" đã bị xóa, Bài đánh giá của bạn đã bị chúng tôi gỡ bỏ  ☹\n");
                    AccountControl.updateRecord(notify);
                    RateControl.deleteRecord(item.getCourseId(), item.getAccountId());
                }
            });
            // Thông báo cho tất cả mọi người
            Base.accounts.forEach(item -> {
                if (!item.getAdmin()) {
                    item.setNotify(item.getNotify() + "\tKhóa học có mã " + course.getId() + " đã bị quản trị viên gỡ bỏ\n");
                    AccountControl.updateRecord(item);
                }
            });
            CourseControl.deleteRecord(courseDelete.getId());
            System.out.println("\t\tXóa thành công ☺");
        }
        System.out.print("Nhấn Enter để tiếp tục: ");
        Base.sc.nextLine();
    }

    // Hàm hiển thị danh sách các bài học lessonList đưới dạng bảng
    public static void viewLessonByAdmin(List<Lesson> lessonList) {
        System.out.println(Constants.lessonHRAd + "\n" + Constants.lessonTitleAd + "\n" + Constants.lessonHRAd);
        for (int i = 0; i < lessonList.size(); i++) {
            {
                System.out.printf("| %3d ", i+1);
                System.out.println(lessonList.get(i));
            }
        }
        System.out.println(Constants.lessonHRAd);
    }

    // Hàm xem thông tin các bài học lessons và các lựa chọn xử lý dữ liệu
    public static int viewInforLesson(List<Lesson> lessons) {
        AdminControl.viewLessonByAdmin(lessons);
        System.out.print("Nhắn Enter để tiếp tục: ");
        Base.sc.nextLine();
        Base.sc.nextLine();
        System.out.println("\t╔═════════════════╗");
        System.out.println("\t║ 0  Quay lại     ║");
        System.out.println("\t║ 1. Thêm bài học ╚══╗");
        System.out.println("\t║ 2. Vào xem bài học ║");
        System.out.println("\t║ 3. Sửa bài học ╔═══╝    MENU");
        System.out.println("\t║ 4. Xóa bài học ╚═══════════════════════╗");
        System.out.println("\t║ 5. Hiện danh sách các bài học của khóa ║");
        System.out.println("\t╚════════════════════════════════════════╝");
        int chooseThis = Base.chooseIn(0, 5);
        if (chooseThis == 0)
            return 0;
        System.out.print("\nTrang chủ > Quản trị > .... Bài học");
        switch (chooseThis) {
            case 1:
                System.out.println(" > Thêm bài học");
                System.out.print("Bạn muốn thêm bài học cho khóa có mã?: ");
                String courseID = Base.sc.nextLine();
                if (CourseControl.findRecord(courseID) == null) {
                    System.out.println("\t\tRất tiếc! Không có khóa học nào có mã là \"" + courseID + "\" ☹");
                } else {
                    addLesson(courseID);
                    lessons.add(Base.lessons.get(Base.lessons.size()-1));
                }
                break;
            case 2:
                System.out.println(" > Vào xem bài học");
                System.out.print("\tBạn muốn vào xem bài học của khóa có mã?: ");
                String courseID2 = Base.sc.nextLine();
                List<Lesson> lessons2 = LessonControl.readAllByCourseID(courseID2);
                if(lessons2.size() == 0) {
                    System.out.println("\t\tRất tiếc! Không có khóa nào có mã là \"" + courseID2 + "\" ☹");
                } else {
                    viewLessonByAdmin(lessons2);
                    System.out.print("\tBạn muốn học bài số? ");
                    int lessonIndex = Base.chooseInIndex(1, lessons2.size()+1);
                    if(lessonIndex < 0) {
                        System.out.println("\t\tRất tiếc! Không có bài này ☹");
                    }
                    else {
                        Course.joinLessonInList(lessons2, lessonIndex);
                    }
                }
                System.out.print("Nhấn Enter để tiếp tục: ");
                Base.sc.nextLine();
                break;
            case 3:
                System.out.println(" > Sửa bài học");
                System.out.print("\tBạn muốn sửa bài học số?: ");
                int lessonIndexChange = Base.chooseInIndex(1, lessons.size() + 1);
                if (lessonIndexChange == -1) {
                    System.out.println("\t\tRất tiếc! Không có bài học này ☹");
                    System.out.print("Nhấn Enter để trở về: ");
                    Base.sc.nextLine();
                } else {
                    Lesson lessonChange = lessons.get(lessonIndexChange-1);
                    System.out.println("Bài học: " + lessonChange.getName() + "(" + lessonChange.getId() + ")");
                    System.out.print("Bạn có muốn cập nhật tên bài học[y/n]?: ");
                    if(Base.ynQuestion()) {
                        System.out.println("Tên bài học hiện tại: " + lessonChange.getName());
                        System.out.print("\tNhập tên bài học[0-35]: ");
                        String name = Base.stringRequest(35);
                        lessonChange.setName(name);
                    }
                    System.out.print("Bạn có muốn cập nhật nội dung bài học[y/n]?: ");
                    if(Base.ynQuestion()) {
                        String content;
                        System.out.println("Phần nội dung hiện tại: " + lessonChange.getContent());
                        while (true) {
                            System.out.print("\tNhập phần content[0-50]: ");
                            content = Base.sc.nextLine();
                            if (Constants.regexLink.matcher(content).find())
                                break;
                            System.out.println("Phần nội dung là một link bắt đầu bởi https://owbcode.io/ và có độ dài 50 kí tự");
                        }
                        lessonChange.setContent(content);
                    }
                    LessonControl.updateRecord(lessonChange);
                    lessons = LessonControl.readAllRecord();
                    System.out.print("Bạn có muốn xem khóa học sau khi thay đổi không?[y/n]: ");
                    if(Base.ynQuestion()) {
                        System.out.println(Constants.lessonHRAd + "\n" + Constants.lessonTitleAd + "\n" + Constants.lessonHRAd);
                        System.out.printf("| %3d ", lessonIndexChange);
                        System.out.println(lessonChange);
                        System.out.println(Constants.lessonHRAd);
                    }
                    System.out.print("Nhấn Enter để trở về: ");
                    Base.sc.nextLine();
                }
                break;
            case 4:
                System.out.println(" > Xóa bài học");
                System.out.print("Bạn muốn xóa bài học số?: ");
                int lessonIndexDelete = Base.chooseInIndex(1, lessons.size() + 1);
                if (lessonIndexDelete == -1) {
                    System.out.println("\t\tRất tiếc! Không có bài học này ☹");
                    System.out.print("Nhấn Enter để trở về: ");
                    Base.sc.nextLine();
                } else {
                    Lesson lessonDelete = lessons.get(lessonIndexDelete-1);
                    LessonControl.deleteRecord(lessonDelete.getId());
                    System.out.println("\t\tĐã xóa thành công ☺");
                    System.out.print("Nhấn Enter để trỏ về: ");
                    Base.sc.nextLine();
                }
                break;
            case 5:
                System.out.println(" > Hiện danh sách các bài học của khóa");
                System.out.print("\tBạn muốn vào xem bài học của khóa có mã?: ");
                String courseID5 = Base.sc.nextLine();
                List<Lesson> lessons5 = LessonControl.readAllByCourseID(courseID5);
                if(lessons5.size() == 0) {
                    System.out.println("\t\tRất tiếc! Không có khóa học nào có mã là \"" + courseID5 + "\" ☹");
                } else {
                    viewLessonByAdmin(lessons5);
                }
                System.out.print("Nhấn Enter để tiếp tục: ");
                Base.sc.nextLine();
                break;
        }
        return 0;
    }

    // Hàm thêm một bài học mới cho khóa học
    public static void addLesson(String courseID) {
        System.out.print("\tNhập tên bài học[0-35]: ");
        String name = Base.stringRequest(35);
        String content;
        while (true) {
            System.out.print("\tNhập phần nội dung[0-50]: ");
            content = Base.sc.nextLine();
            if (Constants.regexLink.matcher(content).find())
                break;
            System.out.println("Phần nội dung là một link bắt đầu bởi https://owbcode.io/ và có độ dài 50 kí tự");
        }
        String lastId = Base.lessons.get(Base.lessons.size()-1).getId();
        String autoId = "LS" + String.format("%08d", Integer.parseInt(lastId.substring(2, 10))+1);
        Lesson lesson = new Lesson(autoId, courseID, name, content);
        LessonControl.insertRecord(lesson);
        Base.lessons.add(lesson);
        System.out.println("Nhập    0. Quay lại");
        System.out.println("        1. Tiếp tục thêm");
        System.out.println("        2. Xem các bài học vừa thêm");
        int chooseAddLesson = Base.chooseIn(0, 2);
        if (chooseAddLesson == 1) {
            addLesson(courseID);
        }
        if (chooseAddLesson == 2) {
            viewLessonByAdmin(LessonControl.readAllByCourseID(courseID));
        }
    }

    // Hàm hiển thị danh sách các đánh giá rates đưới dạng bảng
    public static void viewRateByAdmin(List<Rate> rates) {
        System.out.println(Constants.rateHRAd + "\n" + Constants.rateTitleAd + "\n" + Constants.rateHRAd);
        for (int i = 0; i < rates.size(); i++) {
            {
                System.out.printf("| %3d ", i+1);
                System.out.println(rates.get(i));
            }
        }
        System.out.println(Constants.rateHRAd);
    }

    // Hàm xem thông tin các đánh giá rates và các lựa chọn xử lý dữ liệu
    public static int viewInforRate(List<Rate> rates) {
        AdminControl.viewRateByAdmin(rates);
        System.out.print("Nhắn Enter để tiếp tục: ");
        Base.sc.nextLine();
        System.out.println("\t╔═══════════════╗     MENU");
        System.out.println("\t║  0. Quay lại  ╚════════════════╗");
        System.out.println("\t║  1. Xem chi tiết đánh giá số   ║");
        System.out.println("\t║  2. Xem đánh giá của khóa học  ║");
        System.out.println("\t║  3. Xem đánh giá của tài khoản ╚════╗");
        System.out.println("\t║  4. Xem đánh giá chưa được phản hồi ║");
        System.out.println("\t╚═════════════════════════════════════╝");
        int chooseThis = Base.chooseIn(0,4);
        if (chooseThis == 0)
            return 0;
        System.out.print("\nTrang chủ > Quản trị > .... đánh giá");
        switch (chooseThis) {
            case 1:
                System.out.println(" > Xem chi tiết đánh giá số");
                System.out.print("\tBạn muốn vào xem đánh giá số?: ");
                int rateIdexJoin = Base.chooseInIndex(1, rates.size() + 1);
                if (rateIdexJoin == -1) {
                    System.out.println("\t\tRất tiếc! Không có đánh giá này ☹");
                    System.out.print("Nhấn Enter để trở về: ");
                    Base.sc.nextLine();
                } else {
                    RunMain.rateJoin = rates.get(rateIdexJoin - 1);
                    RunMain.rateJoin.join();
                    RunMain.rateJoin = null;
                }
            break;
            case 2:
                System.out.println(" > Xem đánh giá của khóa học");
                System.out.print("\tBạn muốn xem đánh giá của khóa học có mã?: ");
                String courseID1 = Base.sc.nextLine();
                List<Rate> rates1 = RateControl.readAllByCourseID(courseID1);
                if(rates1.size() == 0) {
                    System.out.println("\t\tRất tiếc! Không có khóa học nào có mã là \"" + courseID1 + "\" ☹");
                    System.out.print("Nhấn Enter để trở về: ");
                    Base.sc.nextLine();
                } else {
                    viewInforRate(rates1);
                }
                break;
            case 3:
                System.out.println(" > Xem đánh giá của tài khoản");
                System.out.print("\tBạn muốn xem đánh giá của tài khoản có mã?: ");
                String accountID2 = Base.sc.nextLine();
                List<Rate> rates2 = RateControl.readAllByAccountID(accountID2);
                if(rates2.size() == 0) {
                    System.out.println("\t\tRất tiếc! Không có tài khoản nào có mã là \"" + accountID2 + "\" ☹");
                    System.out.print("Nhấn Enter để trở về: ");
                    Base.sc.nextLine();
                } else {
                    viewInforRate(rates2);
                }
                break;
            case 4:
                System.out.println(" > Xem đánh giá chưa được phản hồi");
                List<Rate> rates3 = rates.stream().filter(item -> item.getReply().equals("")).collect(Collectors.toList());
                viewInforRate(rates3);
        }
        return 0;
    }

    // Hàm hiển thị danh sách các giao dịch bills đưới dạng bảng
    public static void viewBillByAdmin(List<Bill> bills) {
        System.out.println(Constants.billHRAd + "\n" + Constants.billTitleAd + "\n" + Constants.billHRAd);
        for (int i = 0; i < bills.size(); i++) {
            {
                System.out.printf("| %3d ", i+1);
                System.out.println(bills.get(i));
            }
        }
        System.out.println(Constants.billHRAd);
    }

    // Hàm xem thông tin các giao dịch bills và các lựa chọn xử lý dữ liệu
    public static int viewInforBill(List<Bill> bills) {
        AdminControl.viewBillByAdmin(bills);
        System.out.print("Nhắn Enter để tiếp tục: ");
        Base.sc.nextLine();
        System.out.println("\t╔═════════════╗  MENU");
        System.out.println("\t║ 0. Quay lại ╚═══════════════════╗");
        System.out.println("\t║ 1. Xem giao dịch của khóa học   ║");
        System.out.println("\t║ 2. Xem giao dịch của tài khoản  ║");
        System.out.println("\t╚═════════════════════════════════╝");
        int chooseThis = Base.chooseIn(0, 2);
        if (chooseThis == 0)
            return 0;
        System.out.print("\nTrang chủ > Quản trị > .... giao dịch");
        switch (chooseThis) {
            case 1:
                System.out.println(" > Xem giao dịch của khóa học");
                System.out.print("\tBạn muốn xem giao dịch của khóa học có mã?: ");
                String courseID = Base.sc.nextLine();
                List<Bill> billList1 = BillControl.readAllByCourseID(courseID);
                if(billList1.size() == 0) {
                    System.out.println("\t\tRất tiếc! Không có khóa học nào có mã là \"" + courseID + "\" ☹");
                    System.out.print("Nhấn Enter để trở về: ");
                    Base.sc.nextLine();
                } else {
                    viewInforBill(billList1);
                }
                break;
            case 2:
                System.out.println(" > Xem giao dịch của tài khoản");
                System.out.print("\tBạn muốn xem giao dịch của tài khoản có mã?: ");
                String accountID = Base.sc.nextLine();
                List<Bill> billList2 = BillControl.readAllByAccountID(accountID);
                if(billList2.size() == 0) {
                    System.out.println("\t\tRất tiếc! Không có tài khoản nào có mã là \"" + accountID + "\" ☹");
                    System.out.print("Nhấn Enter để trở về: ");
                    Base.sc.nextLine();
                } else {
                    viewInforBill(billList2);
                }
                break;
        }
        return 0;
    }
}
