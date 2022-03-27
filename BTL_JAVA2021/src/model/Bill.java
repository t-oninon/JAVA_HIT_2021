package model;

import controller.AccountControl;
import controller.CourseControl;

public class Bill {
    private String courseId;
    private String accountId;
    private String buyAt;

    public Bill() {

    }

    public Bill(String courseId, String accountId, String buyAt) {
        this.courseId = courseId;
        this.accountId = accountId;
        this.buyAt = buyAt;
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

    public String getBuyAt() {
        return buyAt;
    }

    public void setBuyAt(String buyAt) {
        this.buyAt = buyAt;
    }

    // Hàm hiển thị bill của 1 account
    public String show() {
        String nameOfCourse = CourseControl.findRecord(courseId).getName();
        double priceOfCourse = CourseControl.findRecord(courseId).getPrice();
        return String.format("|  %-5s  |  %-30s  |  %7.2f |  %-20s |", courseId, nameOfCourse, priceOfCourse, buyAt);
    }

    @Override
    public String toString() {
        String nameOfCourse = CourseControl.findRecord(courseId).getName();
        double priceOfCourse = CourseControl.findRecord(courseId).getPrice();
        String nameOfAccount = AccountControl.findRecord(accountId).getFullName();
        return String.format("|  %-5s  |  %-30s  |  %-10s  |  %-25s  |  %7.2f |  %-20s |",
                courseId, nameOfCourse, accountId, nameOfAccount, priceOfCourse, buyAt);
    }
}