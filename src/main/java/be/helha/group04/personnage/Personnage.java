    package be.helha.group04.personnage;

    /**
     * création de l'objet personnage
     * @author Clara
     * @see be.helha.group04.personnage
     */
    public class Personnage {

        /**
         * ça représente l'identiant d'un personnage (créé automatiquement).
         */
        private int id ;
        /**
         * ça représente le nom d'un personnage.
         */
        private String nom;
        /**
         * ça représente le nombre de points de vie qu'a un personnage, il ne peut en avoir que maximum 1000.
         */
        private int pv ;
        /**
         * ça représente la quantité de mana qu'a un personnage, il ne peut en avoir que maximum 100
         */
        private int mana ;

        /**
         *
         * @return
         */
        /**
         * cette méthode retourne l'identifiant du personnage.
         * @return - l'identifiant du personnage.
         */
        public int getId() {
            return id;
        }

        /**
         * cette méthode retourne le nom du personnage.
         * @return - le nom du personnage.
         */
        public String getNom() {
            return nom;
        }

        /**
         * cette méthode retourne le nombre de points vie du personnage.
         * @return - le nombre de points de vie du personnage.
         */
        public int getPv() {
            return pv;
        }

        /**
         * cette méthode retourne la mana du personnage.
         * @return - la mana du personnage.
         */
        public int getMana() {
            return mana;
        }

        /**
         * cette méthode définit l'identifiant du personnage.
         * @param id - l'identifiant à définir pour le personnage.
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * cette méthode définit le nom du personnage.
         * @param nom - le nom à définir pour le personnage.
         */
        public void setNom(String nom) {
            this.nom = nom;
        }

        /**
         * cette méthode définit le nombre de points de vie du personnage.
         * @param pv - le nombre de points de vies à définir pour le personnage.
         */
        public void setPv(int pv) {
            this.pv = pv;
        }

        /**
         * cette méthode définit la quantité de mana du personnage.
         * @param mana - la quantité de mana à définir pour le personnage.
         */
        public void setMana(int mana) {
            this.mana = mana;
        }
    }
