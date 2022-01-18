package model;

import controller.AccountControl;
import data.Base;

public class Rate {
    private String courseId;
    private String accountId;
    private int rateValue;
    private String comment;

    public Rate() {
    }

    public Rate(String courseId, String accountId, int rateValue, String comment) {
        this.courseId = courseId;
        this.accountId = accountId;
        this.rateValue = rateValue;
        this.comment = comment;
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

    public void show() {
        System.out.printf("||");
    }

    @Override
    public String toString() {
        return String.format("==================(%10s).%-25s\nSao đánh giá: %d *\n\tComment\n%s",
        accountId, AccountControl.findRecord(accountId).getFullName(), rateValue, Base.toParagraph(comment)
        );
    }
}
