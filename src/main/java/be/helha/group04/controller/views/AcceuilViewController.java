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
 * Contrôleur pour la vue d'accueil
 * Cette classe initialise les données affichées sur la vue d'accueil et gère les actions de navigation vers d'autres vues
 * @author Clara Collart
 * @author Hayriye Dogan
 * @see be.helha.group04.controller.views
 */
public class AcceuilViewController implements Initializable {

    @FXML
    Label nombrePersonnageLabel;

    /**
     * Label pour afficher le nombre d'arme
     */
    @FXML
    Label NombreArmeLabel;

    private PersonnageController personnageController;

    /**
     * instance de ArmeController
     */
    private ArmeController armeController;

    //constructeur
    /**
     * Constructeur qui initialise les contrôleurs de personnage et d'arme
     */
    public AcceuilViewController() {
        this.personnageController = new PersonnageController();

        ArmeService armeService = new ArmeService();
        this.armeController = new ArmeController(armeService);
    }

    /**
     * Méthode appelée pour initialiser un contrôleur lorsque son élément racine a été complètement traité
     * Initialise les label avec le nombre de personnages et d'armes
     *
     * @param location l'emplacement utilisé pour résoudre les chemins relatifs pour l'objet racine, ou null si l'emplacement n'est pas connu
     *
     * @param resources les ressources utilisées pour localiser l'objet racine, ou null si les ressources ne sont pas connues
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int nombrePersonnages =  personnageController.getAllPersonnage().size();
        String nombrePersonnagesString = String.valueOf(nombrePersonnages);
        nombrePersonnageLabel.setText(nombrePersonnages > 1 ? "il y a " + nombrePersonnagesString + " personnages" : "il y a " + nombrePersonnagesString + " personnage" );

        int nombreArmes = armeController.getAllArmes().size();
        String nombreArmesString = String.valueOf(nombreArmes);
        this.NombreArmeLabel.setText(nombreArmes > 1 ? "Il y a "+nombreArmesString+ " armes" : "Il y a "+nombreArmesString+ " arme");
    }

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
     * Affiche la vue de la liste des armes
     * @param event événement déclenché par l'action de navigation pour lister les armes
     */
    @FXML
    public void showViewListeArme(Event event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/list-arme-view.fxml"));
            Parent newRoot = loader.load();

            Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(newRoot));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}