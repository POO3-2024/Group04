package be.helha.group04.controller.views;

import be.helha.group04.controller.api.ArmeController;
import be.helha.group04.controller.api.PersonnageController;
import be.helha.group04.modele.service.ArmeService;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * cette classe répresente le controlleur de la vue dédié à l'acceuil
 * @author Clara
 * @see be.helha.group04.controller.views
 */
public class AcceuilViewController implements Initializable {

    /**
     * le label qui reprèsente une phrase qui indique le nombre de personnages présent dans la db
     */
    @FXML
    Label nombrePersonnageLabel;

    /**
     * le label qui reprèsente une phrase qui indique le nombre d'armes présent dans la db
     */
    @FXML
    Label NombreArmeLabel;

    /**
     * une instance de personnageController
     */
    private PersonnageController personnageController;

    /**
     * une instance de armeController
     */
    private ArmeController armeController;

    /**
     * constructeur de la classe acceuilViewController
     * initialise l'instance de personnageController
     * initialise l'instance de armeController
     */
    public AcceuilViewController() {
        ArmeService armeService = new ArmeService();
        this.armeController = new ArmeController(armeService);
        this.personnageController = new PersonnageController();
    }

    /**
     * Initialise la classe du contrôleur de l'acceuil. Elle définit le texte du nombrePersonnageLabel et nombreArmeLabel pour indiquer le nombre de personnages et d'armes
      *présent dans la db, en utilisant la forme singulière ou plurielle en fonction du nombre de personnages et armes.
     * @param location
     * L'emplacement utilisé pour résoudre les chemins relatifs pour l'objet racine, ou
     * {@code null} si l'emplacement n'est pas connu.
     *
     @param resources
     * Les ressources utilisées pour localiser l'objet racine, ou {@code null}
      * si l'objet racine n'a pas été localisé.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int nombrePersonnages =  personnageController.getAllPersonnage().size();
        String nombrePersonnagesString = String.valueOf(nombrePersonnages);
        nombrePersonnageLabel.setText(nombrePersonnages > 1 ? "il y a " + nombrePersonnagesString + " personnages" : "il y a " + nombrePersonnagesString + " personnage" );
        int nombreArmes =  armeController.getAllArmes().size();
        String nombreArmesString = String.valueOf(nombreArmes);
        NombreArmeLabel.setText(nombreArmes > 1 ? "il y a " + nombreArmesString + " armes" : "il y a " + nombreArmesString + " arme" );
    }

    /**
     *  Affiche la vue de la liste des personnages. Cette méthode est appelée lorsqu'il y a un clic
     *  sur le bouton afficher personnage.
     *  Elle charge la vue FXML correspondante et remplace la scène acceuil par la nouvelle scène.
     *
     * @param event est l'événement qui déclenche l'appel de cette méthode.
     */
    @FXML
    public void showViewListePersonnage(Event event){
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
     * Affiche la vue de la liste des armes. Cette méthode est appelée lorsqu'il y a un clic
     * sur le bouton afficher arme.
     * Elle charge la vue FXML correspondante et remplace la scène actuelle par la nouvelle scène.
     * @param event est l'événement qui déclenche l'appel de cette méthode.
     */
    @FXML
    public void showViewListeArme(Event event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/list-arme-view.fxml"));
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