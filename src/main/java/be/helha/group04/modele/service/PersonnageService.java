package be.helha.group04.modele.service;

import be.helha.group04.modele.ConnectionDb;
import be.helha.group04.personnage.Personnage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * cette classe représente les CRUD (Create, Read, Update, Delete) d'un personnage.
 * @author Clara
 * @see be.helha.group04.modele.service
 */
@Service
public class PersonnageService {

    @Autowired
    /**
     * objet qui permet la connexion à la base de données
     */
    private ConnectionDb connectionDb;

    /**
     * constructeur de la classe personnageService
     * initialise la connexion à la base de données
     */
    public PersonnageService() {
        this.connectionDb = new ConnectionDb();
    }

    /**
     * fonction qui permet la création d'un personnage dans la database
     * @param personnage - personnage est l'objet que l'on souhaite ajouté à la db
     */
    public boolean createPersonnage(Personnage personnage) {
        try (Connection conn = connectionDb.getConnection()) {
            String sql = "INSERT INTO personnage (Nom,Pv, Mana) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, personnage.getNom());
            pstmt.setInt(2, personnage.getPv());
            pstmt.setInt(3, personnage.getMana());
            int result = pstmt.executeUpdate();
            ResultSet resultSet = pstmt.getGeneratedKeys();
            while(resultSet.next()) {
                personnage.setId(resultSet.getInt(1));
            }
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * fonction qui permet d'obtenir un personnage selon son id
     * @param id - id du personnage voulu
     * @return - retourne le personnage souhaité selon son id
     */
    public Personnage getPersonnageById(Integer id) {
        Personnage personnage = null;
        try (Connection conn = connectionDb.getConnection()) {
            String sql = "SELECT * FROM personnage WHERE Id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                personnage = new Personnage();
                personnage.setId(rs.getInt("Id"));
                personnage.setNom(rs.getString("Nom"));
                personnage.setPv(rs.getInt("Pv"));
                personnage.setMana(rs.getInt("Mana"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personnage;
    }

    /**
     * fonction qui permet d'obtenir tout les personnages dans la db
     * @return - qui retourne une liste de tout les personnages
     */
    public List<Personnage> getAllPersonnages() {
        List<Personnage> personnages = new ArrayList<>();
        try (Connection conn = connectionDb.getConnection()) {
            String sql = "SELECT * FROM personnage";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Personnage personnage = new Personnage();
                personnage.setId(rs.getInt("Id"));
                personnage.setNom(rs.getString("Nom"));
                personnage.setPv(rs.getInt("Pv"));
                personnage.setMana(rs.getInt("Mana"));
                personnages.add(personnage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personnages;
    }

    /**
     * fonction qui permet de mettre à jour les données d'un personnage.
     * @param personnage -,personnage est l'objet que l'on souhaite mettre à jour.
     */
    public boolean updatePersonnage(Personnage personnage) {
        try (Connection conn = connectionDb.getConnection()) {
            String sql = "UPDATE personnage SET Nom = ? , Pv = ? , Mana = ? WHERE Id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, personnage.getNom());
            pstmt.setInt(2, personnage.getPv());
            pstmt.setInt(3, personnage.getMana());
            pstmt.setInt(4, personnage.getId());
            int result = pstmt.executeUpdate();
            return result>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * fonction qui permet de supprimer un personnage à l'aide de son id.
     * @param id - id du personnage que l'on veut supprimer.
     */
    public boolean deletePersonnageById(Integer id) {
        try (Connection conn = connectionDb.getConnection()) {
            String sql = "DELETE FROM personnage WHERE Id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int result = pstmt.executeUpdate();
            return result>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
