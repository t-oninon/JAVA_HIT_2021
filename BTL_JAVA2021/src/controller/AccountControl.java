package controller;

import data.Base;
import data.Constants;
import model.Account;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountControl {
    // Hàm chèn một bản ghi của bảng Accounts vào DataBase
    public static void insertRecord(Account account) {
        String isAdmin_toString = (account.getAdmin() ? "1" : "0");
        String sqlInsert = "insert into Accounts values" +
                "('"+account.getId()+"', '"+account.getUserName()+"', '"+account.getPassword()+"'," +
                " "+isAdmin_toString+", N'"+account.getFullName()+"',  "+account.getBalance()+")";
        try {
            JDBC.statement.executeUpdate(sqlInsert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm cập nhật một bản ghi của bảng Accounts trong DataBase
    public static void updateRecord(Account account) {
        String isAdmin_toString = (account.getAdmin() ? "1" : "0");
        String sqlUpdate = "update Accounts set userName = '"+account.getUserName()+"', " +
                "password = '"+account.getPassword()+"'," +
                "isAdmin = "+isAdmin_toString+", " +
                "fullName = N'"+account.getFullName()+"', " +
                "balance = "+account.getBalance()+"" +
                "where accountID = '"+account.getId()+"'";
        try {
            JDBC.statement.executeUpdate(sqlUpdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm xóa một bản ghi trong của bảng Accounts dựa vào id trong DataBase
    public static void deleteRecord(String id) {
        String sqlDelete = "delete Accounts where accountID = '"+id+"'";
        try {
            JDBC.statement.executeUpdate(sqlDelete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm trả về 1 List<Account> khi đọc toàn bộ thông tin từ bảng Accounts trong DataBase
    public static List<Account> readAllRecord() {
        List<Account> accounts = new ArrayList<>();
        String sqlSelect = "select * from Accounts";
        try {
            ResultSet resultSet = JDBC.statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                Account account = new Account(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4).equals("1"),
                    resultSet.getString(5),
                    Double.parseDouble(resultSet.getString(6))
                );
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    // Tìm Account trong AccountList dựa vào id
    public static Account findRecord(String id) {
        try {
            ResultSet resultSet = JDBC.statement.executeQuery("select * from Accounts where accountID = '" + id + "'");
            if (resultSet.next())
                return new Account(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4).equals("1"),
                        resultSet.getString(5),
                        Double.parseDouble(resultSet.getString(6))
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Trả về courseID nếu tồn tại userName
    public static String userNameExist(String userName)  {
        try {
            ResultSet resultSet = JDBC.statement.executeQuery("select accountID from Accounts where userName = '"+userName+"'");
            if(resultSet.next())
                return resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Tạo request để nhập tên tài khoản đúng form và trả về chuỗi vừa nhập
    public static String requestUserName() {
        String userName;
        while (true) {
            System.out.print("\tNhập tên người dùng: ");
            userName = Base.sc.nextLine();
            if (Constants.regexUserName.matcher(userName).find())
                break;
            System.out.println("Tên người dùng không dấu chứa các kí tự hoa, thường, số,\n" +
                    "độ đài 8 - 15 kí tự");
        }
        return userName;
    }

    // Tạo request để nhập mật khẩu đúng form và trả về chuỗi vừa nhập
    public static String requestPassword() {
        String password;
        while (true) {
            System.out.print("\tNhập mật khẩu: ");
            password = Base.sc.nextLine();
            if (Constants.regexPassword.matcher(password).find())
                break;
            System.out.println("Mật khẩu không dấu, chứa ít nhất 1 chữ hoa, chữ thường, số,\n" +
                    "kí tự đặc biệt, độ đài 8 - 15 kí tự");
        }
        String rePassword;
        while (true) {
            System.out.print("\tXác nhận lại mật khẩu: ");
            rePassword = Base.sc.nextLine();
            if (rePassword.equals(password))
                break;
            System.out.println("\t\tMật khẩu nhập lại không khớp ☹");
        }
        return password;
    }
}
