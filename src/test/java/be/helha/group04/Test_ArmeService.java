package be.helha.group04;

import be.helha.group04.arme.Arme;
import be.helha.group04.service.ArmeService;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static java.lang.Class.forName;
import static java.sql.DriverManager.getDriver;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Cette classe de test vérifie les fonctionnalités de la classe pour les méthodes de CRUD
 * ArmeService
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

}
