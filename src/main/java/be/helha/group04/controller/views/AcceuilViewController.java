package be.helha.group04.controller.views;

import be.helha.group04.controller.api.PersonnageController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class AcceuilViewController {

    @FXML
    Label nombrePersonnageLabel;
    @FXML
    Label NombreArmeLabel;

    private PersonnageController personnageController;

    @FXML
    public void initializable() {
       int nombrePersonnages =  personnageController.getAllPersonnage().size();
        String nombrePersonnagesString = String.valueOf(nombrePersonnages);
        nombrePersonnageLabel.setText("il y a " + nombrePersonnagesString + " personnages");
    }
    @FXML
    public void showViewListePersonnage(){

    }
    @FXML
    public void showViewListeArme(){
        //TO DO
    }


}
