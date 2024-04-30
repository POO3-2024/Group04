package be.helha.group04.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Cette classe gère la connexion à la base de données en utilisant les propriétés de connexion définies dans `DataBaseProperties`.
 *
 * Elle fournit une méthode pour obtenir une connexion à la base de données.
 * @author Dedecker Bastien
 * @see be.helha.group04.config
 */
@Component
public class ConnectionDb {
    @Autowired
    private DataBaseProperties databaseProperties;

    /**
     * Retourne une connexion à la base de données en utilisant les propriétés de connexion définies dans `DataBaseProperties`.
     *
     * @return une connexion à la base de données.
     * @throws SQLException si une erreur se produit lors de l'établissement de la connexion.
     */
    public Connection getConnection() throws SQLException {
        DataSourceProperties dsProperties = databaseProperties.getDataSource();
        return DriverManager.getConnection(dsProperties.getUrl(), dsProperties.getUsername(), dsProperties.getPassword());
    }
}
