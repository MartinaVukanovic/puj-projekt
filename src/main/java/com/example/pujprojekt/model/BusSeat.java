package com.example.pujprojekt.model;

public class BusSeat extends Table{
    @Entity(type="INTEGER", size=30, primary = true)
    int id;


    @Entity(type="INTEGER", size=30)
    int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Entity(type="INTEGER", size=30)
    int free;

    @Entity(type="INTEGER", size=30)
    @ForeignKey(table = "Bus", attribute = "id")
    int bus_fk;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public Bus getBus_fk() throws Exception {
        return (Bus) Table.get(Bus.class, bus_fk);
    }

    public void setBus_fk(int bus_fk) {
        this.bus_fk = bus_fk;
    }


}
