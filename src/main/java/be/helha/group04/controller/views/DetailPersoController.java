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

/**
 * cette classe reprèsente le controller de la vue dédié à la page de détail d'un personnage
 * @author Clara
 * @see be.helha.group04.controller.views
 */
public class DetailPersoController {

    /**
     * ce label reprèsente l'id du personnage
     */
    @FXML
    Label id_perso;
    /**
     * ce label reprèsente le nom du personnage
     */
    @FXML
    Label nom_perso;
    /**
     * ce label repèsente les pv du personnage
     */

    @FXML
    Label pv;
    /**
     * ce label reprèsente la mana du personnage
     */
    @FXML
    Label mana;

    /**
     * une instance de personnageController
     */
    private PersonnageController personnageController;

    /**
     * consctructeur de la classe DetailPersoController
     * initialise l'instance de personnageController
     */
    public DetailPersoController() {
        this.personnageController = new PersonnageController();
    }

    /**
     * cette méthode est appelé avant l'affichage de la page pour
     * pouvoir récupérer les bonnes informations à afficher
     * @param id est l'id du personnage que l'on veut afficher les détails
     */
    public void setId_perso(int id){
        Personnage personnageInfo = personnageController.getPersonnageById(id);
        id_perso.setText(String.valueOf(personnageInfo.getId()));
        nom_perso.setText(personnageInfo.getNom());
        pv.setText(String.valueOf(personnageInfo.getPv()));
        mana.setText(String.valueOf(personnageInfo.getMana()));
    }


    /**
     * Réaffiche la page de détails des personnages, c'est un retour en arrière sur la page précedentes
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
