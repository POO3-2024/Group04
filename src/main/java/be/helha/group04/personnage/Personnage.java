package be.helha.group04.personnage;

public class Personnage {

private int id ;
private String nom;
private int pv ;
private int mana ;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getPv() {
        return pv;
    }

    public int getMana() {
        return mana;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
