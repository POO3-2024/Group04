package be.helha.group04.controller.api;

import be.helha.group04.modele.service.PersonnageService;
import be.helha.group04.personnage.Personnage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controleur pour la gestion des personnages
 * Il permet de récupérer la liste de tous les personnages et de récupérer un personnages selon son ID
 */
@RestController
@RequestMapping("/personnage")
public class PersonnageController {

    /**
     * Instance pour la gestion personnages
     */
    @Autowired
    private PersonnageService personnageService;

    public PersonnageController() {
        this.personnageService = new PersonnageService();
    }

    //créer un nouveau personnage
    @PostMapping
    public Personnage createPersonnage(@RequestBody Personnage personnage){
        personnageService.createPersonnage(personnage);
        return personnage;
    }

    //obtenir un personnage via son id
    @GetMapping("/{id}")
    public Personnage getPersonnageById(@PathVariable Integer id) {
        return personnageService.getPersonnageById(id);
    }

    //obtenir toutes les personnages

    /**
     * Récupère la liste de tous les personnages
     * @return Liste de tous les personnages
     */
    @GetMapping
    public List<Personnage> getAllPersonnage() {
        return personnageService.getAllPersonnages();
    }


    //update les personnages
    @PutMapping("/{id}")
    public Personnage updatePersonnage(@PathVariable Integer id, @RequestBody Personnage PersonnageUpdated) {
        PersonnageUpdated.setId(id);
        personnageService.updatePersonnage(PersonnageUpdated);
        return PersonnageUpdated;
    }

    /**
     * Récupère un personnage par son ID
     * @param id ID du personnage à récupèrer
     * @return Le personnage correspondant à l'ID specifie
     */
    @GetMapping("/{id}")
    public Personnage getPersonnage(@PathVariable Integer id) {
        return personnageService.getPersonnageById(id);
    }

    //supprimer un personnage via son id
    @DeleteMapping("/{id}")
    public void deletePersonnageById(@PathVariable Integer id) {
        personnageService.deletePersonnageById(id);
    }

}
