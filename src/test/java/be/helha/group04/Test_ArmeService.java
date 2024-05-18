package be.helha.group04;

import be.helha.group04.arme.Arme;
import be.helha.group04.modele.service.ArmeService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static java.lang.Class.forName;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Cette classe de test vérifie les fonctionnalités de la classe pour les méthodes de CRUD
 * ArmeService
 * @author Hayriye Dogan
 * @see ArmeService ;
 */
@TestMethodOrder(OrderAnnotation.class)
public class Test_ArmeService {
    /**
     * Instance de ArmeService utilisée pour effectuer les opérations CRUD pendant les tests
     */
    private static ArmeService armeService;

    /**
     * Liste des objets Arme utilisés pour les tests
     */
    private static List<Arme> armes;

    /**
     * Initialise l'instance d'ArmeService avant tous les tests
     * @throws SQLException si une erreur de connexion à la base de données survient
     */
    @BeforeAll
    // sera exécuté une fois avant toutes les méthodes
    public static void initialiserArmeService() throws SQLException {
        armeService = new ArmeService();
    }

    /**
     * Initialise la liste des armes avant tous les tests
     * Cette méthode sera exécutée avant toutes les autres méthodes de test
     */
    @BeforeAll
    // sera exécuté avant toutes les méthodes
    static void initialiserListeArmes() {
        armes = new ArrayList<Arme>(3);
        // ajout des armes (liste déjà triée)
        armes.add(new Arme("fusil", 80));
        armes.add(new Arme("arme blanche", 50));
        armes.add(new Arme("bombe", 99));
    }

    /**
     * Teste l'ajout d'armes à la base de données
     * Vérifie que chaque arme est ajoutée avec succès
     */
    @Test
    @Order(1)
    public void testAjouterArme(){
        for (Arme b : armes) {
            assertTrue(armeService.createArme(b));
        }
    }

    /**
     * Teste la récupération de toutes les armes depuis la base de données
     * Vérifie que la liste des armes récupérées est égale à la liste initiale des armes
     */
    @Test
    @Order(2)
    public void testListerArmes() {
        List<Arme> armesObtenues = armeService.getAllArmes();
        for (int i = 0; i < armes.size(); i++) {
            assertEquals(armesObtenues.get(i), armes.get(i));
        }
    }

    /**
     * Teste la recherche d'armes par leur identifiant dans la base de données
     * Pour chaque arme dans la liste des armes, vérifie que l'arme récupérée
     * depuis la base de données est égale à l'arme initiale
     */
    @Test
    @Order(3)
    public void testRechercherArme() {
        for (Arme b : armes) {
            Arme armeObtenue = armeService.getArmeById(b.getId());
            assertEquals(armeObtenue, b);
        }
    }

    /**
     * Teste la modification d'une arme dans la base de données
     * Récupère une arme depuis la liste des armes (aussi présente dans la base de données),
     * la modifie en ajoutant un symbole "!" à son nom et en augmentant ses dégâts de 1,
     * puis vérifie que la modification a été effectuée avec succès
     */
    @Test
    @Order(4)
    public void testModifierArme() {
        Arme arme = armeService.getArmeById(armes.get(0).getId());
        arme.setNom(arme.getNom() + "!");
        arme.setDegats(arme.getDegats()+1);
        assertTrue(armeService.updateArme(arme));
        Arme armeObtenue = armeService.getArmeById(arme.getId());
        assertEquals(armeObtenue, arme);
    }

    /**
     * Teste la suppression d'une arme de la base de données
     * Pour chaque arme dans la liste des armes, vérifie que l'arme est supprimée avec succès
     */
    @Test
    @Order(5)
    public void testSupprimerArme() {
        for (Arme b : armes) {
            assertTrue(armeService.deleteArmeById(b.getId()));
        }
    }
}
