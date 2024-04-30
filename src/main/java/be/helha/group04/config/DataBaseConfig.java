package be.helha.group04.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * Cette classe de configuration charge les propriétés de la base de données à partir du fichier db-config.json
 * Elle utilise Jackson pour lire le fichier JSON et instancier un objet `DataBaseProperties`
 *
 * @author Dedecker Bastien
 * @see be.helha.group04.config
 */
@Configuration
public class DataBaseConfig {
    @Value("${db.config.file}")
    private String dbConfigFile;

    /**
     * Crée un bean `DataBaseProperties` à partir du fichier JSON spécifié par `db.config.file`.
     * Utilise Jackson pour lire le fichier et mapper les propriétés JSON à l'objet `DataBaseProperties`.
     *
     * @return un objet `DataBaseProperties` contenant les propriétés de la base de données.
     */
    @Bean
    public DataBaseProperties dataBaseProperties() {
        ObjectMapper objectMapper = new ObjectMapper();
        DataBaseProperties properties = new DataBaseProperties();
        try {
            File file = new File(dbConfigFile);
            properties = objectMapper.readValue(file, DataBaseProperties.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
