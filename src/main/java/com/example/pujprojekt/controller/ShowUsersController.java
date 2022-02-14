package com.example.pujprojekt.controller;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.pujprojekt.Main;

import com.example.pujprojekt.model.User;
import com.example.pujprojekt.model.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShowUsersController implements Initializable {

    @FXML
    Label labela;

    @FXML
    TextField UserNameTxt;

    @FXML
    TextField UserEmailTxt;

    @FXML
    TextField UserRoleTxt;

    @FXML
    PasswordField UserPasswordTxt;


    @FXML
    TableView UserTbl;

    @FXML
    TableColumn UserID;

    @FXML
    TableColumn UserName;

    @FXML
    TableColumn UserEmail;

    @FXML
    TableColumn UserRole;

    @FXML
    Button addUserBtn;


    User selectedUser = null;


    @FXML
    protected void goToAdminPanel() throws IOException {
        Main.showWindow(
                "/adminPanel.fxml",
                "Admin Panel", 650, 400);
    }

    @FXML
    protected void addUser (){
        String name = this.UserNameTxt.getText();
        String email = this.UserEmailTxt.getText();
        String role = this.UserRoleTxt.getText();
        String password = this.UserPasswordTxt.getText();


        if (name.equals("") || email.equals("") || role.equals("") || password.equals("") ){
            labela.setText("All fields are required!");
        } else
        if (this.selectedUser == null){
            User c = new User();
            c.setName(name);
            c.setEmail(email);
            c.setRole(role);
            c.setPassword(password);
            try {
                c.save();
                this.UserNameTxt.setText("");
                this.UserEmailTxt.setText("");
                this.UserRoleTxt.setText("");
                this.UserPasswordTxt.setText("");
                this.fillUsers();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            this.selectedUser.setName(name);
            this.selectedUser.setRole(role);
            this.selectedUser.setEmail(email);
            this.selectedUser.setPassword(password);
            try {
                this.selectedUser.update();
                this.removeSelection();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.UserID.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.UserName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.UserEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.UserRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        this.fillUsers();
    }

    private void fillUsers(){
        try {
            List<?> usersList = Table.list(User.class);
            ObservableList<?> usersObservableList = FXCollections.observableList(usersList);
            this.UserTbl.setItems(usersObservableList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void deleteUser(){
        if (selectedUser != null){
            try {
                selectedUser.delete();
                this.fillUsers();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            labela.setText("Please select the user you want to delete!");
        }
    }

    @FXML
    protected void selectUser(){
        this.selectedUser = (User) this.UserTbl.getSelectionModel().getSelectedItem();
        this.UserNameTxt.setText(this.selectedUser.getName());
        this.UserEmailTxt.setText(this.selectedUser.getEmail());
        this.UserRoleTxt.setText(this.selectedUser.getRole());
        this.UserPasswordTxt.setText(this.selectedUser.getPassword());
    }

    @FXML
    protected void removeSelection(){
        this.selectedUser = null;
        this.fillUsers();
        this.UserNameTxt.setText("");
        this.UserEmailTxt.setText("");
        this.UserRoleTxt.setText("");
        this.UserPasswordTxt.setText("");
    }
}


