package com.example.pujprojekt.controller;



import com.example.pujprojekt.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import com.example.pujprojekt.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserViewController implements Initializable {

    @FXML
    Label labela;

    @FXML
    Label welcome;

    @FXML
    TextField RelationStartTxt;

    @FXML
    TextField RelationEndTxt;

    @FXML
    TextField RelationPriceTxt;;

    @FXML
    TextField RelationBusCompanyTxt;


    @FXML
    TableView UserTbl;

    @FXML
    TableColumn RelationID;

    @FXML
    TableColumn RelationStart;

    @FXML
    TableColumn RelationEnd;

    @FXML
    TableColumn RelationPrice;

    @FXML
    TableColumn RelationBusCompany;

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    Button addUserBtn;


    Ticket selectedUser = null;


    public Connection bazaLink;

    public Connection getConnection(){
        //Podaci o bazi podataka:
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


    @FXML
    protected void goToLogin() throws IOException {
        Main.showWindow(
                "/login.fxml",
                "Login", 480, 300);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.RelationStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        this.RelationEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        this.RelationPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        this.fillUsers();


    }

// funkcija iz Table.java

    private void fillUsers(){
        try {
            List<?> usersList = Table.listTicket(Ticket.class);
            ObservableList<?> usersObservableList = FXCollections.observableList(usersList);
            this.UserTbl.setItems(usersObservableList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @FXML
    protected void selectUser() throws Exception {
        this.selectedUser = (Ticket) this.UserTbl.getSelectionModel().getSelectedItem();
       // this.RelationStartTxt.setText(this.selectedUser.getStart());
        int id = this.selectedUser.getId();
        String start = this.selectedUser.getStart();
        String end = this.selectedUser.getEnd();
        int price = this.selectedUser.getPrice();
        int bus_fk = this.selectedUser.getBus_fk();


    }

    @FXML
    protected void goToBusLayout(ActionEvent event) throws Exception {
     try {
         this.selectedUser = (Ticket) this.UserTbl.getSelectionModel().getSelectedItem();
         int bus_fk = this.selectedUser.getBus_fk();
         String start = this.selectedUser.getStart();
         String end = this.selectedUser.getEnd();
         int price = this.selectedUser.getPrice();

         FXMLLoader loader = new FXMLLoader(getClass().getResource("/BusLayout.fxml"));
         root = loader.load();

         BusLayoutController scene2Controller = loader.getController();
         scene2Controller.display(bus_fk, start, end, price);

         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
     } catch (Exception e) {
         labela.setText("Please select one relation!");
     }

        }

    @FXML
    protected void removeSelection(){
        this.selectedUser = null;
        this.fillUsers();
    }

    public void display(String username) {
        welcome.setText("Welcome " + username + " !");
    }
}
