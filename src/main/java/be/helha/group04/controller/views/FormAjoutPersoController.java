package be.helha.group04.controller.views;

import be.helha.group04.controller.api.PersonnageController;
import be.helha.group04.personnage.Personnage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.Event;
import javafx.scene.control.Label;

import java.io.IOException;

public class FormAjoutPersoController {

    private PersonnageController personnageController;

    @FXML
    TextField inputNom;
    @FXML
    TextField inputPv;
    @FXML
    TextField inputMana;
    @FXML
    Label errorMessage;

    public FormAjoutPersoController() {
        this.personnageController = new PersonnageController();
    }

    @FXML
    public void confirmerAjoutPerso(Event event){
        if(formulaireCorrect()){
            Personnage personnageToAdd = new Personnage();
            personnageToAdd.setNom(inputNom.getText());
            personnageToAdd.setPv(Integer.parseInt(inputPv.getText()));
            personnageToAdd.setMana(Integer.parseInt(inputMana.getText()));
            personnageController.createPersonnage(personnageToAdd);
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

    public boolean formulaireCorrect(){
        if (inputMana.getText().isEmpty() || inputPv.getText().isEmpty() || inputNom.getText().isEmpty()) {
            errorMessage.setText("Ce formulaire n'est pas complet");
            return false;
        }
        try {
            Integer.parseInt(inputPv.getText());
        } catch (NumberFormatException e) {
            errorMessage.setText("le pv n'est pas une valeur valide (rentrez un chiffre)");
            return false;
        }
        try {
            Integer.parseInt(inputMana.getText());
        } catch (NumberFormatException e) {
            errorMessage.setText("le mana n'est pas une valeur valide (rentrez un chiffre)");
            return false;
        }
        if(Integer.parseInt(inputPv.getText()) > 1000 || Integer.parseInt(inputPv.getText()) < 0 ){
            errorMessage.setText("la valeur du pv n'est pas accepté (0-1000)");
            return false;
        }
        if(Integer.parseInt(inputMana.getText()) > 100 || Integer.parseInt(inputMana.getText()) < 0 ){
            errorMessage.setText("la valeur du mana n'est pas accepté (0-100)");
            return false;
        }
        return true;
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
