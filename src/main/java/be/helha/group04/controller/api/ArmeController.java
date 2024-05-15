package be.helha.group04.controller.api;


import be.helha.group04.arme.Arme;
import be.helha.group04.modele.service.PersonnageService;
import be.helha.group04.personnage.Personnage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arme")
public class ArmeController {
/*
    @Autowired
    //private ArmeService armeService;

    public ArmeController() {
        this.personnageService = new PersonnageService();
    }

    //créer un nouveau personnage
    @PostMapping
    public Arme createArme(@RequestBody Arme arme){
        //ArmeService.createArme(arme);
        return arme;
    }

    //obtenir un personnage via son id
    @GetMapping("/{id}")
    public Arme getArmeById(@PathVariable("id") Integer id) {
        return armeService.getArmeById(id);
    }

    //obtenir toutes les personnages
    @GetMapping
    public List<Arme> getAllArme() {
        return armeService.getAllArme();
    }

    //update les personnages
    @PutMapping("/{id}")
    public Arme updateArme(@PathVariable Integer id, @RequestBody Arme ArmeUpdated) {
        ArmeUpdated.setId(id);
        armeService.updateArme(ArmeUpdated);
        return ArmeUpdated;
    }

    //supprimer un personnage via son id
    @DeleteMapping("/{id}")
    public void deleteArmeById(@PathVariable Integer id) {
        armeService.deleteArmeById(id);
    }

 */
}
