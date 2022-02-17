package com.example.pujprojekt.model;

public class User extends Table {

    @Entity(type = "INTEGER", size = 32, primary = true)
    int id;

    @Entity(type="VARCHAR", size=50, isnull = false)
    String name;

    @Entity(type="VARCHAR", size=50, isnull = false)
    String email;

    @Entity(type="VARCHAR", size=50, isnull = false)
    String password;

    @Entity(type="VARCHAR", size=50, isnull = false)
    String role;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
