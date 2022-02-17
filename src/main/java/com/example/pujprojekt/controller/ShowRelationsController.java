package com.example.pujprojekt.controller;



import com.example.pujprojekt.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.pujprojekt.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShowRelationsController implements Initializable {



    @FXML
    Label labela;

    @FXML
    TextField RelationStartTxt;

    @FXML
    TextField RelationEndTxt;

    @FXML
    TextField RelationPriceTxt;;

    @FXML
    TextField RelationBusCompanyTxt;

    @FXML
    ComboBox <String> combo;


    @FXML
    TableView UserTbl;

    @FXML
    TableView UserTbl1;

    @FXML
    TableColumn RelationID;

    @FXML
    TableColumn BusID;

    @FXML
    TableColumn BusName;

    @FXML
    TableColumn RelationStart;

    @FXML
    TableColumn RelationEnd;

    @FXML
    TableColumn RelationPrice;

    @FXML
    TableColumn RelationBusCompany;


    @FXML
    Button addUserBtn;
    public Connection bazaLink;

    public Connection getConnection(){

        String bazaIme = "puj-projekt";
        String bazaKorisnik = "root";
        String bazaLozinka = "";
        String url = "jdbc:mysql://localhost/" + bazaIme;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            bazaLink = DriverManager.getConnection(url, bazaKorisnik, bazaLozinka);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return bazaLink;
    }

    Ticket selectedUser = null;


    @FXML
    protected void goToAdminPanel() throws IOException {
        Main.showWindow(
                "/adminPanel.fxml",
                "Admin Panel", 655, 480);
    }

    @FXML
    protected void addUser (){
        String start = this.RelationStartTxt.getText();
        String end = this.RelationEndTxt.getText();
        String price = this.RelationPriceTxt.getText();
        String bus_fk = this.RelationBusCompanyTxt.getText();


        if (start.equals("") || end.equals("") ){
            labela.setText("All fields are required!");
        } else
        if (this.selectedUser == null){
            Ticket c = new Ticket();
            c.setStart(start);
            c.setEnd(end);
            c.setPrice(Integer.parseInt(price));
            c.setBus_fk(Integer.parseInt(bus_fk));
            try {
                c.save();
                this.RelationStartTxt.setText("");
                this.RelationEndTxt.setText("");
                this.RelationBusCompanyTxt.setText("");
                this.RelationPriceTxt.setText("");
                this.fillUsers();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            this.selectedUser.setStart(start);
            this.selectedUser.setEnd(end);
            this.selectedUser.setPrice(Integer.parseInt(price));
            this.selectedUser.setBus_fk(Integer.parseInt(bus_fk));
            try {
                this.selectedUser.update();
                this.removeSelection();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    ArrayList <String> lista = new ArrayList<String>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.RelationStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        this.RelationEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        this.RelationPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        this.RelationBusCompany.setCellValueFactory(new PropertyValueFactory<>("bus_fk"));


        this.BusID.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.BusName.setCellValueFactory(new PropertyValueFactory<>("name"));

        this.fillUsers();
        this.fillBuses();

    }



    private void fillUsers(){
        try {
            List<?> usersList = Table.listTicket(Ticket.class);
            ObservableList<?> usersObservableList = FXCollections.observableList(usersList);
            this.UserTbl.setItems(usersObservableList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void fillBuses(){
        try {
            List<?> usersList = Table.list(Bus.class);
            ObservableList<?> usersObservableList = FXCollections.observableList(usersList);
            this.UserTbl1.setItems(usersObservableList);
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
            labela.setText("Please select the relation you want to delete!");
        }
    }

    @FXML
    protected void selectUser() throws Exception {
        this.selectedUser = (Ticket) this.UserTbl.getSelectionModel().getSelectedItem();
        this.RelationStartTxt.setText(this.selectedUser.getStart());
        this.RelationEndTxt.setText(""+this.selectedUser.getEnd());
        this.RelationPriceTxt.setText(""+this.selectedUser.getPrice());
        this.RelationBusCompanyTxt.setText(""+this.selectedUser.getBus_fk());
    }

    @FXML
    protected void removeSelection(){
        this.selectedUser = null;
        this.fillUsers();
        this.RelationStartTxt.setText("");
        this.RelationEndTxt.setText("");
        this.RelationBusCompanyTxt.setText("");
        this.RelationPriceTxt.setText("");
    }
}


