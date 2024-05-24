package be.helha.group04.controller.views;

import be.helha.group04.arme.Arme;
import be.helha.group04.controller.api.ArmeController;
import be.helha.group04.modele.service.ArmeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur pour la validation de la suppression d'une arme
 * Cette classe gère la confirmation de la suppression d'une arme et la navigation vers la liste des armes
 * @author Hayriye Dogan
 * @see be.helha.group04.controller.views
 */
public class Validation_sup_armeController {
    /**
     * Label pour afficher le message de confirmation de suppression
     */
    @FXML
    private Label messageDeConfirmation;

    /**
     * instance de ArmeController
     */
    private ArmeController armeController;

    /**
     * instance de Arme qui sera l'arme selectionnée pour supprimer
     */
    private Arme armeSelectionne;

    /**
     * Constructeur qui initialise le contrôleur avec un ArmeService
     */
    public Validation_sup_armeController() {
        ArmeService armeService = new ArmeService();
        this.armeController = new ArmeController(armeService);
    }

    /**
     * Gère la confirmation de la suppression de l'arme sélectionnée
     * Supprime l'arme en utilisant son identifiant et redirige vers la vue de la liste des armes
     * @param actionEvent événement déclenché par la confirmation de la suppression
     */
    public void ConfirmerSuppression(ActionEvent actionEvent) {
        this.armeController.deleteArmeById(this.armeSelectionne.getId());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/list-arme-view.fxml"));
            Parent newRoot = loader.load();

            Stage stage = (Stage) ((Parent) actionEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(newRoot));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Configure l'arme à supprimer en fonction de son identifiant et met à jour le message de confirmation
     * @param id identifiant de l'arme à supprimer
     */
    public void setId_arme(int id) {
        this.armeSelectionne = this.armeController.getArmeById(id);
        this.messageDeConfirmation.setText("voulez-vous confirmer la suppression de l'arme " + this.armeSelectionne.getNom() +" ?");
    }

    /**
     * Gère l'action pour revenir à la vue de la liste des armes sans supprimer l'arme.
     * @param actionEvent l'événement déclenché par l'action de retour à la liste des armes
     */
    public void RetourListArme(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/list-arme-view.fxml"));
            Parent newRoot = loader.load();

            Stage stage = (Stage) ((Parent) actionEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(newRoot));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
