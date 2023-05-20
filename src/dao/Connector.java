/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;

/**
 *
 * @author LENOVO
 */
public class Connector {
    private String DBurl = "jdbc:mysql://localhost:3306/contact_list";
    private String DBusername = "root";
    private String DBpassword = "";
    private Connection connection;
    Statement statement;
    public Connector(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(DBurl,DBusername,DBpassword);
            System.out.println("Koneksi Berhasil");
        }
        catch(Exception ex){
            System.out.println("Connection Failed");
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
//    public static void main(String[] args) {
//        new Connector();
//    }
}
