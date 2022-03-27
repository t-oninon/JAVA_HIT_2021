package model;

import controller.AdminControl;
import controller.LessonControl;
import controller.RateControl;
import data.Base;
import data.Constants;
import run.RunMain;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class Course {
    private String id;
    private String name;
    private double price;
    private String introduce;

    public Course() {
    }

    public Course(String id, String name, double price, String introduce) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.introduce = introduce;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    // Hiển thị thông tin cơ của khóa học
    public void show() {
        System.out.println("\n\t\t\t\t~~ KHÓA HỌC: " + name.toUpperCase(Locale.ROOT) + " ~~");
        System.out.println("Mã khóa học: " + id + "\t\t\t\t\tGiá: " + price + "$");
        System.out.println("Đánh giá trung bình: " + RateControl.rateAVG(id));
        System.out.println(Base.toParagraph(introduce));
    }

    // Hàm đưa ra danh sách các bài học lessonList dưới dạng 2 cột
    public static void viewListLessonTwoColumn(List<Lesson> lessonList) {
        if (lessonList.size() == 0) {
            System.out.println("\nChưa có bài học nào ☹");
        } else {
            System.out.println("\nDanh sách bài học");
            int n = (int) Math.ceil(lessonList.size()*1.0 / 2);
            System.out.println(Constants.lessonHR + "\n" + Constants.lessonTitle + "\n" + Constants.lessonHR);
            for (int i = 0; i < n; i++) {
                {
                    System.out.printf("| %3d |  %-10s  |  %-30s  |",
                            i+1, lessonList.get(i).getId(), lessonList.get(i).getName());
                    if(i+n == lessonList.size()) {
                        System.out.printf("     | %3s |  %-10s  |  %-30s  |\n", "", "", "");
                    } else {
                        System.out.printf("     | %3d |  %-10s  |  %-30s  |\n",
                                i+n+1, lessonList.get(i + n).getId(), lessonList.get(i + n).getName());
                    }
                }
            }
        }
    }

    // Hàm vào xem chi tiết bài học
    public void join() {
        show();
        System.out.print("Nhập Enter để tiếp tục: ");
        Base.sc.nextLine();
        List<Lesson> lessonList = LessonControl.readAllByCourseID(RunMain.courseJoin.getId());
        viewListLessonTwoColumn(lessonList);
        System.out.println(Constants.lessonHR);

        boolean isBought = Base.isBought();
        boolean isAdmin = RunMain.accountJoin.getAdmin();
        int chooseThis = 1;
        System.out.println();
        System.out.println("\t\t╓----------------╥-------------MENU-----------╖");
        System.out.println("\t\t║    ,___,       ║ 0. Quay lại                ║");
        System.out.println("\t\t║    [0.o]  /    ║ 1. Vào học                 ║");
        System.out.println("\t\t║    /)__)>/     ║ 2. Để lại đánh giá         ║");
        System.out.println("\t\t║-----″--″--     ║ 3. Xem đánh giá            ║");

        if (isAdmin) {
            System.out.println("\t\t║----=-----------║ 4. Sửa thông tin khóa học  ║");
            System.out.println("\t\t╟----------------╢ 5. Xóa khóa học            ║");
            System.out.println("\t\t╙----------------╨----------------------------╜");
            chooseThis = Base.chooseIn(0,5);
        }
        else {
            System.out.println("\t\t╙----------------╨----------------------------╜");
            chooseThis = Base.chooseIn(0,3);
        }
        if (chooseThis == 1) {
            if (isAdmin)
                System.out.println("\nTrang chủ > Quản trị > ... > Học");
            else
                System.out.println("\nTrang chủ > Người dùng > ... > Học");
            viewListLessonTwoColumn(lessonList);
            System.out.print("\tBạn muốn học bài số? ");
            int lessonIndex = Base.chooseInIndex(1, lessonList.size()+1);
            if(lessonIndex < 0) {
                System.out.println("\t\tRất tiếc! Không có bài này ☹");
            }
            else {
                if (isBought || isAdmin) {
                    joinLessonInList(lessonList, lessonIndex);
                } else {
                    System.out.println("\t\tBạn chưa mua khóa học ☹");
                    System.out.printf("Số dư của bạn là: %.2f\n", RunMain.accountJoin.getBalance());
                    System.out.print("Bạn có muốn mua khóa học này với giá " +
                            RunMain.courseJoin.getPrice() + "$ hay không[y/n]: ");
                    if(Base.ynQuestion())
                        Base.buyCourse();
                }
            }
            System.out.print("Nhấn Enter để trở về: ");
            Base.sc.nextLine();
            RunMain.courseJoin.join();
        }
        if (chooseThis == 2) {
            if (isAdmin)
                System.out.println("\nTrang chủ > Quản trị > ... > Để lại đánh giá");
            else
                System.out.println("\nTrang chủ > Người dùng > ... > Để lại đánh giá");
            vote();
            System.out.print("Nhấn Enter để trở về: ");
            Base.sc.nextLine();
            RunMain.courseJoin.join();
        }
        if (chooseThis == 3) {
            if (isAdmin)
                System.out.println("\nTrang chủ > Quản trị > ... > Xem đánh giá");
            else
                System.out.println("\nTrang chủ > Người dùng > ... > Xem đánh giá");
            List<Rate> rateInCourse = RateControl.readAllByCourseID(RunMain.courseJoin.getId());
            if (rateInCourse.size() == 0) {
                System.out.println("\t\tChưa có người đánh giá ☹");
            } else {
                AtomicInteger i = new AtomicInteger(1);
                rateInCourse.forEach(item -> System.out.println("\n" + i.getAndIncrement() + ". \n"  + item.show()));
            }
            System.out.println("    Số đánh giá trung bình: " + RateControl.rateAVG(RunMain.courseJoin.getId()));
            if (isAdmin) {
                System.out.print("Bạn có muốn phản hồi hay xóa bài đánh giá nào không?[y/n] : ");
                if(Base.ynQuestion()) {
                    System.out.print("Bạn muốn chọn bài đánh giá số? ");
                    int rateIndex = Base.chooseInIndex(1, rateInCourse.size());
                    if (rateIndex == -1) {
                        System.out.println("\t\tRất tiếc! không có bài đánh giá như vậy ☹");
                    } else {
                        rateInCourse.get(rateIndex-1).join();
                    }
                }
            }
            System.out.print("Nhấn Enter để trở về: ");
            Base.sc.nextLine();
            RunMain.courseJoin.join();
        }
        if(chooseThis == 4) {
            System.out.println("\nTrang chủ > Quản trị > ... > Sửa thông tin khóa học");
            AdminControl.editCourse(RunMain.courseJoin);
            RunMain.courseJoin.join();
        }
        if(chooseThis == 5) {
            System.out.println("\nTrang chủ > Quản trị > ... > Xóa khóa học");
            AdminControl.deleteCourse(RunMain.courseJoin);
        }
        RunMain.courseJoin = null;
    }

    // Hàm vào học bài ở vị trí lessonIndex trong danh sách lessonList
    public static void joinLessonInList(List<Lesson> lessonList, int lessonIndex) {
        System.out.println("\nTrang chủ > ... > Học > Bài số " + lessonIndex);
        lessonList.get(lessonIndex-1).join(lessonIndex);
        int chooseThisThis = 1;
        while (chooseThisThis != 0) {
            System.out.println("\tNhấn:   0. Quay lại");
            System.out.println("\t        1. Bài trước    ⏵");
            System.out.println("\t        2. Bài kế tiếp  ⏴");
            chooseThisThis = Base.chooseIn(0, 3);
            if (chooseThisThis == 1) {
                if (lessonIndex == 1) {
                    System.out.println("Bạn ở bài đầu tiên");
                } else {
                    lessonIndex--;
                    System.out.println("\nTrang chủ > ... > Học > Bài số " + lessonIndex);
                    lessonList.get(lessonIndex-1).join(lessonIndex);
                }
            }
            if (chooseThisThis == 2) {
                if (lessonIndex == lessonList.size()) {
                    System.out.println("Bạn ở bài cuối cùng");
                } else {
                    lessonIndex++;
                    System.out.println("\nTrang chủ > ... > Học > Bài số " + lessonIndex);
                    lessonList.get(lessonIndex-1).join(lessonIndex);
                }
            }
        }
    }

    // Hàm đưa ra đánh giá cho bài học
    public void vote() {
            System.out.println("    ,___,    ");
            System.out.println("    [♥.♥]    Hãy đưa ra ý kiến");
            System.out.println("   <(_._)>*     của bạn");
            System.out.println("----\"--\"-   ");
            Rate myRate = RateControl.findRecord(RunMain.courseJoin.getId(), RunMain.accountJoin.getId());
            boolean stillContinue = true;
            if (myRate != null) {
                System.out.println("     " + "Đánh giá của bạn".toUpperCase(Locale.ROOT));
                System.out.println(myRate.show() + "\n");
                System.out.print("Bạn có muốn thay đổi bản đánh giá[y/n] ? ");
                stillContinue = Base.ynQuestion();
            }
            if(stillContinue) {
                System.out.print("\tNhập bình luận[0-300]: ");
                String comment = Base.stringRequest(300);
                System.out.println("\tBạn hãy vote từ 1* -> 5* để cho biết chất lượng khóa học");
                int star = Base.chooseIn(1,5);
                Rate rate = new Rate(RunMain.courseJoin.getId(), RunMain.accountJoin.getId(), star, comment, "");
                RateControl.deleteRecord(RunMain.courseJoin.getId(), RunMain.accountJoin.getId());
                RateControl.insertRecord(rate);
                System.out.println("\t\tCảm ơn đã đánh giá ☺");
            }
    }

    @Override
    public String toString() {
        String introduceFake = (introduce.length() > 38) ? introduce.substring(0,38)+ ".." : introduce;
        return String.format("|  %-5s  |  %-30s  |  %-8s  |  %7.2f  |  %-40s  |", id, name, RateControl.rateAVG(id), price, introduceFake );
    }
}