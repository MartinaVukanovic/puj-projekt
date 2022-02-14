module com.example.pujprojekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;

    opens com.example.pujprojekt to javafx.fxml;
    exports com.example.pujprojekt;
    exports com.example.pujprojekt.controller;
    opens com.example.pujprojekt.controller to javafx.fxml;
    opens com.example.pujprojekt.model to javafx.base;
}