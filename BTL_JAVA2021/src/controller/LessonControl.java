package controller;

import model.Bill;
import model.Lesson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonControl {
    // Hàm chèn một bản ghi của bảng Lesson vào DataBase
    public static void insertRecord(Lesson lesson) {
        String sqlInsert = "insert into Lessons values" +
                "('"+lesson.getId()+"'," +
                "'"+lesson.getCourseId()+"'," +
                "N'"+lesson.getName()+"'," +
                "N'"+lesson.getContent()+"')";
        try {
            JDBC.statement.executeUpdate(sqlInsert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm cập nhật một bản ghi của bảng Lesson trong DataBase
    public static void updateRecord(Lesson lesson) {
        String sqlUpdate = "update Lessons set " +
                "courseID = '"+lesson.getCourseId()+"', " +
                "name = N'"+lesson.getName()+"', " +
                "content = N'"+lesson.getContent()+"'" +
                "where lessonID = '"+lesson.getId()+"'";
        try {
            JDBC.statement.executeUpdate(sqlUpdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm xóa một bản ghi trong của bảng Lesson dựa vào id trong DataBase
    public static void deleteRecord(String id) {
        String sqlDelete = "delete Lessons where lessonID = '"+id+"'";
        try {
            JDBC.statement.executeUpdate(sqlDelete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm trả về 1 List<Lesson> khi đọc toàn bộ thông tin từ bảng Lesson trong DataBase
    public static List<Lesson> readAllRecord() {
        List<Lesson> lessons = new ArrayList<>();
        String sqlSelect = "select * from Lessons";
        try {
            ResultSet resultSet = JDBC.statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                Lesson lesson = new Lesson(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    // Hàm trả về 1 List<Lesson> của khóa học có mã là courseID
    public static List<Lesson> readAllByCourseID(String courseID) {
        List<Lesson> lessons = new ArrayList<>();
        String sqlSelect = "select * from Lessons where courseID = '"+courseID+"'";
        try {
            ResultSet resultSet = JDBC.statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                Lesson lesson = new Lesson(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
                lessons.add(lesson);
            }
            return lessons;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
