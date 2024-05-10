package be.helha.group04.modele;

/**
 * Cette classe représente les propriétés du fichier db-config.json pour se connecter à la base de données
 *
 * @author Dedecker Bastien
 * @see be.helha.group04.modele
 */
public class DataSourceProperties {

    /**
     * URL de la base de données
     */
    private String url;

    /**
     * Nom du pilote
     */
    private String driverClassName;

    /**
     * Nom d'utilisateur (il n'y en a pas)
     */
    private String username;

    /**
     * Mot de passe (il n'y en a pas)
     */
    private String password;

    /**
     * Retourne le nom de la classe du pilote de la base de données
     *
     * @return le nom de la classe du pilote de la base de données
     */
    public String getDriverClassName() {
        return driverClassName;
    }

    /**
     * Retourne le mot de passe pour se connecter à la base de données
     *
     * @return le mot de passe pour se connecter à la base de données
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retourne l'URL de la base de données
     *
     * @return l'URL de la base de données
     */
    public String getUrl() {
        return url;
    }

    /**
     * Retourne le nom d'utilisateur pour se connecter à la base de données
     *
     * @return le nom d'utilisateur pour se connecter à la base de données
     */
    public String getUsername() {
        return username;
    }

    /**
     * Définit le nom de la classe du pilote de la base de données.
     *
     * @param driverClassName le nom de la classe du pilote de la base de données.
     */
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    /**
     * Définit le mot de passe pour se connecter à la base de données.
     *
     * @param password le mot de passe pour se connecter à la base de données.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Définit l'URL de la base de données.
     *
     * @param url l'URL de la base de données.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Définit le nom d'utilisateur pour se connecter à la base de données.
     *
     * @param username le nom d'utilisateur pour se connecter à la base de données.
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
