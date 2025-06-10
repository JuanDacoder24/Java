package com.example.examen_recuperacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class VetApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VetApp.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        try (Connection connection = SQLDataBaseManager.getConnection()) {
//            if (connection != null) {
//                System.out.println("Conexión exitosa a la base de datos.");
//                launch(args);
//            } else {
//                System.out.println("Error al conectar con la base de datos.");
//            }    } catch (SQLException e) {
//            System.out.println("Error al probar la conexión: " + e.getMessage());
//        }}
        SQLAccesVetDaw vetDaw = new SQLAccesVetDaw();
        for (Mascota m : vetDaw.getAllMascotas()){
            System.out.println(m);
        }

        Mascota mascota = vetDaw.getMascotasByPasaporte("P00000001");
        System.out.println("filtro de mascota");
        System.out.println(mascota);
        launch(args);
    }}
