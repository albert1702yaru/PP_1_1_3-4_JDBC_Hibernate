package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "Albert", "Albert");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}