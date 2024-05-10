package be.helha.group04.controller.api;

import be.helha.group04.modele.service.PersonnageService;
import be.helha.group04.personnage.Personnage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/personnage")
public class PersonnageController {

    @Autowired
    private PersonnageService personnageService;

    @GetMapping
    public List<Personnage> getAllPersonnage() {
        return personnageService.getAllPersonnages();
    }

    @GetMapping("/{id}")
    public Personnage getPersonnage(@PathVariable("id") Integer id) {
        return personnageService.getPersonnageById(id);
    }
}
