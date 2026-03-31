package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3307/smartphone_store_management";
    private static final String USER = "root";
    private static final String PASS = "12345678";

    public static Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            return connection;
        }catch (Exception e){
            System.out.println("Kết nối thất bại : " + e.getMessage());
        }
        return null;
    }
}