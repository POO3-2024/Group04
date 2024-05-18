package be.helha.group04.controller.api;

import be.helha.group04.modele.service.PersonnageService;
import be.helha.group04.personnage.Personnage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Contrôleur REST API pour la gestion des personnages.
 * Permet la création, la récupération, la mise à jour et la suppression des personnages.
 * Les opérations sont accessibles via les requêtes HTTP correspondantes.
 *
 * @author Dedecker Bastien
 * @see be.helha.group04.controller.api
 */
@RestController
@RequestMapping("/personnage")
public class PersonnageController {

    @Autowired
    private PersonnageService personnageService;

    /**
     * Constructeur de la classe PersonnageController.
     * Initialise le service de gestion des personnages.
     */
    public PersonnageController() {
        this.personnageService = new PersonnageService();
    }

    /**
     * Crée un nouveau personnage.
     *
     * @param personnage Le personnage à créer.
     * @return Le personnage créé.
     * @throws RuntimeException si une erreur survient lors de la création.
     */
    @PostMapping
    public Personnage createPersonnage(@RequestBody Personnage personnage){
        if(personnageService.createPersonnage(personnage)){
            return personnage;
        } else {
            throw new RuntimeException("Erreur lors de la création du personnage.");
        }
    }

    /**
     * Récupère un personnage via son ID.
     *
     * @param id L'ID du personnage à récupérer.
     * @return Le personnage correspondant à l'ID.
     * @throws RuntimeException si aucun personnage n'est trouvé pour l'ID spécifié.
     */
    @GetMapping("/{id}")
    public Personnage getPersonnageById(@PathVariable("id") Integer id) {
        Personnage personnage = personnageService.getPersonnageById(id);
        if (personnage != null){
            return personnage;
        } else {
            throw new RuntimeException("Personnage non trouvée pour l'ID: " + id);
        }
    }

    /**
     * Récupère tous les personnages.
     *
     * @return La liste de tous les personnages.
     */
    @GetMapping
    public List<Personnage> getAllPersonnage() {
        return personnageService.getAllPersonnages();
    }

    /**
     * Met à jour un personnage.
     *
     * @param id L'ID du personnage à mettre à jour.
     * @param PersonnageUpdated Les nouvelles données du personnage.
     * @return Le personnage mis à jour.
     * @throws RuntimeException si une erreur survient lors de la mise à jour.
     */
    @PutMapping("/{id}")
    public Personnage updatePersonnage(@PathVariable Integer id, @RequestBody Personnage PersonnageUpdated) {
        PersonnageUpdated.setId(id);
        if(personnageService.updatePersonnage(PersonnageUpdated)){
            return PersonnageUpdated;
        } else {
            throw new RuntimeException("Erreur lors de la mise à jour du peronnage avec l'ID: " + id);
        }
    }

    /**
     * Supprime un personnage par son ID.
     *
     * @param id L'ID du personnage à supprimer.
     * @throws RuntimeException si une erreur survient lors de la suppression.
     */
    @DeleteMapping("/{id}")
    public void deletePersonnageById(@PathVariable Integer id) {
        if(!personnageService.deletePersonnageById(id)){
            throw new RuntimeException("Erreur lors de la suppression du personnage avec l'ID: " + id);
        }
    }

}
