package com.example.pujprojekt.controller;

import com.example.pujprojekt.Main;
import com.example.pujprojekt.model.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.*;


public class AdminPanelController {

    @FXML
    Label labela;

    @FXML
    Label adminName;

    @FXML
    Label numberOfSignUps;

    @FXML
    Label numberOfRelations;

    @FXML
    Label numberOfBuses;

    @FXML
    Button button;

    public void display(String username) {
        adminName.setText(""+username);
    }


    @FXML
    protected void GoToLogin() throws IOException {
        Main.showWindow(
                "/login.fxml",
                "Log in", 480, 300);
    }

    @FXML
    protected void goToAllUsers() throws IOException {
        Main.showWindow(
                "/showUsers.fxml",
                "all users", 650, 355);
    }

    @FXML
    protected void goToAllBuses() throws IOException {
        Main.showWindow(
                "/showBuses.fxml",
                "all buses", 630, 295);
    }

    @FXML
    protected void goToAllRelations() throws IOException {
        Main.showWindow(
                "/showRelations.fxml",
                "all relations", 680, 420);
    }


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

    public void show() throws SQLException {


        Database connectNow = new Database();
        Connection connectDB = connectNow.getConnection();



        String countUsers = "SELECT COUNT(*) FROM User";

        Statement BusesStatement = connectDB.createStatement();
        ResultSet countBuses_result = BusesStatement.executeQuery(countUsers);

        int nUsers = 0;
        while(countBuses_result.next()){
            nUsers = (countBuses_result.getInt(1));
        }
        numberOfSignUps.setText(""+nUsers);



        String countBuses = "SELECT COUNT(id) FROM Bus";

        Statement UsersStatement = connectDB.createStatement();
        ResultSet countUsers_result = UsersStatement.executeQuery(countBuses);

        int nBuses = 0;
        while(countUsers_result.next()){
            nBuses = (countUsers_result.getInt(1));
        }
        numberOfBuses.setText(""+nBuses);



        String countRelation = "SELECT COUNT(id) FROM Ticket";

        Statement RelationStatement = connectDB.createStatement();
        ResultSet countRelations_result = RelationStatement.executeQuery(countRelation);

        int nRelations = 0;
        while(countRelations_result.next()){
            nRelations = (countRelations_result.getInt(1));
        }
        numberOfRelations.setText(""+nRelations);



    }





    }




