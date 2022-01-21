package model;

import controller.AccountControl;
import controller.CourseControl;
import controller.RateControl;
import data.Base;
import run.RunMain;

public class Rate {
    private String courseId;
    private String accountId;
    private int rateValue;
    private String comment;
    private String reply;

    public Rate() {
    }

    public Rate(String courseId, String accountId, int rateValue, String comment, String reply) {
        this.courseId = courseId;
        this.accountId = accountId;
        this.rateValue = rateValue;
        this.comment = comment;
        this.reply = reply;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getRateValue() {
        return rateValue;
    }

    public void setRateValue(int rateValue) {
        this.rateValue = rateValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    // Hàm vào xem chi tiết đánh giá
    public int join() {
        System.out.println(show());
        System.out.println("Nhập    0. Quay lại");
        System.out.println("        1. Phản hồi");
        System.out.println("        2. Xóa đánh giá");
        int chooseJoin = Base.chooseIn(0, 2);
        if (chooseJoin == 1) {
            System.out.println("\nQuản trị > .... đánh giá > Phản hồi");
            boolean willReply = true;
            if(RunMain.rateJoin.reply.length() != 0) {
                System.out.println("Phản hồi trước đó: ");
                System.out.println(Base.toParagraph(RunMain.rateJoin.reply));
                System.out.print("Bạn có muốn thay đổi phản hồi này?[y/n]: ");
                if (!Base.ynQuestion()) {
                    willReply = false;
                }
            }
            if(willReply) {
                System.out.print("Nhập phần phản hồi [0-500]: ");
                String reply = Base.stringRequest(500);
                RunMain.rateJoin.setReply(reply);
                RateControl.deleteRecord(RunMain.rateJoin.getCourseId(), RunMain.rateJoin.getAccountId());
                RateControl.insertRecord(RunMain.rateJoin);
                Account account = AccountControl.findRecord(RunMain.rateJoin.getAccountId());
                account.setNotify(account.getNotify() + "\nĐánh giá của bạn trong khóa học mã \"" + RunMain.rateJoin.getCourseId() + "\" có phản hồi mới, Hãy vào để xem thử");
                AccountControl.updateRecord(account);
            }
            RunMain.rateJoin.join();
            return 0;
        }
        if(chooseJoin == 2) {
            System.out.println("\nQuản trị > .... đánh giá > Xóa đánh giá");
            System.out.print("Nhập lý do xóa [0-400]: ");
            String reason = Base.stringRequest(400);
            Account account = AccountControl.findRecord(RunMain.rateJoin.getAccountId());
            account.setNotify(account.getNotify() + "\nĐánh giá của bạn trong khóa học mã \"" + RunMain.rateJoin.getCourseId() + "\" đã bị xóa vì lý do: " + reason);
            AccountControl.updateRecord(account);
            RateControl.deleteRecord(RunMain.rateJoin.getCourseId(), RunMain.rateJoin.getAccountId());
        }
        return 0;
    }

    // Hàm hiển thị đánh giá
    public String show() {
        String replyFake = (reply.length() == 0) ? reply : "\n  ⮩ REPLY: " + Base.toParagraph(reply);
        String result = "┏------------------\\\\\n";
        result += String.format("|       (%10s).%s đánh giá khóa học %s(%s)\n|Sao đánh giá: %d *\n|\tComment\n%s%s",
                accountId, AccountControl.findRecord(accountId).getFullName(), CourseControl.findRecord(courseId).getName(),
                courseId, rateValue, Base.toParagraph(comment), replyFake
        );
        return result + "\n┗---------------------------------------------------------------------------------------\\\\";
    }

    @Override
    public String toString() {
        String replyFake = (reply.length() > 28) ? reply.substring(0,27)+ "..." :
                ((reply.length() == 0) ? "Chưa có phản hồi" : reply);
        String commentFake = (comment.length() > 28) ? comment.substring(0,27)+ "..." : comment;
        String nameOfCourse = CourseControl.findRecord(courseId).getName();
        String nameOfAccount = AccountControl.findRecord(accountId).getFullName();
        return String.format("|  %-5s  |  %-30s  |  %-10s  |  %-25s  |   %d  |  %-30s |  %-30s |", courseId, nameOfCourse,
                accountId, nameOfAccount, rateValue, commentFake, replyFake);
    }

}
