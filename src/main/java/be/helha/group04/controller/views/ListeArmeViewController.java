package be.helha.group04.controller.views;

import be.helha.group04.arme.Arme;
import be.helha.group04.controller.api.ArmeController;
import be.helha.group04.modele.service.ArmeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Contrôleur pour la vue de liste des armes
 * Cette classe gère l'affichage des armes et les actions associées (détails, modification, suppression)
 * @author Hayriye Dogan
 * @see be.helha.group04.controller.views
 */
public class ListeArmeViewController implements Initializable {

    /**
     * Liste des noms des armes
     */
    @FXML
    private ListView<String> list_nom_arme;

    /**
     * Liste des identifiants des armes
     */
    @FXML
    private ListView<Integer> list_id_arme;

    /**
     * Liste des boutons pour afficher les détails des armes
     */
    @FXML
    private ListView<Button> list_bouton_detail;

    /**
     * Liste des boutons pour modifier les armes
     */
    @FXML
    private ListView<Button> list_bouton_modifier;

    /**
     * Liste des boutons pour supprimer les armes
     */
    @FXML
    private ListView<Button> list_bouton_supprimer;

    /**
     * Liste des armes disponibles
     */
    private List<Arme> listeArme;

    /**
     * Liste des noms des armes
     */
    private List<String> listeNomArme;

    /**
     * Liste des identifiants des armes
     */
    private List<Integer> listeIdArme;

    /**
     * Liste des boutons pour afficher les détails de chaque arme
     */
    private List<Button> listeBtDetail;

    /**
     * Liste des boutons pour modifier chaque arme
     */
    private List<Button> listeBtModif;

    /**
     * Liste des boutons pour supprimer chaque arme
     */
    private List<Button> listeBtSup;

    /**
     * instance de ArmeController pour gérer les opérations liées aux armes
     */
    private ArmeController armeController;

    /**
     * Constructeur qui initialise le contrôleur avec un ArmeService
     */
    public ListeArmeViewController() {
        ArmeService armeService = new ArmeService();
        this.armeController = new ArmeController(armeService);
    }

    /**
     * Initialise les listes d'armes et configure les ListView avec les données correspondantes
     * @param location emplacement utilisé pour résoudre les chemins relatifs pour l'objet racine, ou null si l'emplacement n'est pas connu
     * @param resources les ressources utilisées pour localiser l'objet racine, ou null si les ressources ne sont pas connues
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.listeArme = this.armeController.getAllArmes();
        this.listeNomArme = new ArrayList<String>();
        this.listeIdArme = new ArrayList<Integer>();
        this.listeBtDetail = new ArrayList<Button>();
        this.listeBtModif = new ArrayList<Button>();
        this.listeBtSup = new ArrayList<Button>();

        for(Arme arme: this.listeArme) {
            this.listeNomArme.add(arme.getNom());
            this.listeIdArme.add(arme.getId());

            Button detailButton = new Button("Detail");
            detailButton.setOnAction(event -> showViewDetailArme(event, arme.getId()));
            this.listeBtDetail.add(detailButton);

            Button modifierButton = new Button("Modifier");
            modifierButton.setOnAction(event -> showViewModifierArme(event, arme.getId()));
            this.listeBtModif.add(modifierButton);

            Button supprimerButton = new Button("Supprimer");
            supprimerButton.setOnAction(event -> showViewSupprimerArme(event, arme.getId()));
            this.listeBtSup.add(supprimerButton);
        }
        ObservableList<String> observableListNom = FXCollections.observableList(this.listeNomArme);
        ObservableList<Integer> observableListId = FXCollections.observableList(this.listeIdArme);;
        ObservableList<Button> observableListBtDetail = FXCollections.observableList(this.listeBtDetail);
        ObservableList<Button> observableListBtModif = FXCollections.observableList(this.listeBtModif);
        ObservableList<Button> observableListBtSup = FXCollections.observableList(this.listeBtSup);
        this.list_nom_arme.setItems(observableListNom);
        this.list_id_arme.setItems(observableListId);
        this.list_bouton_detail.setItems(observableListBtDetail);
        this.list_bouton_modifier.setItems(observableListBtModif);
        this.list_bouton_supprimer.setItems(observableListBtSup);
    }


    /**
     * Affiche la vue de détail d'une arme
     * @param event événement déclenché par l'action de visualisation des détails
     * @param id identifiant de l'arme à afficher en détail
     */
    public void showViewDetailArme(Event event, int id) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/detail_arme.fxml"));
            Parent newRoot = loader.load();
            DetailArmeController detailArmeController = loader.getController();
            detailArmeController.setId_arme(id);

            Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(newRoot));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche la vue pour modifier une arme
     * @param event événement déclenché par l'action de modification
     * @param id identifiant de l'arme à modifier
     */
    public void showViewModifierArme(Event event, int id) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/form_modif_arme.fxml"));
            Parent newRoot = loader.load();
            Form_modif_armeController formModifArmeController = loader.getController();
            formModifArmeController.setId_arme(id);

            Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(newRoot));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche la vue pour confirmer la suppression d'une arme
     * @param event événement déclenché par l'action de suppression
     * @param id identifiant de l'arme à supprimer
     */
    public void showViewSupprimerArme(Event event, int id) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/validation_sup_arme.fxml"));
            Parent newRoot = loader.load();
            Validation_sup_armeController validationSupArmeController = loader.getController();
            validationSupArmeController.setId_arme(id);

            Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(newRoot));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche le formulaire pour ajouter une nouvelle arme
     * @param actionEvent événement déclenché par l'action d'ajout d'une arme
     */
    public void AjouterArme(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/form_ajout_arme.fxml"));
            Parent newRoot = loader.load();

            Stage stage = (Stage) ((Parent) actionEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(newRoot));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne à la vue de l'accueil
     * @param actionEvent événement déclenché par l'action de retour à l'accueil
     */
    public void retourAccueil(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/acceuil-view.fxml"));
            Parent newRoot = loader.load();

            Stage stage = (Stage) ((Parent) actionEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(newRoot));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
