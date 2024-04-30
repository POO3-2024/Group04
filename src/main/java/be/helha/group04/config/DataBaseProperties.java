package be.helha.group04.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Cette classe représente les propriétés de la base de données
 *
 * @author Dedecker Bastien
 * @see be.helha.group04.config
 */
public class DataBaseProperties {
    /**
     * Instance de 'DataSourceProperties'
     */
    @JsonProperty("datasource")
    private DataSourceProperties dataSource;

    /**
     * Retourne les propriétés de la source de données
     *
     * @return une instance de `DataSourceProperties` contenant les propriétés de la source de données
     */
    public DataSourceProperties getDataSource() {
        return dataSource;
    }

    /**
     * Définit les propriétés de la source de données
     *
     * @param dataSource une instance de `DataSourceProperties` contenant les propriétés de la source de données
     */
    public void setDataSource(DataSourceProperties dataSource) {
        this.dataSource = dataSource;
    }
}
