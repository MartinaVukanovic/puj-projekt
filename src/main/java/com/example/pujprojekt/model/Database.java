package com.example.pujprojekt.model;


import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RepetedEntity.class)
@interface Entity {
    String type();
    int size() default 0;
    boolean primary() default false;
    boolean isnull() default true;
}

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RepetedForeignKey.class)
@interface ForeignKey {
    String table();
    String attribute();
}

@Retention(RetentionPolicy.RUNTIME)
@interface RepetedEntity{
    Entity[] value();
}

@Retention(RetentionPolicy.RUNTIME)
@interface RepetedForeignKey{
    ForeignKey[] value();
}

public class Database{

    private static String hostname = "localhost";
    private static String user = "root";
    private static String password = "";
    private static String database = "puj-projekt";

    public static Connection CONNECTION = null;

    static {
        try {
            CONNECTION = DriverManager.getConnection(
                    "jdbc:mysql://"+hostname+":3306/"+database, user, password
            );

        } catch (SQLException e) {
            System.out.println("Nismo se uspjeli spojiti na bazu podataka: " + e.getMessage());
        }
    }


    public Connection getConnection() {
        Connection bazaLink = null;
        //Podaci o bazi podataka:
        String bazaIme = "puj-projekt";
        String bazaKorisnik = "root";
        String bazaLozinka = "";
        String url = "jdbc:mysql://localhost/" + bazaIme;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            bazaLink = DriverManager.getConnection(url, bazaKorisnik, bazaLozinka);
        }catch(Exception e){
            System.out.println("Nismo se uspjeli spojiti na bazu podataka: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
        return bazaLink;
    }
}