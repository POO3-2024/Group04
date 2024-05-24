package be.helha.group04.controller.views;

import be.helha.group04.arme.Arme;
import be.helha.group04.controller.api.ArmeController;
import be.helha.group04.modele.service.ArmeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur pour le formulaire d'ajout d'une nouvelle arme
 * Cette classe gère la soumission du formulaire et
 * redirige vers la vue de la liste des armes en cas de soumission réussie
 * @author Hayriye Dogan
 * @see be.helha.group04.controller.views
 */
public class FormAjoutArmeController {
    /**
     * Champ de texte pour entrer le nom de l'arme
     */
    @FXML
    TextField inputNom;

    /**
     * Champ de texte pour entrer le dégât infligé par l'arme
     */
    @FXML
    TextField inputDegat;

    /**
     * Label pour afficher les messages d'erreur
     */
    @FXML
    Label errorMessage;

    /**
     * instance de ArmeController
     */
    private ArmeController armeController;

    /**
     * Initialise le contrôleur ArmeController avec un ArmeService entré en paramètre
     */
    public FormAjoutArmeController() {
        ArmeService armeService = new ArmeService();
        this.armeController = new ArmeController(armeService);
    }

    /**
     * Gère la soumission du formulaire pour ajouter une nouvelle arme.
     * Valide les entrées et crée un nouvel objet Arme si les entrées sont valides
     * Redirige vers la vue de la liste des armes en cas de soumission réussie
     * @param actionEvent l'événement déclenché par la soumission du formulaire (via confirmation)
     */
    public void ConfirmerAjout(ActionEvent actionEvent) {
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
        this.armeController.createArme(armeACreer);
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
     * Gère l'action pour revenir à la vue de la liste des armes
     * @param actionEvent événement déclenché par l'action de retour à la liste
     */
    public void RetourList(ActionEvent actionEvent) {
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
