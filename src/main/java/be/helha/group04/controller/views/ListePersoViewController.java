package be.helha.group04.controller.views;

import be.helha.group04.personnage.Personnage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.awt.*;

import java.util.List;

import be.helha.group04.controller.api.PersonnageController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * cette classe est le controller dédié à la page de la liste des personnages
 * @author Clara
 * @see be.helha.group04.controller.views
 */
public class ListePersoViewController implements Initializable {

    /**
     * une instance du personnageController
     */
    private PersonnageController personnageController;
    /**
     * une liste des personnages
     */
    private List<Personnage> listePersonnage;
    /**
     * une liste qui va contenir l'id de tous les personnages
     */
    @FXML
    ListView<Label> list_id_perso;
    /**
     * une liste qui va contenir le nom de tous les personnages
     */
    @FXML
    ListView<Label>  list_nom_perso;
    /**
     * une liste qui va contenir le bouton detail pour tous les personnages
     * qui va afficher les détails du personnages
     */
    @FXML
    ListView<Button>  list_bouton_detail;
    /**
     * une liste qui va contenir le bouton modifier pour tous les personnages
     * où on va pouvoir modifier les informations du personnage
     */
    @FXML
    ListView<Button>  list_bouton_modifier;
    /**
     * une liste qui va contenir le bouton supprimer pour tous les personnages
     * où on pourra supprimer un personnage
     */
    @FXML
    ListView<Button>  list_bouton_supprimer;


    /**
     * consctructeur de la classe ListePersoViewController
     * initialise l'instance de personnageController
     * et initialise la liste des personnages en prenant tout les personnages dans la db
     */
    public ListePersoViewController() {
        this.personnageController = new PersonnageController();
        this.listePersonnage = this.personnageController.getAllPersonnage();
    }

    /**
     * initialisation des listes des labels et boutons et créations des labels et des boutons pour chaque personnages,
     * et met les informations de chaque personnage dans les listesView correspondantes
     @param location
     L'emplacement utilisé pour résoudre les chemins relatifs pour l'objet racine, ou
     {@code null} si l'emplacement n'est pas connu.
     *
     @param resources
     Les ressources utilisées pour localiser l'objet racine, ou {@code null}
     si l'objet racine n'a pas été localisé.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Label> labelsId = FXCollections.observableArrayList();
        ObservableList<Label> labelsNom = FXCollections.observableArrayList();
        ObservableList<Button> boutonsDetail = FXCollections.observableArrayList();
        ObservableList<Button> boutonsModif = FXCollections.observableArrayList();
        ObservableList<Button> boutonsSupp = FXCollections.observableArrayList();

      listePersonnage.forEach(personnage -> {
          Label id = new Label(String.valueOf(personnage.getId()));
          labelsId.add(id);
          Label nom = new Label(personnage.getNom());
          labelsNom.add(nom);
          Button buttonDetail = new Button("Détail");
          buttonDetail.setOnAction(event -> showViewDetailPerso(event , personnage.getId()));
          boutonsDetail.add(buttonDetail);
          Button buttonModif = new Button("Modifier");
          buttonModif.setOnAction(event -> showViewModifPerso(event, personnage.getId()));
          boutonsModif.add(buttonModif);
          Button buttonSupp = new Button("Supprimer");
          buttonSupp.setOnAction(event -> SupprimerPersonnage(event, personnage.getId()));
          boutonsSupp.add(buttonSupp);
      });

        list_id_perso.setItems(labelsId);
        list_nom_perso.setItems(labelsNom);
        list_bouton_detail.setItems(boutonsDetail);
        list_bouton_modifier.setItems(boutonsModif);
        list_bouton_supprimer.setItems(boutonsSupp);
    }

    /**
     * Affiche la vue des détails des personnages. Cette méthode est appelée lorsqu'il y a un clic
     * sur le bouton afficher les détails du personnage.
     *   Elle charge la vue FXML correspondante et remplace la scène acceuil par la nouvelle scène.
     * @param event est l'événement qui déclenche l'appel de cette méthode.
     * @param id est l'id du personnage que l'on veut voir les détails
     */
    @FXML
    public void showViewDetailPerso(Event event , int id) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/detail_perso.fxml"));
            Parent newRoot = loader.load();

            //obtenir le contrôler associé
            DetailPersoController persoController = loader.getController();

            //passer le paramètre au controler
            persoController.setId_perso(id);

            // Obtenir la scène actuelle
            Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();

            // Changer la scène
            stage.setScene(new Scene(newRoot));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche la vue des formulaire de modification des personnages. Cette méthode est appelée lorsqu'il y a un clic
     * sur le bouton modifier du personnage.
     * Elle charge la vue FXML correspondante et remplace la scène acceuil par la nouvelle scène.
     * @param event est l'événement qui déclenche l'appel de cette méthode.
     * @param id est l'id du personnage que l'on veut modifier
     */
    @FXML
    public void showViewModifPerso(Event event, int id) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/form_modif_perso.fxml"));
            Parent newRoot = loader.load();

            //obtenir le contrôler associé
            Form_modif_persoController persoController = loader.getController();

            //passer le paramètre au controler
            persoController.recupInfoPerso(id);

            // Obtenir la scène actuelle
            Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();

            // Changer la scène
            stage.setScene(new Scene(newRoot));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche la vue suppression des personnages. Cette méthode est appelée lorsqu'il y a un clic
     * sur le bouton supprimer du personnage.
     * Elle charge la vue FXML correspondante et remplace la scène acceuil par la nouvelle scène.
     * @param event est l'événement qui déclenche l'appel de cette méthode.
     * @param id est l'id du personnage que l'on veut supprimer
     */
    @FXML
    public void SupprimerPersonnage(Event event, int id){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/validation_sup_perso.fxml"));
            Parent newRoot = loader.load();

            //obtenir le contrôler associé
            Validation_sup_persoController persoController = loader.getController();

            //passer le paramètre au controler
            persoController.recupId(id);

            // Obtenir la scène actuelle
            Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();

            // Changer la scène
            stage.setScene(new Scene(newRoot));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche la vue de l'ajout des personnages. Cette méthode est appelée lorsqu'il y a un clic
     * sur le bouton ajouter un personnage.
     * Elle charge la vue FXML correspondante et remplace la scène acceuil par la nouvelle scène.
     * @param event est l'événement qui déclenche l'appel de cette méthode.
     */
    @FXML
    public void ajouterPersonnage(Event event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/form-ajout-perso.fxml"));
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
    public void retourAcceuil(Event event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/acceuil-view.fxml"));
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
