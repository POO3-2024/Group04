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

public class ListePersoViewController implements Initializable {

    private PersonnageController personnageController;
    private List<Personnage> listePersonnage;
    @FXML
    ListView<Label> list_id_perso;
    @FXML
    ListView<Label>  list_nom_perso;
    @FXML
    ListView<Button>  list_bouton_detail;
    @FXML
    ListView<Button>  list_bouton_modifier;
    @FXML
    ListView<Button>  list_bouton_supprimer;


    public ListePersoViewController() {
        this.personnageController = new PersonnageController();
        this.listePersonnage = this.personnageController.getAllPersonnage();
    }

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

    @FXML
    public void showViewModifPerso(Event event, int id) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/form_modif_perso.fxml"));
            Parent newRoot = loader.load();

            // Obtenir la scène actuelle
            Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();

            // Changer la scène
            stage.setScene(new Scene(newRoot));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void SupprimerPersonnage(Event event, int id){
        //TO DO
    }

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
