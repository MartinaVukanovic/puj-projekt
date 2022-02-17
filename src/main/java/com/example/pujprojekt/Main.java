package com.example.pujprojekt;

import com.example.pujprojekt.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    private static Stage primaryStage;


    @Override
    public void start(Stage stage) throws IOException {

        try {
/*
            Parent root = FXMLLoader.load(getClass().getResource("/adminPanel.fxml"));
            stage.setTitle("Register");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
*/
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        Main.primaryStage = stage;
        Main.showWindow(
                "/Register.fxml",
                "Register", 480, 300);
    }

    public static void showWindow(String viewName, String title, int w, int h) throws IOException {
        FXMLLoader root = new FXMLLoader(Main.class.getResource(viewName));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root.load(), w, h));
        primaryStage.show();
    }

    public static void main(String[] args) {

        try {

/*
            Table.create(Bus.class);
            Table.create(BusSeat.class);

            Table.create(Ticket.class);


            Table.create(User_Ticket.class);

        } catch (SQLException e) {
            System.out.println(e.getMessage());

*/
            launch();
        } finally {

        }


    }
}


