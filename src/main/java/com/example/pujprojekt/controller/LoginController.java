package com.example.pujprojekt.controller;



import com.example.pujprojekt.Main;
import com.example.pujprojekt.model.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {

    @FXML
    private Label errorMsg;

    @FXML
    private TextField UserEmail;

    @FXML
    private PasswordField UserPassword;

    @FXML
    private Button loginButton;


    @FXML
    protected void GoToRegister() throws IOException {
        Main.showWindow(
                "/register.fxml",
                "Register", 480, 300);
    }


    public static String email;
    public static String username;
    public static String password;
    public static String role;
    private Stage stage;
    private Scene scene;
    private Parent root;



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
    protected void Login(ActionEvent event) throws IOException {


        if(UserEmail.getText().isBlank() == false && UserPassword.getText().isBlank() == false){
            email = UserEmail.getText();
            password = UserPassword.getText();
            validateLogin();
            if(role.equals("admin")) {
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminPanel.fxml"));
                    root = loader.load();

                    AdminPanelController scene2Controller = loader.getController();
                    scene2Controller.display(username);

                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();


                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if(!role.equals("admin")) {
                try {


                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/userView.fxml"));
                    root = loader.load();

                    UserViewController scene2Controller = loader.getController();
                    scene2Controller.display(username);

                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

        }
        else{
            errorMsg.setText("All fields are required!");
        }

    }



    public void validateLogin(){


        Database connectNow = new Database();
        Connection connectDB = connectNow.getConnection();


        String verfyLogin = "SELECT * FROM User WHERE email = '"+ UserEmail.getText() + "' AND password = '" + UserPassword.getText() + "'";

        try{

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verfyLogin);


            if(queryResult.next()){
                errorMsg.setText("Login successful!");

                role = queryResult.getString("role");
                username = queryResult.getString("name");

            } else {
                errorMsg.setText("Login failed! Please try again!");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }




}

