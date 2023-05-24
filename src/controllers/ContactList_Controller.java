/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import models.ContactList_Model;
import views.ContactList_View;
import views.login;

/**
 *
 * @author LENOVO
 */
public class ContactList_Controller {

    private int id_user;
    private ContactList_Model contactModel;
    private ContactList_View contactView;

    public ContactList_Controller(ContactList_View contactView, int id_user) {
        this.contactModel = contactModel;
        this.contactView = contactView;
        this.id_user = id_user;
        //this.contactModel = new ContactList_Model(id_user);
    }

    public void getTable() {
        contactModel = new ContactList_Model(id_user);
        DefaultTableModel TableModel = contactModel.getTableModel();
        contactView.setTableModel(TableModel);
    }

    public void logout() {
        contactView.dispose();
        new login().setVisible(true);
    }

    public void searchContact(String search) {
        contactModel = new ContactList_Model(id_user);
        DefaultTableModel TableModel = contactModel.searchContact(search);
        contactView.setTableModel(TableModel);
    }

    public void updateContact(String idStr, String sourcePathStr, String image, String name, String gender, String noTelp, String email, String address) {
        try {
            if (idStr == null || idStr.isEmpty()) {
                throw new NullPointerException("Select the Row first");
            }
            if (image != null) {
                Path sourcePath = Path.of(sourcePathStr);
                Path destinationPath = Path.of("assets/" + image);
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            } else {
                image = "";
            }
            contactModel = new ContactList_Model(id_user);
            int id = Integer.parseInt(idStr);
            contactModel.updateContact(id, image, name, gender, noTelp, email, address);
            getTable();
            contactView.resetInput();
        } catch (Exception e) {
            contactView.showMessage("Update Failed\n" + e.getMessage());
        }

    }

    public void tableRowClicked() {
        contactView.resetInput();
        contactView.getRowData();
    }

    public void deleteContact(String idStr) {
        try {
            if (idStr == null || idStr.isEmpty()) {
                throw new NullPointerException("Select the Row first");
            }
            contactModel = new ContactList_Model(id_user);
            int id = Integer.parseInt(idStr);
            contactModel.deleteContact(id);
            getTable();
            contactView.resetInput();
        } catch (Exception e) {
            contactView.showMessage("Delete Failed\n" + e.getMessage());
        }

    }

    public void addContact(String sourcePathStr, String image, String name, String gender, String noTelp, String email, String address) {
        try {
            if (image != null) {
                Path sourcePath = Path.of(sourcePathStr);
                Path destinationPath = Path.of("assets/" + image);
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            } else {
                image = "";
            }
//            if (image != null) {
//                // Generate a unique name for the image file based on the current timestamp
//                String uniqueName = System.currentTimeMillis() + "_"+image;
//
//                Path sourcePath = Path.of(sourcePathStr);
//                Path destinationPath = Path.of("assets/" + uniqueName);
//                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
//
//                // Store the unique name in the image variable
//                image = uniqueName;
//            } else {
//                image = "";
//            }
            contactModel = new ContactList_Model(id_user);
            System.out.println(image + name + gender + noTelp + email + address);
            contactModel.addContact(image, name, gender, noTelp, email, address);
            getTable();
            contactView.resetInput();
        } catch (Exception e) {
            contactView.showMessage("Failed to add contact \n" + e.getMessage());
        }
    }

}
