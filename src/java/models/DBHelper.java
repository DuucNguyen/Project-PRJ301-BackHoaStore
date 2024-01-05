/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class DBHelper implements Serializable {

    public static Connection makeConnection() throws ClassNotFoundException, SQLException {
        //load Driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //create connection String
        String url = "jdbc:sqlserver:"
                + "//localhost:1433"
                + ";databaseName=BachHoaStore";
        //open connection
        Connection con = DriverManager.getConnection(url, "sa", "123456");
        return con;
    }
}
