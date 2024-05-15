package be.helha.group04.controller.views;

import be.helha.group04.controller.api.PersonnageController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AcceuilViewController implements Initializable {

    @FXML
    Label nombrePersonnageLabel;
    @FXML
    Label NombreArmeLabel;

    private PersonnageController personnageController;

    //constructeur
    public AcceuilViewController() {
        this.personnageController = new PersonnageController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int nombrePersonnages =  personnageController.getAllPersonnage().size();
        String nombrePersonnagesString = String.valueOf(nombrePersonnages);
        nombrePersonnageLabel.setText(nombrePersonnages > 1 ? "il y a " + nombrePersonnagesString + " personnages" : "il y a " + nombrePersonnagesString + " personnage" );
    }

    @FXML
    public void showViewListePersonnage(Stage primaryStage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/list-perso-view.fxml"));
            primaryStage.setTitle("Changer de Vue");
            primaryStage.setScene(new Scene(root, 600, 500));
            primaryStage.show();

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    public void showViewListeArme(Stage primaryStage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/list-arme-view.fxml"));
            primaryStage.setTitle("Changer de Vue");
            primaryStage.setScene(new Scene(root, 600, 500));
            primaryStage.show();

        } catch(IOException e){
            e.printStackTrace();
        }
    }



}
