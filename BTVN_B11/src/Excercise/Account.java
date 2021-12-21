package Excercise;


import javax.swing.plaf.IconUIResource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {
    private long id;
    private String fullName;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String createAt;

//    Use for regex
    static Pattern pattern;
    static Matcher matcher;

    public Account() {
    }

    public Account(long id, String fullName, String userName, String password, String email, String phone, String createAt) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.createAt = createAt;
    }

    public long getId() {
        return id;
    }

    public void setId() {
        System.out.print("Enter id: ");
        this.id = RunMain.sc.nextLong();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName() {
        RunMain.sc.nextLine();
        System.out.print("Enter fullName: ");
        this.fullName = RunMain.sc.nextLine();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName() {
        pattern = Pattern.compile("^[a-zA-Z0-9]{6,}$");
        String userName = "";
        do {
            System.out.print("Enter user name: ");
            userName = RunMain.sc.nextLine();
            matcher = pattern.matcher(userName);
        } while (!matcher.find());
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword() {
        pattern = Pattern.compile("^(?=.*\\w)(?=.*[@$!%*#?&])[\\w@$!%*#?&]{8,}$");
        String password = "";
        do {
            System.out.print("Enter password: ");

            password = RunMain.sc.nextLine();
            matcher = pattern.matcher(password);
        } while (!matcher.find());
        // Rewrite password
        do {
            System.out.print("Confirm password: ");
        } while(password.compareTo(RunMain.sc.nextLine()) != 0);
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail() {
        pattern = Pattern.compile("^[\\w\\.]+@(\\w+\\.)+[\\w]{2,4}$");
        String email = "";
        do {
            System.out.print("Enter email: ");
            email = RunMain.sc.nextLine();
            matcher = pattern.matcher(email);
        } while (!matcher.find());
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone() {
        pattern = Pattern.compile("^(\\d{8,})$");
        String phone = "";
        do {
            System.out.print("Enter phone number: ");
            phone = RunMain.sc.nextLine();
            System.out.println(phone);
            matcher = pattern.matcher(phone);
        } while (!matcher.find());
        this.email = email;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String convertToFile() {
        return String.format("%d|%s|%s|%s|%s|%s|%s"
                , id, fullName, userName, password,
                email, phone, createAt);
    }

    @Override
    public String toString() {
        return String.format("%10d|%20s|%10s|%15s|%20s|%15s|%10s"
                , id, fullName, userName, password,
                email, phone, createAt);
    }

    @Override
    public boolean equals(Object obj) {
        Account account = (Account) obj;
        return (account.getId() == this.id) && (account.userName == this.userName);
    }
}
