package com.example.pujprojekt.model;

import javafx.scene.control.TextField;

public class Bus extends Table{
    @Entity(type="INTEGER", size=30, primary = true)
    int id;

    @Entity(type="INTEGER", size=100)
    int capacity;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Entity(type="VARCHAR", size=25)
    String name;


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

}
