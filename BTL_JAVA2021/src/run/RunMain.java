package run;

import controller.*;
import data.Base;
import model.*;
import java.sql.*;

public class RunMain {
    public static Account accountJoin = null;
    public static Account adminJoin = null;
    public static Course courseJoin = null;
    public static Rate rateJoin = null;

    public static void main(String[] args) throws SQLException {
        Base.home();
        JDBC.conn.close();
    }
}
