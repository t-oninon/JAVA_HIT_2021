package controller;

import model.Bill;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillControl {
    // Hàm chèn một bản ghi của bảng Bills vào DataBase
    public static void insertRecord(Bill bill) {
        String sqlInsert = "insert into Bills values " +
                "('"+bill.getCourseId()+"'," +
                "'"+bill.getAccountId()+"'," +
                "'"+bill.getBuyAt()+"')";
        try {
            JDBC.statement.executeUpdate(sqlInsert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm cập nhật một bản ghi của bảng Bills trong DataBase
    public static void updateRecord(Bill bill) {
        String sqlUpdate = "update Bills set " +
                "buyAt = '"+bill.getBuyAt()+"'" +
                "where courseID = '"+bill.getCourseId()+"' and accountID = '"+bill.getAccountId()+"'";
        try {
            JDBC.statement.executeUpdate(sqlUpdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm xóa một bản ghi trong của bảng Bills dựa vào courseID, accountID
    public static void deleteRecord(String courseID, String accountID) {
        String sqlDelete = "delete Bills where courseID = '"+courseID+"' and accountID = '"+accountID+"'";
        try {
            JDBC.statement.executeUpdate(sqlDelete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm xóa một bản ghi trong của bảng Bills dựa vào courseID, accountID
    public static Bill findRecord(String courseID, String accountID) {
        try {
            ResultSet resultSet = JDBC.statement.executeQuery("select * from Bills " +
                    "where courseID = '"+courseID+"' and accountID = '"+accountID+"'");
            if (resultSet.next()) {
                return new Bill(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Hàm trả về 1 List<Bill> khi đọc toàn bộ thông tin từ bảng Accounts trong DataBase
    public static List<Bill> readAllRecord() {
        List<Bill> bills = new ArrayList<>();
        String sqlSelect = "select * from Bills";
        try {
            ResultSet resultSet = JDBC.statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                Bill bill = new Bill(
                     resultSet.getString(1),
                     resultSet.getString(2),
                     resultSet.getString(3)
                );
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    // Hàm trả về 1 List<Bill> của tài khoàn có mã là accountID
    public static List<Bill> readAllByAccountID(String accountID) {
        List<Bill> bills = new ArrayList<>();
        String sqlSelect = "select * from Bills where accountID = '"+accountID+"'";
        try {
            ResultSet resultSet = JDBC.statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                Bill bill = new Bill(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    // Hàm trả về 1 List<Bill> của khóa học có mã là courseID
    public static List<Bill> readAllByCourseID(String courseID) {
        List<Bill> bills = new ArrayList<>();
        String sqlSelect = "select * from Bills where courseID = '"+courseID+"'";
        try {
            ResultSet resultSet = JDBC.statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                Bill bill = new Bill(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

}
