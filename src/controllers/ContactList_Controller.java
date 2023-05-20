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

/**
 *
 * @author LENOVO
 */
public class ContactList_Controller {

    private ContactList_Model contactModel;
    private ContactList_View contactView;

    public ContactList_Controller(ContactList_Model contactModel, ContactList_View contactView) {
        this.contactModel = contactModel;
        this.contactView = contactView;
        contactModel = new ContactList_Model();
    }

    public void getTable() {
        contactModel = new ContactList_Model();
        DefaultTableModel TableModel = contactModel.getTableModel();
        contactView.setTableModel(TableModel);
    }

    public void searchContact(String search) {
        contactModel = new ContactList_Model();
        DefaultTableModel TableModel = contactModel.searchContact(search);
        contactView.setTableModel(TableModel);
    }

    public void updateContact(String idStr, String image, String name, String gender, String noTelp, String email, String address) {
        try {
            if(idStr==null || idStr==""){
                throw new IllegalArgumentException("Select the Row first");
            }
            contactModel = new ContactList_Model();
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
            if(idStr==null){
                throw new IllegalArgumentException("Select the Row first");
            }
            contactModel = new ContactList_Model();
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
            System.out.println(image + name + gender + noTelp + email + address);
            contactModel.addContact(image, name, gender, noTelp, email, address);
            getTable();
            contactView.resetInput();
        } catch (Exception e) {
            contactView.showMessage("Failed to add contact \n" + e.getMessage());
        }
    }

}
