package be.helha.group04.arme;

/**  La classe Arme représente une arme avec un identifiant, un nom et des dégats
 *  @author Hayriye Dogan
 *  @see be.helha.group04.arme
 */
public class Arme {
    /**
     *  L'identifiant de l'arme
     */
    private int id;
    /**
     * Le nom de l'arme
     */
    private String nom;
    /**
     * Les dégats infligés par l'arme, maximum 100
     */
    private int degats;

    /**
     * Construit une nouvelle arme
     * @param nom Le nom de l'arme
     * @param degats Les dégats infligés par l'arme
     */
    public Arme(String nom, int degats) {
        this.nom = nom;
        this.degats = degats;
    }

    public Arme()
    {

    }

    /**
     * Getter pour l'identifiant de l'arme
     * @return l'identifiant de l'arme
     */
    public int getId() {
        return id;
    }

    /**
     * Getter pour le nom de l'arme.
     * @return le nom de l'arme
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter pour les dégâts infligés par l'arme
     * @return les dégâts infligés par l'arme
     */
    public int getDegats() {
        return degats;
    }

    /**
     * Setter pour l'identifiant de l'arme
     * @param id L'identifiant de l'arme
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter pour le nom de l'arme
     * @param nom Le nouveau nom de l'arme
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Setter pour les dégâts infligés par l'arme
     * @param degats Les nouveaux dégâts infligés par l'arme
     */
    public void setDegats(int degats) {
        this.degats = degats;
    }

}
