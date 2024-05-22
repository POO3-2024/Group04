package be.helha.group04;

import be.helha.group04.modele.service.PersonnageService;
import be.helha.group04.personnage.Personnage;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * cette classe de test vérifie toutes les fonctionnalités des CRUD du personnage
 * @author Clara
 * @see be.helha.group04.modele.service.PersonnageService
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_PersonnageService {

    /**
     * une instance de personnageService pour effectuer les opérations du CRUD du personnages lors des tests
     */
    private static PersonnageService personnageService;

    /**
     * une liste des personnages pour les tests
     */
    private static List<Personnage> personnages;

    /**
     * le nombre de personnage qu'il y a dans la db avant les test
     */
    private static int nombrePersoAvanttest;

    /**
     * Initialiser l'instance d'ArmeService avant tous les tests
     * @throws SQLException lance une exception si une erreur de connexion à la base de données survient
     */
    @BeforeAll
    public static void initialiserPersonnageService() throws SQLException {
        personnageService = new PersonnageService();
    }

    /**
     * initialise la liste des personnages avant tout les tests
     */
    @BeforeAll
    // sera exécuté avant toutes les méthodes
    static void initialiserListePersonnages() {
        personnages = new ArrayList<Personnage>(3);
        personnages.add(new Personnage("Billie", 528, 59));
        personnages.add(new Personnage("Lotus", 600, 99));
        personnages.add(new Personnage("Cedric", 900, 99));
    }

    /**
     * ajout d'un personnage à la base de données
     */
    @Test
    @Order(1)
    public void testAjoutPersonnage(){
        nombrePersoAvanttest = personnageService.getAllPersonnages().size();
        for (Personnage Bastion : personnages) {
            assertTrue(personnageService.createPersonnage(Bastion));
        }
    }

    /**
     * Teste la récupération de toutes les armes depuis la base de données
     */
    @Test
    @Order(2)
    public void testListePersonnage() {
        List<Personnage> personnagesObtenus = personnageService.getAllPersonnages();
        for (int i = nombrePersoAvanttest; i < personnagesObtenus.size(); i++) {
            assertEquals(personnagesObtenus.get(i), personnages.get(i - nombrePersoAvanttest));
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
        for (Personnage Bastion : personnages) {
            Personnage personneObtenu = personnageService.getPersonnageById(Bastion.getId());
            assertEquals(personneObtenu, Bastion);
        }
    }

    /**
     * test la modification d'un personnage puis vérifie
     * que la modification a été effectuée avec succès
     */
    @Test
    @Order(4)
    public void testModifierArme() {
        Personnage personnage = personnageService.getPersonnageById(personnages.get(0).getId());
        personnage.setNom("Ben");
        personnage.setPv(200);
        personnage.setMana(50);
        assertTrue(personnageService.updatePersonnage(personnage));
        Personnage personnageObtenu = personnageService.getPersonnageById(personnage.getId());
        assertEquals(personnageObtenu, personnage);
    }

    /**
     * test la suppression d'un personnage dans la base de données
     */
    @Test
    @Order(5)
    public void testSupprimerArme() {
        for (Personnage Bastion : personnages) {
            assertTrue(personnageService.deletePersonnageById(Bastion.getId()));
        }
    }
}
