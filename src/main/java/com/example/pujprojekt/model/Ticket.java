package com.example.pujprojekt.model;

public class Ticket extends Table {

    @Entity(type="INTEGER", size=32, primary = true)
    int id;

    @Entity(type="VARCHAR", size=50)
    String Start;

    @Entity(type="VARCHAR", size=50)
    String End;

    @Entity(type="INTEGER", size=32)
    int Price;

    @Entity(type="INTEGER", size=30)
    @ForeignKey(table = "Bus", attribute = "id")
    int bus_fk;

    @Entity(type="INTEGER", size=32)
    @ForeignKey(table = "User", attribute = "id")
    int user_fk;

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public User getUser_fk() throws Exception {
        return (User) Table.get(User.class, bus_fk);
    }

    public void setUser_fk(int user_fk) {
        this.user_fk = user_fk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getEnd() {
        return End;
    }

    public void setEnd(String end) {
        End = end;
    }

    public int getBus_fk() throws Exception {
        return bus_fk;
    }

    public void setBus_fk(int bus_fk) {
        this.bus_fk = bus_fk;
    }


}
