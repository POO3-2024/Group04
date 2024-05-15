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


/**
 * Cette classe gère les opérations CRUD (Create, Read, Update, Delete) sur les objets Arme
 * @author Hayriye Dogan
 * @see be.helha.group04.service
 */
@Service
public class ArmeService {

    /**
     * Requête SQL pour obtenir une arme par son nom
     */
        public static final String GET = "SELECT Nom, Degats FROM Arme WHERE Nom=?";

    /**
     * Requête SQL pour ajouter une nouvelle arme dans la base de données
     */
    public static final String AJOUT = "INSERT INTO Arme (Nom, Degats) VALUES (?, ?)";

    /**
     * Requête SQL pour mettre à jour les dégâts d'une arme dans la base de données
     */
        public static final String MAJ = "UPDATE Arme SET Degats=? WHERE Nom=?";

    /**
     * Requête SQL pour lister toutes les armes dans la base de données
     */
    public static final String LISTER = "SELECT * FROM Arme";

    /**
     * Requête SQL pour supprimer une arme de la base de données
     */
    public static final String SUPPRIMER = "DELETE FROM Arme WHERE Nom=?";




    @Autowired
        private ConnectionDb connectionDb;


        public boolean createArme(Arme arme) {
            try (Connection conn = connectionDb.getConnection()) {
                String sql = "INSERT INTO Arme (Nom, Degats) VALUES (?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, arme.getNom());
                pstmt.setInt(2, arme.getDegats());
                int result = pstmt.executeUpdate();
                return result > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        public Arme getArmeById(Integer id) {
            Arme arme = null;
            try (Connection conn = connectionDb.getConnection()) {
                String sql = "SELECT * FROM Arme WHERE Id=?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    arme = new Arme();
                    arme.setId(rs.getInt("Id"));
                    arme.setNom(rs.getString("Degats"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return arme;
        }


        public List<Arme> getAllArmes() {
            List<Arme> armes = new ArrayList<>();
            try (Connection conn = connectionDb.getConnection()) {
                String sql = "SELECT * FROM Arme";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Arme arme = new Arme();
                    arme.setId(rs.getInt("Id"));
                    arme.setNom(rs.getString("Nom"));
                    arme.setDegats(rs.getInt("Degats"));
                    armes.add(arme);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return armes;
        }

        public boolean updateArme(Arme arme) {
            try (Connection conn = connectionDb.getConnection()) {
                String sql = "UPDATE Arme SET Nom=?, Degats=? WHERE Id=?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, arme.getNom());
                pstmt.setInt(2, arme.getDegats());
                pstmt.setInt(3, arme.getId());
                int result = pstmt.executeUpdate();
                return result>0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        public boolean deleteArmeById(Integer id) {
            try (Connection conn = connectionDb.getConnection()) {
                String sql = "DELETE FROM Arme WHERE Id=?";
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
