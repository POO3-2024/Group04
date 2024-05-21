package be.helha.group04.controller.views;
import be.helha.group04.controller.api.PersonnageController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailPersoController implements Initializable {

    @FXML
    Label id_perso;

    @FXML
    Label nom_perso;

    @FXML
    Label pv;

    @FXML
    Label mana;

    private PersonnageController personnageController;

    public DetailPersoController() {
        this.personnageController = new PersonnageController();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
