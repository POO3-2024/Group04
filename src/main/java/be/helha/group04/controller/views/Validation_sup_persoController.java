package be.helha.group04.controller.views;

import be.helha.group04.controller.api.PersonnageController;
import be.helha.group04.personnage.Personnage;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Validation_sup_persoController {

    @FXML
    Label messageDeConfirmation;
    private int id;

    private PersonnageController personnageController;

    public Validation_sup_persoController() {
        this.personnageController = new PersonnageController();
    }

    public void recupId(int id){
        Personnage personnageInfo = personnageController.getPersonnageById(id);
        this.id = id;
        messageDeConfirmation.setText("voulez-vous confirmer la suppression du personnage " + personnageInfo.getNom() + " ?");
    }

    @FXML
    public void confirmerSuppPerso(Event event){
            personnageController.deletePersonnageById(this.id);
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
