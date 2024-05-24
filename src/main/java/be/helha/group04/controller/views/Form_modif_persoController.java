package be.helha.group04.controller.views;

import be.helha.group04.controller.api.PersonnageController;
import be.helha.group04.personnage.Personnage;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * cette classe est le controller dédié à la page du formulaire de modification du personnage
 * @author Clara
 * @see be.helha.group04.controller.views
 */
public class Form_modif_persoController{

    /**
     * une instance du personnageController
     */
    private PersonnageController personnageController;

    /**
     * ce label va contenir le nom du personnage à modifier
     */
    @FXML
    TextField inputNom;
    /**
     * ce label va contenir les pv du personnage à modifier
     */
    @FXML
    TextField inputPv;
    /**
     * ce label va contenir la mana du personnage à modifier
     */
    @FXML
    TextField inputMana;
    /**
     * ce label va contenir un message d'erreur si le formulaire est pas correctement rempli
     */
    @FXML
    Label errorMessage;

    /**
     * variable qui va contenir l'id du personnage qu'on modifie
     */
    private int id;

    /**
     * consctructeur de la classe DetailPersoController
     * initialise l'instance de personnageController
     */
    public Form_modif_persoController() {
        this.personnageController = new PersonnageController();
    }

    /**
     * cette méthode est appelé avant l'affichage de la page pour
     * pouvoir récupérer les bonnes informations à afficher
     * @param id est l'id du personnage que l'on veut modifier
     */
    public void recupInfoPerso(int id){
        Personnage personnageInfo = personnageController.getPersonnageById(id);
        inputNom.setText(personnageInfo.getNom());
        inputPv.setText(String.valueOf(personnageInfo.getPv()));
        inputMana.setText(String.valueOf(personnageInfo.getMana()));
        this.id = personnageInfo.getId();
    }

    /**
     * Affiche la vue de la liste des personnages. Cette méthode est appelée lorsqu'il y a un clic
     * sur le bouton confirmer, ce qui confirme la modification du personnage et envoie les modifications à la db
     * et nous renvoie sur la vue de la liste des personnages.
     * Elle charge la vue FXML correspondante et remplace la scène actuelle par la nouvelle scène.
     * @param event est l'événement qui déclenche l'appel de cette méthode.
     */
    @FXML
    public void confirmerAjoutPerso(Event event){
        if(formulaireCorrect()){
            Personnage personnageToAdd = new Personnage();
            personnageToAdd.setNom(inputNom.getText());
            personnageToAdd.setPv(Integer.parseInt(inputPv.getText()));
            personnageToAdd.setMana(Integer.parseInt(inputMana.getText()));
            personnageController.updatePersonnage(this.id, personnageToAdd);
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

    /**
     * c'est une fonction qui s'assure que le formulaire n'est pas vide,
     * rempli avec les bons types d'informations ainsi que s'assurer que
     * les pv et mana ne soit pas inferieur à 0 et supérieur à leurs limites réspèctives
     * (1000 pour les pv et 100 pour la mana)
     * @return retourne faux si le formulaire n'est pas correct, retourne vrai si le formulaire est correct
     */
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

    /**
     * Réaffiche la page de la liste des personnages, c'est un retour en arrière sur la page précedente
     * @param event est l'événement qui déclenche l'appel de cette méthode.
     */
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
