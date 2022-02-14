package com.example.pujprojekt.controller;

import com.example.pujprojekt.Main;
import com.example.pujprojekt.model.Database;
import com.example.pujprojekt.model.Bus;
import com.example.pujprojekt.model.BusSeat;
import com.example.pujprojekt.model.Table;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BusLayoutController {
    public static Integer id_BusSeat;
    public static Integer numberOfSeat;
    public static Integer capacity_Bus;
    public static Integer free;

    @FXML
    private HBox container2;

    @FXML
    private HBox container21;

    @FXML
    private HBox container22;
    @FXML
    private HBox container221;

    @FXML
    private Button botun;

    @FXML
    private List<Button> labels ;

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
            e.printStackTrace();
            e.getCause();
        }
        return bazaLink;
    }

    public void add() throws SQLException {
        make();
        botun.setVisible(false);
    }


    public void make() throws SQLException {


        Database connectNow = new Database();
        Connection connectDB = connectNow.getConnection();


        String findBus = "SELECT * FROM Bus WHERE id = '1'";
        Statement BusStatement = connectDB.createStatement();
        ResultSet BusResult = BusStatement.executeQuery(findBus);

        if(BusResult.next()) {
            capacity_Bus = BusResult.getInt("capacity");
            capacity_Bus = capacity_Bus-1;


        String findBusSeat = "SELECT * FROM BusSeat WHERE bus_fk = '1'";
        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(findBusSeat);


        // --------------------------------------------------------------------------------//


        for (int i = 1; i <= (capacity_Bus/4)+1; i++) {
            if(queryResult.next()) {
                id_BusSeat = queryResult.getInt("id");
                numberOfSeat = queryResult.getInt("number");
                free = queryResult.getInt("free");

        labels = new ArrayList<>();

            Button label = new Button(""+numberOfSeat);
                label.setId(""+i);
                String id = label.getId();

                if(free==0){
                    label.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                    label.setStyle("-fx-font-size: 12px;");
                    label.setStyle("-fx-min-width: 42px; -fx-text-fill: white; ");

                } else {
                    label.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                    label.setDisable(true);
                    label.setStyle("-fx-font-size: 12px;");
                    label.setStyle("-fx-min-width: 42px; -fx-text-fill: white;");
                }


                label.setOnAction(event -> {
                    String updateBusSeat = "UPDATE `busseat` SET `free`='1' WHERE id ='" +  id +"'";
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

                    System.out.println(id);

                });


            container2.setMargin(label, new Insets(5));
            labels.add(label);
            container2.getChildren().add(label);

             }
        }


            // ------------------------------------------------------------------------------------//



            for (int j = capacity_Bus/4; j <= (capacity_Bus/4)*2; j++){

                if(queryResult.next()) {
                    id_BusSeat = queryResult.getInt("id");
                    numberOfSeat = queryResult.getInt("number");
                    free = queryResult.getInt("free");

                    labels = new ArrayList<>();

                    Button label = new Button(""+numberOfSeat);
                    label.setId(""+(j+2));
                    String id = label.getId();

                    if(free==0){
                        label.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                        label.setStyle("-fx-font-size: 12px;");
                        label.setStyle("-fx-min-width: 42px; -fx-text-fill: white; ");

                    } else {
                        label.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                        label.setDisable(true);
                        label.setStyle("-fx-font-size: 12px;");
                        label.setStyle("-fx-min-width: 42px; -fx-text-fill: white;");
                    }

                    label.setOnAction(event -> {
                        String updateBusSeat = "UPDATE `busseat` SET `free`='1' WHERE id ='" +  id +"'";
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

                        System.out.println(id);

                    });



                    container21.setMargin(label, new Insets(5));
                    labels.add(label);
                    container21.getChildren().add(label);

                }
            }


            for (int f = capacity_Bus/4; f <= (capacity_Bus/4)*2; f++){
                if(queryResult.next()) {
                    id_BusSeat = queryResult.getInt("id");
                    numberOfSeat = queryResult.getInt("number");
                    free = queryResult.getInt("free");

                    labels = new ArrayList<>();

                    Button label = new Button(""+ numberOfSeat);
                    label.setId(""+id_BusSeat);
                    String id = label.getId();


                    if(free==0){
                        label.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                        label.setStyle("-fx-font-size: 12px;");
                        label.setStyle("-fx-min-width: 42px; -fx-text-fill: white; ");

                    } else {
                        label.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                        label.setDisable(true);
                        label.setStyle("-fx-font-size: 12px;");
                        label.setStyle("-fx-min-width: 42px; -fx-text-fill: white;");
                    }

                    label.setOnAction(event -> {
                        String updateBusSeat = "UPDATE `busseat` SET `free`='1' WHERE id ='" +  id +"'";
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

                        System.out.println(id);

                    });


                    container22.setMargin(label, new Insets(5));
                    labels.add(label);
                    container22.getChildren().add(label);

                }
            }
            for (int m = capacity_Bus/4; m <= capacity_Bus; m++){
                if(queryResult.next()) {
                    id_BusSeat = queryResult.getInt("id");
                    numberOfSeat = queryResult.getInt("number");
                    free = queryResult.getInt("free");

                    labels = new ArrayList<>();

                    Button label = new Button(""+numberOfSeat);
                    label.setId(""+id_BusSeat);
                    String id = label.getId();


                    if(free==0){
                        label.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                        label.setStyle("-fx-font-size: 12px;");
                        label.setStyle("-fx-min-width: 42px; -fx-text-fill: white; ");

                    } else {
                        label.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                        label.setDisable(true);
                        label.setStyle("-fx-font-size: 12px;");
                        label.setStyle("-fx-min-width: 42px; -fx-text-fill: white;");
                    }

                    label.setOnAction(event -> {
                        String updateBusSeat = "UPDATE `busseat` SET `free`='1' WHERE id ='" +  id +"'";
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

                        System.out.println(id);

                    });


                    container221.setMargin(label, new Insets(5));
                    labels.add(label);
                    container221.getChildren().add(label);

                }}
        }
    }
}
