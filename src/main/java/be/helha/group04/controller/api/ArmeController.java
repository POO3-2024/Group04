package be.helha.group04.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/arme")
public class ArmeController {

    /*@Autowired
    private ArmeService armeService;

    @GetMapping
    public List<Personnage> getAllArmes() {
        return armeService.getAllArmes();
    }

    @GetMapping("/{id}")
    public Personnage getArme(@PathVariable("id") Integer id) {
        return armeService.getArmeById(id);
    }*/
}
