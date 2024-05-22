package be.helha.group04.modele.service;

import be.helha.group04.arme.Arme;
import be.helha.group04.personnage.Personnage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttaqueService {

    private final PersonnageService personnageService;
    private final ArmeService armeService;

    @Autowired
    public AttaqueService(PersonnageService personnageService, ArmeService armeService) {
        this.personnageService = personnageService;
        this.armeService = armeService;
    }

    public Personnage attaquePersonnage(Integer personnageId, Integer armeId) {

        Personnage personnage = personnageService.getPersonnageById(personnageId);
        Arme arme = armeService.getArmeById(armeId);

        if (personnage == null || arme == null) {
            throw new RuntimeException("Le personnage ou l'arme n'a pas été trouvé");
        }
        int nouveauPv = personnage.getPv() - arme.getDegats();
        personnage.setPv(Math.max(nouveauPv, 0));
        personnageService.updatePersonnage(personnage);

        return personnage;
    }
}
