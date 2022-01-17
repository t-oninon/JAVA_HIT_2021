package controller;

import java.sql.*;

public class JDBC {
    public static Connection conn = getJDBCConnection();
    public static Statement statement;

    static {
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getJDBCConnection() {
        final String url = "jdbc:sqlserver://localhost:1433;databaseName=QLKH;user=sa;password=123";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
