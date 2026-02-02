package com.eventvol.util;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    public static Connection getDBConnection() throws Exception {     
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "vaishu";
        String password = "system";
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }
}
