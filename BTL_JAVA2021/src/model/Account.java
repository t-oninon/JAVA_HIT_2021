package model;

public class Account {
    private String id;
    private String userName;
    private String password;
    private boolean isAdmin;
    private String fullName;
    private double balance;
    private String notify;

    public Account() {
    }

    public Account(String id, String userName, String password, boolean isAdmin, String fullName, double balance, String notify) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
        this.fullName = fullName;
        this.balance = balance;
        this.notify = notify;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    @Override
    public String toString() {
        String notifyFake = (notify.length() > 38) ? notify.substring(1,38)+ "..." :
                ((notify.length() == 0) ? "Tài khoản chưa có thông báo!" : notify);
        return String.format("|  %-10s  |  %-15s  |  %-15s  |  %-5s  |  %-25s  |  %10.2f  |  %-40s  |",
                id, userName, password, isAdmin, fullName, balance, notifyFake);
    }

}
