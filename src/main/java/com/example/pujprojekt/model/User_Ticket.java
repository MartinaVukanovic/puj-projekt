package com.example.pujprojekt.model;

public class User_Ticket extends Table{

    @Entity(type="INTEGER", size=30, primary = true)
    int id;

    @Entity(type="INTEGER", size=32)
    @ForeignKey(table = "Ticket", attribute = "id")
    int ticket_fk;

    @Entity(type="INTEGER", size=32)
    @ForeignKey(table = "User", attribute = "id")
    int user_fk;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Ticket getTicket_fk() throws Exception{
        return (Ticket) Table.get(Ticket.class, ticket_fk);
    }

    public void setTicket_fk(int ticket_fk) {
        this.ticket_fk = ticket_fk;
    }

    public User getUser_fk() throws Exception{
        return (User) Table.get(User.class, user_fk);
    }

    public void setUser_fk(int user_fk) {
        this.user_fk = user_fk;
    }
}
