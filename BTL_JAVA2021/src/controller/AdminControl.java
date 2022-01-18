package controller;

import data.Base;
import data.Constants;
import model.*;
import run.RunMain;

import java.util.List;

public class AdminControl {
    public static int viewInforAccount(List<Account> accounts) {
        System.out.println("\nTrang chủ > Quản trị > Danh sách tài khoản");
        System.out.println("\t\tChức năng đang hoàn thiện ☺");
        System.out.print("Nhập Enter để tiếp tục: ");
        Base.sc.nextLine();
        return 0;
    }

    public static void courseOutput(List<Course> courses) {
        System.out.println(Constants.courseHRAd + "\n" + Constants.courseTitleAd);
        for (int i = 0; i < courses.size(); i++) {
            System.out.printf("| %3d ", i+1);
            System.out.println(courses.get(i));
        }
        System.out.println(Constants.courseHRAd);
    }

    public static int viewInforCourse(List<Course> courses) {
        System.out.println("\nTrang chủ > Quản trị > Danh sách khóa học");
        courseOutput(courses);
        System.out.print("Nhập Enter để tiếp tục: ");
        Base.sc.nextLine();
        System.out.println("|------------------| 1. Thêm khóa học");
        System.out.println("|------------------| 2. Sửa thông tin khóa học");
        System.out.println("|                  | 3. Xóa khóa học");
        System.out.println("|    ,_♥_,   ~     | 4. Sắp xếp tăng dần theo tên khóa học");
        System.out.println("|    [O.o]  /      | 5. Sắp xếp tăng dần theo vote");
        System.out.println("|    /)__)>/       | 6. Sắp xếp tăng dần theo giá");
        System.out.println("|----\"--\"-         | 7. Sắp xếp giảm dần theo tên khóa học ");
        System.out.println("|------------------| 8. Sắp xếp giảm dần theo vote");
        System.out.println("|------------------| 9. Sắp xếp giảm dần theo giá");
        System.out.println("|------------------| 10. Thoát");
        int chooseThis = Base.chooseIn(1,10);
        if (chooseThis == 10)
            return 0;
        System.out.print("\nTrang chủ > .... khóa học");
        switch (chooseThis) {
            case 1:
                System.out.println(" > Thêm khóa học");
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
                System.out.print("\t\tBạn có muốn xem khóa học đã tạo không[y/n]? ");
                if(Base.ynQuestion()) {
                    System.out.println(Constants.courseHRAd + "\n" + Constants.courseTitleAd);
                    System.out.printf("| %3d ", courses.size() + 1);
                    System.out.println(course);
                    System.out.println(Constants.courseHRAd);
                }
                break;
            case 2:
                System.out.println(" > Sửa thông tin khóa học");
                System.out.println("\tBạn muốn xem khóa học số? ");
                int courseIndex = Base.chooseIn(1, courses.size() + 1);
                Course courseNew = courses.get(courseIndex - 1);
                System.out.println(Constants.courseHRAd + "\n" + Constants.courseTitleAd);
                System.out.printf("| %3d ", courseIndex);
                System.out.println(courseNew);
                System.out.println(Constants.courseHRAd);
                int choose2 = 1;
                while (choose2 != 4) {
                    System.out.println("Nhập:   1. Sửa tên khóa học");
                    System.out.println("        2. Sửa tên giá khóa học");
                    System.out.println("        3. Sửa phần giới thiệu");
                    System.out.println("        4. Quay lại");
                    choose2 = Base.chooseIn(1, 4);
                    if (choose2 == 1) {
                        System.out.println("Tên khóa học hiện tại: " + courseNew.getName());
                        System.out.print("\tNhập tên khóa học[0-30]: ");
                        name = Base.stringRequest(30);
                        courseNew.setName(name);
                    }
                    if(choose2 == 2) {
                        System.out.println("Giá hiện tại: " + courseNew.getPrice());
                        System.out.print("\tNhập giá: ");
                        price = Base.sc.nextDouble();
                        Base.sc.nextLine();
                        courseNew.setPrice(price);
                    }
                    if (choose2 == 3) {
                        System.out.println("Phần giới thiệu hiện tại: \n"
                                + Base.toParagraph(courseNew.getIntroduce()));
                        System.out.print("\tNhập phần giới thiệu[0-500]: ");
                        introduce = Base.stringRequest(500);
                        courseNew.setIntroduce(introduce);
                    }
                    if(choose2 != 4) {
                        System.out.println("\t\tCập nhật thành công ☺");
                        CourseControl.updateRecord(courseNew);
                        Base.courses = CourseControl.readAllRecord();
                        System.out.println("Nhấn: 1. Tiếp tục cập nhật");
                        System.out.println("      2. Xem thông tin sau khi cập nhật");
                        System.out.println("      3. Thoát");
                        int chooseNext = Base.chooseIn(0, 3);
                        if(chooseNext == 2) {
                            System.out.println(Constants.courseHRAd + "\n" + Constants.courseTitleAd);
                            System.out.printf("| %3d ", courseIndex);
                            System.out.println(courseNew);
                            System.out.println(Constants.courseHRAd);
                            choose2 = 4;
                        }
                        if(chooseNext == 3)
                            choose2 = 4;
                    }
                    List<Bill> billOfCourseUpdate = BillControl.readAllByCourseID(courseNew.getId());
                    billOfCourseUpdate.forEach(item -> {
                        Account accountNotfy = AccountControl.findRecord(item.getAccountId());
                        accountNotfy.setNotify(accountNotfy.getNotify() + "\tKhóa học mã " + courseNew.getId() + " có một số thông tin thay đổi, Bạn hãy vào xem để biết rõ hơn\n");
                        AccountControl.updateRecord(accountNotfy);
                    });
                }
                break;
            case 3:
                System.out.println(" > Xóa khóa học");
                boolean willRemove = true;
                List<Bill> billOfCourseDelete;
                System.out.print("\tBạn muốn xóa khóa học số?: ");
                int courseIndexDelete = Base.chooseInIndex(1, courses.size() + 1);
                if (courseIndexDelete == -1) {
                    System.out.println("\t\tKhông có khóa học này ☹");
                } else {
                    Course courseDelete = courses.get(courseIndexDelete - 1);
                    System.out.println("Xóa khóa học " + courseDelete.getName() + "(" + courseDelete.getId() + ")");
                    billOfCourseDelete = BillControl.readAllByCourseID(courseDelete.getId());
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
                        billOfCourseDelete.forEach(item -> {
                            Account refund = AccountControl.findRecord(item.getAccountId());
                            refund.setNotify(refund.getNotify() + "\tKhóa học mã \"" + courseDelete.getId() + "\" đã bị xóa, Bạn được bồi thường " + money + "$ (100% giá khóa học). Hãy vào kiểm tra lại số dư\n");
                            refund.setBalance(refund.getBalance() + money);
                            AccountControl.updateRecord(refund);
                            BillControl.deleteRecord(item.getCourseId(), item.getAccountId());
                        });
                        List<Lesson> lessonsOfCourseDelete = LessonControl.readAllByCourseID(courseDelete.getId());
                        lessonsOfCourseDelete.forEach(item -> LessonControl.deleteRecord(item.getId()));
                        List<Rate> ratesOfCourseDelete = RateControl.readAllByCourseID(courseDelete.getId());
                        ratesOfCourseDelete.forEach(item -> {
                            Account notify = AccountControl.findRecord(item.getAccountId());
                            notify.setNotify(notify.getNotify() + "\tKhóa học mã \"" + courseDelete.getId() + "\" đã bị xóa, Bài đánh giá của bạn đã bị chúng tôi gỡ bỏ  ☹\n");
                            AccountControl.updateRecord(notify);
                            RateControl.deleteRecord(item.getCourseId(), item.getAccountId());
                        });
                        CourseControl.deleteRecord(courseDelete.getId());
                        Base.courses = CourseControl.readAllRecord();
                        courses.remove(courseIndexDelete - 1);
                        System.out.println("\t\tXóa thành công ☺");
                    }
                }
                break;
            case 4:
                System.out.println(" > Tên khóa tăng dần");
                courses.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
                courseOutput(courses);
                break;
            case 5:
                System.out.println(" > Vote tăng dần");
                courses.sort((o1, o2) -> RateControl.rateAVG(o1.getId()).compareTo(RateControl.rateAVG(o2.getId())));
                courseOutput(courses);
                break;
            case 6:
                System.out.println(" > Giá tăng dần");
                courses.sort((o1, o2) -> (o1.getPrice() > o2.getPrice() ? 1 : -1));
                courseOutput(courses);
                break;
            case 7:
                System.out.println(" > Tên khóa giảm dần");
                courses.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
                courseOutput(courses);
                break;
            case 8:
                System.out.println(" > Vote giảm dần");
                courses.sort((o1, o2) -> RateControl.rateAVG(o2.getId()).compareTo(RateControl.rateAVG(o1.getId())));
                courseOutput(courses);
                break;
            case 9:
                System.out.println(" > Giá giảm dần");
                courses.sort((o1, o2) -> (o2.getPrice() > o1.getPrice() ? 1 : -1));
                courseOutput(courses);
                break;
        }
        System.out.print("Nhấn Enter để tiếp tục: ");
        Base.sc.nextLine();
        return 0;
    }

    public static int viewInforLesson(List<Lesson> lessons) {
        System.out.println("\nTrang chủ > Quản trị > Danh sách bài học");
        System.out.println("\t\tChức năng đang hoàn thiện ☺");
        System.out.print("Nhập Enter để tiếp tục: ");
        Base.sc.nextLine();
        return 0;
    }

    public static int viewInforRate(List<Rate> rates) {
        System.out.println("\nTrang chủ > Quản trị > Danh sách vote");
        System.out.println("\t\tChức năng đang hoàn thiện ☺");
        System.out.print("Nhập Enter để tiếp tục: ");
        Base.sc.nextLine();
        return 0;
    }

    public static int viewInforBill(List<Bill> bills) {
        System.out.println("\nTrang chủ > Quản trị > Danh sách giao dịch");
        System.out.println("\t\tChức năng đang hoàn thiện ☺");
        System.out.print("Nhập Enter để tiếp tục: ");
        Base.sc.nextLine();
        return 0;
    }
}
