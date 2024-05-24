package be.helha.group04.controller.views;

import be.helha.group04.arme.Arme;
import be.helha.group04.controller.api.ArmeController;
import be.helha.group04.modele.service.ArmeService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Contrôleur pour la vue de détail d'une arme
 * Cette classe gère l'affichage des détails d'une arme sélectionnée.
 * @author Hayriye Dogan
 * @see be.helha.group04.controller.views
 */
public class DetailArmeController {

    /**
     * instance de Arme qui est l'arme selectionnée via son id
     */
    private Arme armeSelectionne;

    /**
     * Label pour afficher l'identifiant de l'arme
     */
    @FXML
    private Label id_arme;

    /**
     * Label pour afficher le nom de l'arme
     */
    @FXML
    private Label nom_arme;

    /**
     * Label pour afficher les dégâts de l'arme
     */
    @FXML
    private Label degats;

    /**
     * instance de ArmeController
     */
    private ArmeController armeController;

    /**
     * Constructeur qui initialise le contrôleur avec un ArmeService
     */
    public DetailArmeController() {
        ArmeService armeService = new ArmeService();
        this.armeController = new ArmeController(armeService);
    }

    /**
     * Définit l'arme à afficher en détail.
     * Met à jour les labels avec les informations de l'arme sélectionnée via l'id
     * @param id identifiant de l'arme à afficher en détail
     */
    public void setId_arme(int id) {
        this.armeSelectionne = this.armeController.getArmeById(id);
        int idArmeSelectionne = this.armeSelectionne.getId();
        String nomArmeSelectionne = this.armeSelectionne.getNom();
        int degatArmeSelectionne = this.armeSelectionne.getDegats();
        this.id_arme.setText(idArmeSelectionne+"");
        this.nom_arme.setText(nomArmeSelectionne);
        this.degats.setText(degatArmeSelectionne+"");
    }

    /**
     * Retourne à la vue de la liste des armes
     * @param actionEvent événement déclenché par l'action de retour à la liste des armes
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
