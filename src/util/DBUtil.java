package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    static String ip = "127.0.0.1";
    static int port = 3306;
    static String database = "trading";
    static String name = "root";
    static String password = "19980806";
    static String encoding = "UTF-8";

    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s",ip,port,database,encoding);
        return DriverManager.getConnection(url,name,password);
    }

    //测试连接
    public static void main(String[] args) throws SQLException{
        System.out.print(getConnection());
    }
}