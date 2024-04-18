package com.example.projet_java.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Création d'un élément d'interface utilisateur
        Label label = new Label("Hello, JavaFX!");

        // Création d'une disposition et ajout du label
        StackPane root = new StackPane();
        root.getChildren().add(label);

        // Création de la scène et ajout de la disposition
        Scene scene = new Scene(root, 300, 200);

        // Configuration de la scène primaire et affichage
        primaryStage.setScene(scene);
        primaryStage.setTitle("HelloFX");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}