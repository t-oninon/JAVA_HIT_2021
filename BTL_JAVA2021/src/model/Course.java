package model;

import controller.LessonControl;
import controller.RateControl;
import data.Base;
import data.Constants;
import run.RunMain;
import java.util.List;
import java.util.Locale;

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

    public boolean equals(Object obj) {
        Course course = (Course) obj;
        return (course.getId() == this.id);
    }


    public void join() {
        boolean isBought = Base.isBought();
        System.out.printf("%40s\n", name.toUpperCase(Locale.ROOT));
        System.out.println("Mã khóa học: " + id + "\t\t\t\t\tGiá: " + price + "$");
        System.out.println("Đánh giá trung bình: " + RateControl.rateAVG(id));
        System.out.println(Base.toParagraph(introduce));
        System.out.println("\nDanh sách bài học");
        List<Lesson> lessonList = LessonControl.readAllByCourseID(id);
        int n = (int) Math.ceil(lessonList.size()*1.0 / 2);
        System.out.println(Constants.lessonHR + "\n" + Constants.lessonTitle);
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
        System.out.println(Constants.lessonHR);
        int chooseThis = 1;
        System.out.println("\t\t1. Vào học\t\t\t2. Vote");
        System.out.println("\t\t3. Xem các lượt vote\t\t\t4. Quay lại");
        chooseThis = Base.chooseIn(1,4);
        if (chooseThis == 1) {
            System.out.println("\nTrang chủ > Người dùng > ... > Học");
            System.out.println("\tBạn muốn học bài số? ");
            int lessonIndex = Base.chooseIn(1, lessonList.size()+1);
            if (isBought) {
                lessonList.get(lessonIndex-1).join(lessonIndex);
                int chooseThisThis = 1;
                while (chooseThisThis != 3) {
                    System.out.println("\tNhấn:   1. Bài trước đó");
                    System.out.println("\t        2. Bài kế tiếp");
                    System.out.println("\t        3. Thoát");
                    chooseThisThis = Base.chooseIn(1, 3);
                    if (chooseThisThis == 1) {
                        if (lessonIndex == 1) {
                            System.out.println("Bạn ở bài đầu tiên");
                        } else {
                            lessonIndex--;
                            lessonList.get(lessonIndex-1).join(lessonIndex);
                        }
                    } else if (chooseThisThis == 2) {
                        if (lessonIndex == lessonList.size()) {
                            System.out.println("Bạn ở bài cuối cùng");
                        } else {
                            lessonIndex++;
                            lessonList.get(lessonIndex-1).join(lessonIndex);
                        }
                    }
                }
            } else {
                System.out.println("\t\tBạn chưa mua khóa học ☹");
                System.out.println("Số dư của bạn là: " + RunMain.accountJoin.getBalance());
                System.out.print("Bạn có muốn mua khóa học này với giá " +
                        RunMain.courseJoin.getPrice() + "$ hay không[y/n]: ");
                if(Base.ynQuestion())
                    Base.buyCourse();
            }
            System.out.print("Nhập Enter để trở về: ");
            Base.sc.nextLine();
            RunMain.courseJoin.join();
        }
        if (chooseThis == 2) {
            System.out.println("\nTrang chủ > Người dùng > ... > Vote");
            vote();
            System.out.print("Nhập Enter để trở về: ");
            Base.sc.nextLine();
            RunMain.courseJoin.join();
        }
        if (chooseThis == 3) {
            System.out.println("\nTrang chủ > Người dùng > ... > Xem vote");
            List<Rate> rateInCourse = RateControl.readAllByCourseID(RunMain.courseJoin.getId());
            System.out.println(RunMain.courseJoin.getId());
            if (rateInCourse.size() == 0) {
                System.out.println("\t\tChưa có người vote ☹");
            } else {
                rateInCourse.forEach(item -> System.out.println("\n" + item));
            }
            System.out.println("    Số đánh giá trung bình: " + RateControl.rateAVG(RunMain.courseJoin.getId()));
            System.out.print("Nhập Enter để trở về: ");
            Base.sc.nextLine();
            RunMain.courseJoin.join();
        }

        RunMain.courseJoin = null;
    }


    public void vote() {
            System.out.println("    ,___,    ");
            System.out.println("    [♥.♥]    Hãy đưa ra ý kiến");
            System.out.println("   <(_._)>*     của bạn");
            System.out.println("----\"--\"-   ");
            Rate myRate = RateControl.findRecord(RunMain.courseJoin.getId(), RunMain.accountJoin.getId());
            boolean stillContinue = true;
            if (myRate != null) {
                System.out.println("     " + "Đánh giá của bạn".toUpperCase(Locale.ROOT));
                System.out.println(myRate + "\n");
                System.out.print("Bạn có muốn thay đổi bản đánh giá[y/n] ? ");
                stillContinue = Base.ynQuestion();
            }
            if(stillContinue) {
                System.out.print("\tNhập bình luận[0-200]: ");
                String comment = Base.stringRequest(200);
                System.out.print("\tBạn vote[1-5]*: ");
                int star = Base.chooseIn(1,5);
                Rate rate = new Rate(RunMain.courseJoin.getId(), RunMain.accountJoin.getId(), star, comment);
                RateControl.deleteRecord(RunMain.courseJoin.getId(), RunMain.accountJoin.getId());
                RateControl.insertRecord(rate);
                System.out.println("\t\tCảm ơn đã đánh giá ☺");
            }
    }

    @Override
    public String toString() {
        String introduceFake = (introduce.length() > 38) ? introduce.substring(0,38)+ ".." : introduce;
        return String.format("|  %-5s  |  %-30s  |  %-8s  |  %-7.2f  |  %-40s  |", id, name, RateControl.rateAVG(id), price, introduceFake );
    }
}