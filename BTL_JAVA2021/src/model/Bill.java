package model;

public class Bill {
    private String id;
    private String courseId;
    private String accountId;
    private String buyAt;

    public Bill() {

    }

    public Bill(String id, String courseId, String accountId, String buyAt) {
        this.id = id;
        this.courseId = courseId;
        this.accountId = accountId;
        this.buyAt = buyAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public boolean equals(Object obj) {
        Bill bill = (Bill) obj;
        return (bill.getId() == this.id);
    }

    @Override
    public String toString() {
        return String.format("|  %-5s  |  %-5s  |  %-10s  |  %-20s |", id, courseId, accountId, buyAt);
    }
}