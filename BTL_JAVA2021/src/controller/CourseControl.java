package controller;

import model.Account;
import model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseControl {

    // Hàm chèn một bản ghi của bảng Accounts vào DataBase
    public static void insertRecord(Course course) {
        String sqlInsert  = "insert into Courses values" +
                "('"+course.getId()+"'," +
                "N'"+course.getName()+"'," +
                ""+course.getPrice()+"," +
                "N'"+course.getIntroduce()+"')";
        try {
            JDBC.statement.executeUpdate(sqlInsert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm cập nhật một bản ghi của bảng Accounts trong DataBase
    public static void updateRecord(Course course) {
        String sqlUpdate = "update Courses set name = N'"+course.getName()+"'," +
                "price = "+course.getPrice()+"," +
                "introduce = 'N"+course.getIntroduce()+"'" +
                "where courseID = '"+course.getId()+"'";
        try {
            JDBC.statement.executeUpdate(sqlUpdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm xóa một bản ghi trong của bảng Accounts dựa vào id trong DataBase
    public static void deleteRecord(String id) {
        String sqlDelete = "delete Courses where courseID = '"+id+"'";
        try {
            JDBC.statement.executeUpdate(sqlDelete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm trả về 1 List<Account> khi đọc toàn bộ thông tin từ bảng Accounts trong DataBase
    public static List<Course> readAllRecord() {
        List<Course> courses = new ArrayList<>();
        String sqlSelect = "select * from Courses";
        try {
            ResultSet resultSet = JDBC.statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                Course course = new Course(
                  resultSet.getString(1),
                  resultSet.getString(2),
                  resultSet.getDouble(3),
                  resultSet.getString(4)
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public static List<Course> readByCondition(String accountID ,boolean isMyCourse) {
        List<Course> courses = new ArrayList<>();
        String sqlSelect;
        if (isMyCourse) {
            sqlSelect = "select * from Courses " +
                    "where courseID IN " +
                    "(select courseID from Bills where accountID = '"+accountID+"')";
        } else {
            sqlSelect = "select * from Courses " +
                    "where courseID NOT IN " +
                    "(select courseID from Bills where accountID = '"+accountID+"')";
        }

        try {
            ResultSet resultSet = JDBC.statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                Course course = new Course(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getString(4)
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

}
