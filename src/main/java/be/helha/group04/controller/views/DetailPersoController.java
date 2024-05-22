package be.helha.group04.controller.views;
import be.helha.group04.controller.api.PersonnageController;
import be.helha.group04.personnage.Personnage;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DetailPersoController {

    @FXML
    Label id_perso;

    @FXML
    Label nom_perso;

    @FXML
    Label pv;

    @FXML
    Label mana;

    private PersonnageController personnageController;


    public DetailPersoController() {
        this.personnageController = new PersonnageController();
    }

    public void setId_perso(int id){
        Personnage personnageInfo = personnageController.getPersonnageById(id);
        id_perso.setText(String.valueOf(personnageInfo.getId()));
        nom_perso.setText(personnageInfo.getNom());
        pv.setText(String.valueOf(personnageInfo.getPv()));
        mana.setText(String.valueOf(personnageInfo.getMana()));
    }


    @FXML
    public void retourListPerso(Event event){
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
}
