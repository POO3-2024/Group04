package be.helha.group04.service;

import be.helha.group04.arme.Arme;
import be.helha.group04.modele.ConnectionDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * Cette classe gère les opérations CRUD (Create, Read, Update, Delete) sur les objets Arme
 * @author Hayriye Dogan
 * @see be.helha.group04.service
 */
@Service
public class ArmeService {

    /**
     * Instance de ConnectionDb utilisée pour obtenir des connexions à la base de données
     */
    @Autowired
        private ConnectionDb connectionDb;

    /**
     * Constructeur par défaut qui initialise l'instance de ConnectionDb
     */
    public ArmeService() {
        this.connectionDb = new ConnectionDb();
    }

    /**
     * Crée une nouvelle arme dans la base de données
     * @param arme l'objet Arme à ajouter à la base de données
     * @return true si l'insertion a réussi, sinon false
     */
    public boolean createArme(Arme arme) {
            try (Connection conn = connectionDb.getConnection()) {
                String sql = "INSERT INTO Arme (Nom, Degats) VALUES (?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, arme.getNom());
                pstmt.setInt(2, arme.getDegats());
                int result = pstmt.executeUpdate();
                ResultSet resultSet = pstmt.getGeneratedKeys();
                while(resultSet.next()) {
                    arme.setId(resultSet.getInt(1));
                }
                return result > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

    /**
     * Récupère une arme par son nom depuis la base de données
     * @param id l'id de l'arme à récupérer
     * @return l'objet Arme correspondant a l'id, ou null si aucune arme n'est trouvée
     */
    public Arme getArmeById(Integer id) {
        Arme arme = null;
        try (Connection conn = connectionDb.getConnection()) {
            String sql = "SELECT * FROM Arme WHERE ID=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                arme = new Arme();
                arme.setId(rs.getInt("ID"));
                arme.setNom(rs.getString("Nom"));
                arme.setDegats(rs.getInt("Degats"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arme;
    }


    /**
     * Récupère la liste de toutes les armes depuis la base de données
     * @return la liste des objets Arme
     */
    public List<Arme> getAllArmes() {
        List<Arme> armes = new ArrayList<Arme>();
        try (Connection conn = connectionDb.getConnection()) {
            String sql = "SELECT * FROM Arme";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Arme arme = new Arme();
                arme.setId(rs.getInt("ID"));
                arme.setNom(rs.getString("Nom"));
                arme.setDegats(rs.getInt("Degats"));
                armes.add(arme);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return armes;
    }
}
