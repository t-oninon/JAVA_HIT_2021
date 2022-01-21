package controller;

import model.Rate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RateControl {
    // Hàm chèn một bản ghi của bảng Rates vào DataBase
    public static void insertRecord(Rate rate) {
        String sqlInsert = "insert into Rates values" +
                "('"+rate.getCourseId()+"'," +
                "'"+rate.getAccountId()+"'," +
                ""+rate.getRateValue()+", " +
                "N'"+rate.getComment()+"', " +
                "N'"+rate.getReply()+"')";
        try {
            JDBC.statement.executeUpdate(sqlInsert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm cập nhật một bản ghi của bảng Rates trong DataBase
    public static void updateRecord(Rate rate) {
        String sqlUpdate = "update Rates set " +
                "rateValue = "+rate.getRateValue()+", comment = N'"+rate.getComment()+"', reply = N'"+rate.getReply()+"'" +
                " where courseID = '"+rate.getCourseId()+"' and accountID ='"+rate.getAccountId()+"'";
        try {
            JDBC.statement.executeUpdate(sqlUpdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm xóa một bản ghi trong của bảng Rates dựa vào courseID, accountID
    public static void deleteRecord(String courseID, String accountID) {
        String sqlDelete = "delete Rates where courseID = '"+courseID+"' and accountID = '"+accountID+"'";
        try {
            JDBC.statement.executeUpdate(sqlDelete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm tìm một bản ghi trong của bảng Rates dựa vào courseID, accountID
    public static Rate findRecord(String courseID, String accountID) {
        try {
            ResultSet resultSet = JDBC.statement.executeQuery("select * from Rates " +
                    "where courseID = '"+courseID+"' and accountID = '"+accountID+"'");
            if (resultSet.next())
                return new Rate(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Hàm trả về 1 List<Rate> khi đọc toàn bộ thông tin từ bảng Accounts trong DataBase
    public static List<Rate> readAllRecord() {
        List<Rate> rates = new ArrayList<>();
        String sqlSelect = "select * from Rates";
        try {
            ResultSet resultSet = JDBC.statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                Rate rate = new Rate(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
                rates.add(rate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rates;
    }

    // Hàm trả về 1 List<Rate> của tài khoàn có mã là accountID
    public static List<Rate> readAllByAccountID(String accountID) {
        List<Rate> rates = new ArrayList<>();
        String sqlSelect = "select * from Rates where accountID = '"+accountID+"'";
        try {
            ResultSet resultSet = JDBC.statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                Rate rate = new Rate(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
                rates.add(rate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rates;
    }

    // Hàm trả về 1 List<Rate> của khóa học có mã là courseID
    public static List<Rate> readAllByCourseID(String courseID) {
        List<Rate> rates = new ArrayList<>();
        String sqlSelect = "select * from Rates where courseID = '"+courseID+"'";
        try {
            ResultSet resultSet = JDBC.statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                Rate rate = new Rate(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
                rates.add(rate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rates;
    }

    // Hàm trả về chuỗi biểu thị lượt vote của một khóa học
    public static String rateAVG(String courseID) {
        String sqlSelect = "select avg(rateValue), count(rateValue) from Rates " +
                " where courseID = '"+courseID+"'";
        try {
            ResultSet resultSet = JDBC.statement.executeQuery(sqlSelect);
            if(resultSet.next()) {
                return String.format("%4.2f / %d", resultSet.getDouble(1),
                        resultSet.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "0 / 0";
    }
}
