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

/**
 * cette classe est le controller dédié à la page de validation de suppression du personnage
 * @author Clara
 * @see be.helha.group04.controller.views
 */
public class Validation_sup_persoController {

    /**
     * ce label va contenir un message de confirmation pour supprimer le personnage
     */
    @FXML
    Label messageDeConfirmation;

    /**
     * variable qui va contenir l'id du personnage qu'on modifie
     */
    private int id;

    /**
     * une instance du personnageController
     */
    private PersonnageController personnageController;

    /**
     * consctructeur de la classe Validation_sup_persoController
     * initialise l'instance de personnageController
     */
    public Validation_sup_persoController() {
        this.personnageController = new PersonnageController();
    }

    /**
     * cette méthode est appelé avant l'affichage de la page pour
     * pouvoir récupérer l'id du personnage selectionné et afficher le message de confirmation
     * avec un rappel du nom du personnage que l'on souhaite modifié
     * @param id est l'id du personnage que l'on veut supprimer
     */
    public void recupId(int id){
        Personnage personnageInfo = personnageController.getPersonnageById(id);
        this.id = id;
        messageDeConfirmation.setText("voulez-vous confirmer la suppression du personnage " + personnageInfo.getNom() + " ?");
    }

    /**
     * Affiche la vue de la liste des personnages. Cette méthode est appelée lorsqu'il y a un clic
     * sur le bouton confirmer, ce qui confirme la suppression du personnage et envoie les modifications à la db
     * et nous renvoie sur la vue de la liste des personnages.
     * Elle charge la vue FXML correspondante et remplace la scène actuelle par la nouvelle scène.
     * @param event est l'événement qui déclenche l'appel de cette méthode.
     */
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
