package com.example.pujprojekt.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.pujprojekt.Main;
import com.example.pujprojekt.model.User;
import com.example.pujprojekt.model.Table;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {

    @FXML
    private Label errorMsg;

    @FXML
    private TextField UserName;

    @FXML
    private TextField UserEmail;

    @FXML
    private PasswordField UserPassword;

    @FXML
    protected void GoToLogin() throws IOException {
        Main.showWindow(
                "/login.fxml",
                "Log in", 480, 300);
    }


    @FXML
    protected void Register() {
        String username = this.UserName.getText().toString();
        String email = this.UserEmail.getText().toString();
        String password = this.UserPassword.getText().toString();

        if (username.equals("") || email.equals("") || password.equals("") ) {
            errorMsg.setText("All fields are required!");
        } else if (password.length() < 8){
            errorMsg.setText("A minimum 8 characters password required!");
        } else if (!(email.contains("@")) || !(email.contains("."))) {
            errorMsg.setText("Please put valid email address!");
            } else {
            User n = new User();
            n.setName(username);
            n.setEmail(email);
            n.setPassword(password);
            errorMsg.setText("User registered successfully!");
            try {
                n.save();
            } catch (Exception e) {
                errorMsg.setText("User with that email already exists.");
            }

        }
        }
    }
