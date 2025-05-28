package com.example.vetdawexam;

import Demo.SQLDataBaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        try (Connection connection = SQLDataBaseManager.getConnection()) {
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos.");
                launch();
            } else {
                System.out.println("Error al conectar con la base de datos.");
                launch();
            }
        } catch (SQLException e) {
            System.out.println("Error al probar la conexión: " + e.getMessage());
            launch();
        }
    }

}