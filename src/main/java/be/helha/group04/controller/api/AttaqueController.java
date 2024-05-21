package be.helha.group04.controller.api;

import be.helha.group04.personnage.Personnage;
import be.helha.group04.modele.service.AttaqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * Contrôleur REST API pour la gestion des attaques entre personnages et armes.
 * Permet d'effectuer une attaque sur personnage avec une arme spécifiée.
 * Les opérations sont accessibles via les requêtes HTTP correspondantes.
 *
 * @author Dedecker Bastien
 * @see be.helha.group04.controller.api
 */
@RestController
@RequestMapping("/attaque")
public class AttaqueController {

    private final AttaqueService attaqueService;

    /**
     * Constructeur de la classe AttaqueController.
     *
     * @param attaqueService Service pour la gestion des attaques.
     */
    @Autowired
    public AttaqueController(AttaqueService attaqueService) {
        this.attaqueService = attaqueService;
    }

    /**
     * Effectue une attaque d'un personnage avec une arme spécifiée.
     *
     * @param personnageId L'ID du personnage attaquant.
     * @param armeId L'ID de l'arme utilisée pour l'attaque.
     * @return Le personnage attaqué après l'attaque.
     */
    @GetMapping("/{personnageId}/{armeId}")
    public Personnage attaquePersonnage(@PathVariable("personnageId") Integer personnageId, @PathVariable("armeId") Integer armeId) {
        return attaqueService.attaquePersonnage(personnageId, armeId);
    }
}
