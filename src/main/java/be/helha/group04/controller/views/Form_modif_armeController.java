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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur pour le formulaire de modification d'une arme
 * Cette classe gère l'affichage des informations d'une arme à modifier, la validation des modifications,
 * et la navigation vers la liste des armes.
 * @author Hayriye Dogan
 * @see be.helha.group04.controller.views
 */
public class Form_modif_armeController {

    /**
     * Champ de texte pour le nom de l'arme
     */
    @FXML
    private TextField inputNom;

    /**
     * Champ de texte pour les dégâts de l'arme
     */
    @FXML
    private TextField inputDegat;

    /**
     * Label pour afficher les messages d'erreur
     */
    @FXML
    private Label errorMessage;

    /**
     * instance de ArmeController
     */
    private ArmeController armeController;

    /**
     * instance de Arme qui est l'arme selectionnée via son id
     */
    private Arme armeSelectionne;

    /**
     * Constructeur qui initialise le contrôleur avec un ArmeService
     */
    public Form_modif_armeController() {
        ArmeService armeService = new ArmeService();
        this.armeController = new ArmeController(armeService);
    }

    /**
     * Configure les détails de l'arme à modifier en fonction de son identifiant
     * Met à jour les champs de texte avec les informations de l'arme
     * @param id identifiant de l'arme à modifier
     */
    public void setId_arme(int id) {
        this.armeSelectionne = this.armeController.getArmeById(id);
        String nomArmeSelectionne = this.armeSelectionne.getNom();
        int degatArmeSelectionne = this.armeSelectionne.getDegats();
        this.inputNom.setText(nomArmeSelectionne);
        this.inputDegat.setText(degatArmeSelectionne+"");
    }

    /**
     * Gère la confirmation des modifications de l'arme
     * Valide les entrées du formulaire et met à jour l'arme dans le système
     * Redirige ensuite vers la vue de la liste des armes
     * @param actionEvent événement déclenché par la confirmation des modifications
     */
    public void ConfirmerModif(ActionEvent actionEvent) {
        String nom = this.inputNom.getText();
        String degatString = this.inputDegat.getText();
        if(degatString.isEmpty() || nom.isEmpty()) {
            this.errorMessage.setText("Ce formulaire n'est pas complet");
            return;
        }
        try {
            Integer.parseInt(degatString);
        } catch(NumberFormatException e) {
            this.errorMessage.setText("le degat n'est pas une valeur valide (rentrez un chiffre)");
            e.printStackTrace();
        }
        int degat = Integer.parseInt(degatString);
        if(degat<0 || degat >100) {
            this.errorMessage.setText("la valeur du degat n'est pas accepté (0-100)");
            return;
        }

        Arme armeACreer = new Arme(nom, degat);
        this.armeController.updateArme(this.armeSelectionne.getId(), armeACreer);
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
     * Gère l'action pour revenir à la vue de la liste des armes sans enregistrer les modifications
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
