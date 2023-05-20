/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import dao.*;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author LENOVO
 */
public class ContactList_Model {

    private DefaultTableModel tableModel;
    Connector connector;
    Object[] column = {"ID", "Image", "Name", "Gender", "Phone Number", "Email", "Address"};

    public ContactList_Model() {
        connector = new Connector();
        tableModel = new DefaultTableModel();
    }
    
      public DefaultTableModel getTableModel() {
        populateTableModel();
        return tableModel;
    }
    
    public DefaultTableModel searchContact(String search) {
        String query = "SELECT id_contact, image, name, gender, phone_number, email, address  FROM contacts WHERE id_user = 1 AND name LIKE '%" + search + "%';";
        populateTableModel(query);
        return tableModel;
    }

    public void updateContact(int id, String image, String name, String gender, String noTelp, String email, String address) throws SQLException {
    try {
        Connection connection = connector.getConnection();
        String query = "UPDATE contacts SET image = ?, name = ?, gender = ?, phone_number = ?, email = ?, address = ? WHERE id_contact = ?";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, image);
        statement.setString(2, name);
        statement.setString(3, gender);
        statement.setString(4, noTelp);
        statement.setString(5, email);
        statement.setString(6, address);
        statement.setInt(7, id);

        int rowsAffected = statement.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);

        statement.close();
        connection.close();
    } catch (SQLException e) {
        System.out.println("Update failed");
        e.printStackTrace();
        throw new SQLException(e);
    }
}

    
    public void addContact(String image, String name, String gender, String noTelp, String email, String address) throws SQLException {
        try {
            Connector connector2 = new Connector();
            Connection connection = connector2.getConnection();
            String query = "INSERT INTO contacts (name, phone_number, email, image, address, gender, id_user) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            int id_user = 1;

            statement.setString(1, name);
            statement.setString(2, noTelp);
            statement.setString(3, email);
            statement.setString(4, image);
            statement.setString(5, address);
            statement.setString(6, gender);
            statement.setInt(7, id_user);

            int rowsAffected = statement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Insert Failed");
            e.printStackTrace();
            throw new SQLException(e);
        }
   
    }

   public void deleteContact(int id) throws SQLException {
    try {
        Connection connection = connector.getConnection();
        String query = "DELETE FROM contacts WHERE id_contact = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);

        int rowsAffected = statement.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);

        statement.close();
        connection.close();
    } catch (SQLException e) {
        System.out.println("Delete failed");
        e.printStackTrace();
        throw new SQLException(e);
    }
}
    
    private void populateTableModel(){
        try {
            Connection connection = connector.getConnection();
            Statement statement = connection.createStatement();

            String selectQuery = "SELECT id_contact, image, name, gender, phone_number, email, address  FROM contacts WHERE id_user = 1;";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            System.out.println("resultset : " + resultSet);

            ResultSetMetaData metaData = resultSet.getMetaData();
            System.out.println("metadata : " + metaData);
            int columnCount = metaData.getColumnCount();
            System.out.println("column : " + columnCount);

            // Set column names
            for (Object columnName : column) {
                //String columnName = metaData.getColumnName(i);
                tableModel.addColumn(columnName);
            }

             
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = resultSet.getObject(i);
                }
                tableModel.addRow(rowData);
            }
            
            System.out.println("table : " + tableModel);
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Get data table failed!");
            e.printStackTrace();
           
            
        }

    }

    private void populateTableModel(String query) {
        try {
            Connection connection = connector.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("resultset : " + resultSet);

            ResultSetMetaData metaData = resultSet.getMetaData();
            System.out.println("metadata : " + metaData);
            int columnCount = metaData.getColumnCount();
            System.out.println("column : " + columnCount);

            // Set column names
            for (Object columnName : column) {
                //String columnName = metaData.getColumnName(i);
                tableModel.addColumn(columnName);
            }

          
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = resultSet.getObject(i);
                }
                tableModel.addRow(rowData);
            }
          
            System.out.println("table : " + tableModel);
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Get data table failed!");
            e.printStackTrace();
        }

    }
//    public static void main(String[] args) {
//        new ContactList_Model();
//    }
//    
}
