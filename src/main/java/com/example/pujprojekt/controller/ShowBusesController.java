package com.example.pujprojekt.controller;


import com.example.pujprojekt.model.Bus;
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

public class ShowBusesController implements Initializable {

    @FXML
    Label labela;

    @FXML
    TextField BusNameTxt;

    @FXML
    TextField BusCapacityTxt;


    @FXML
    TableView UserTbl;

    @FXML
    TableColumn BusID;

    @FXML
    TableColumn BusName;

    @FXML
    TableColumn BusCapacity;


    @FXML
    Button addUserBtn;


    Bus selectedUser = null;


    @FXML
    protected void goToAdminPanel() throws IOException {
        Main.showWindow(
                "/adminPanel.fxml",
                "Admin Panel", 655, 480);
    }

    @FXML
    protected void addUser (){
        String name = this.BusNameTxt.getText();
        String capacity = this.BusCapacityTxt.getText();


        if (name.equals("") || capacity.equals("") ){
            labela.setText("All fields are required!");
        } else
        if (this.selectedUser == null){
            Bus c = new Bus();
            c.setName(name);
            c.setCapacity(Integer.parseInt(capacity));
            try {
                c.save();
                this.BusNameTxt.setText("");
                this.BusCapacityTxt.setText("");
                this.fillUsers();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            this.selectedUser.setName(name);
            this.selectedUser.setCapacity(Integer.parseInt(capacity));
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
        this.BusID.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.BusName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.BusCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        this.fillUsers();
    }

    private void fillUsers(){
        try {
            List<?> usersList = Table.list(Bus.class);
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
            labela.setText("Please select the bus you want to delete!");
        }
    }

    @FXML
    protected void selectUser(){
        this.selectedUser = (Bus) this.UserTbl.getSelectionModel().getSelectedItem();
        this.BusNameTxt.setText(this.selectedUser.getName());
        this.BusCapacityTxt.setText(""+this.selectedUser.getCapacity());
    }

    @FXML
    protected void removeSelection(){
        this.selectedUser = null;
        this.fillUsers();
        this.BusNameTxt.setText("");
        this.BusCapacityTxt.setText("");
    }
}


