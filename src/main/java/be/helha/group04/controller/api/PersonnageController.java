package be.helha.group04.controller.api;

import be.helha.group04.modele.service.PersonnageService;
import be.helha.group04.personnage.Personnage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * Récupère la liste de tous les personnages
     * @return Liste de tous les personnages
     */
    @GetMapping
    public List<Personnage> getAllPersonnage() {
        return personnageService.getAllPersonnages();
    }

    /**
     * Récupère un personnage par son ID
     * @param id ID du personnage à récupèrer
     * @return Le personnage correspondant à l'ID specifie
     */
    @GetMapping("/{id}")
    public Personnage getPersonnage(@PathVariable("id") Integer id) {
        return personnageService.getPersonnageById(id);
    }
}
