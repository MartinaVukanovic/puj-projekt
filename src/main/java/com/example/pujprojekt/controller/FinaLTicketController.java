package com.example.pujprojekt.controller;

import com.example.pujprojekt.Main;
import com.example.pujprojekt.model.Database;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class FinaLTicketController {

    public static String busName;
    public static Integer price;
    public static String start;
    public static String end;

    @FXML
    Button buy;

    @FXML
    Label labela;

    @FXML
    Label labela1;

    @FXML
    Label labela2;

    @FXML
    Label labela3;

    private Stage stage;


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

    public void goToUserView() throws IOException {
            Main.showWindow(
                    "/userView.fxml",
                    "User View", 566, 336);
        }


        public void funkcija(int bus_fk) throws SQLException {

            Database connectNow = new Database();
            Connection connectDB = connectNow.getConnection();

            String priceSQL = "SELECT * FROM ticket WHERE bus_fk ='" + bus_fk + "'";

            Statement seat = connectDB.createStatement();
            ResultSet queryResult = seat.executeQuery(priceSQL);

            if(queryResult.next()) {
                price = Integer.valueOf(queryResult.getString("price"));
                start = queryResult.getString("start");
                end = queryResult.getString("end");
                labela1.setText(start );
                labela2.setText(end);
                labela3.setText(price + " €");
            }


        }


    public void display(String id,int numberOfSeat, int bus_fk) throws SQLException {

        funkcija(bus_fk);
        Database connectNow = new Database();
        Connection connectDB = connectNow.getConnection();

        String seatNumber = "SELECT name FROM Bus WHERE id ='" + bus_fk + "'";

        Statement seat = connectDB.createStatement();
        ResultSet queryResult = seat.executeQuery(seatNumber);

        if(queryResult.next()){
            busName = queryResult.getString("name");
            labela.setText(busName);
        }

        buy.setOnAction(event -> {

        String updateBusSeat = "UPDATE `busseat` SET `free`='1' WHERE id ='" + id + "'";
        Statement statement1 = null;
        try {
            statement1 = connectDB.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            int resultOfUpdate = statement1.executeUpdate(updateBusSeat);
            System.out.println(resultOfUpdate + "rez");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ticket successfully purchased! ", ButtonType.OK);
            alert.setTitle("Notice");
            alert.setHeaderText("Success!");
            alert.showAndWait();

    });


}
}
