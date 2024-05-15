package be.helha.group04.controller.api;

import be.helha.group04.modele.service.PersonnageService;
import be.helha.group04.personnage.Personnage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personnage")
public class PersonnageController {

    @Autowired
    private PersonnageService personnageService;

    //créer un nouveau personnage
    @PostMapping
    public Personnage createPersonnage(@RequestBody Personnage personnage){
        personnageService.createPersonnage(personnage);
        return personnage;
    }

    //obtenir un personnage via son id
    @GetMapping("/{id}")
    public Personnage getPersonnageById(@PathVariable("id") Integer id) {
        return personnageService.getPersonnageById(id);
    }

    //obtenir toutes les personnages
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

    //supprimer un personnage via son id
    @DeleteMapping("/{id}")
    public void deletePersonnageById(@PathVariable Integer id) {
        personnageService.deletePersonnageById(id);
    }

}
