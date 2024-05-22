package be.helha.group04.controller.views;

import be.helha.group04.controller.api.PersonnageController;
import javafx.event.Event;
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
    public void showViewListePersonnage(Event event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/list-perso-view.fxml"));
            Parent newRoot = loader.load();

            // Obtenir la scène actuelle
            Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();

            // Changer la scène
            stage.setScene(new Scene(newRoot));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void showViewListeArme(Event event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/list-arme-view.fxml"));
            Parent newRoot = loader.load();

            // Obtenir la scène actuelle
            Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();

            // Changer la scène
            stage.setScene(new Scene(newRoot));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }



}