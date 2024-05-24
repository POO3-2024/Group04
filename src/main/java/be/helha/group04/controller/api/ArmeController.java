package be.helha.group04.controller.api;

import be.helha.group04.arme.Arme;
import be.helha.group04.modele.service.ArmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Contrôleur REST API pour la gestion des armes.
 * Permet de créer, lire, mettre à jour et supprimer des armes.
 * Les opérations sont accessibles via les requêtes HTTP correspondantes.
 *
 * @author Dedecker Bastien
 * @see be.helha.group04.controller.api
 */
@RestController
@RequestMapping("/arme")
public class ArmeController {

    private final ArmeService armeService;

    /**
     * Constructeur de la classe ArmeController.
     *
     * @param armeService Service pour la gestion des armes.
     */
    @Autowired
    public ArmeController(ArmeService armeService) {
        this.armeService = armeService;
    }

    /**
     * Crée une nouvelle arme.
     *
     * @param arme L'arme à créer.
     * @return L'arme créée.
     * @throws RuntimeException Si une erreur survient lors de la création de l'arme.
     */
    @PostMapping
    public Arme createArme(@RequestBody Arme arme) {
        if (armeService.createArme(arme)) {
            return arme;
        } else {
            throw new RuntimeException("Erreur lors de la création de l'arme.");
        }
    }

    /**
     * Permet d'obtenir une arme via son ID.
     *
     * @param id L'ID de l'arme à récupérer.
     * @return L'arme correspondant à l'ID spécifié.
     * @throws RuntimeException Si aucune arme n'est trouvée pour l'ID spécifié.
     */
    @GetMapping("/{id}")
    public Arme getArmeById(@PathVariable("id") Integer id) {
        Arme arme = armeService.getArmeById(id);
        if (arme != null) {
            return arme;
        } else {
            throw new RuntimeException("Arme non trouvée pour l'ID: " + id);
        }
    }

    /**
     * Permet d'obtenir toutes les armes.
     *
     * @return Liste contenant toutes les armes.
     */
    @GetMapping
    public List<Arme> getAllArmes() {
        return armeService.getAllArmes();
    }

    /**
     * Met à jour une arme.
     *
     * @param id L'ID de l'arme à mettre à jour.
     * @param armeUpdated  Les nouvelles informations de l'arme.
     * @return L'arme mise à jour.
     * @throws RuntimeException Si une erreur survient lors de la mise à jour de l'arme.
     */
    @PutMapping("/{id}")
    public Arme updateArme(@PathVariable Integer id, @RequestBody Arme armeUpdated) {
        armeUpdated.setId(id);
        if (armeService.updateArme(armeUpdated)) {
            return armeUpdated;
        } else {
            throw new RuntimeException("Erreur lors de la mise à jour de l'arme avec l'ID: " + id);
        }
    }

    /**
     * Supprime une arme via son ID.
     *
     * @param id L'ID de l'arme à supprimer.
     * @throws RuntimeException Si une erreur survient lors de la suppression de l'arme.
     */
    @DeleteMapping("/{id}")
    public void deleteArmeById(@PathVariable Integer id) {
        if (!armeService.deleteArmeById(id)) {
            throw new RuntimeException("Erreur lors de la suppression de l'arme avec l'ID: " + id);
        }
    }
}
