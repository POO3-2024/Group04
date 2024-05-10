package be.helha.group04.modele.service;

import be.helha.group04.modele.ConnectionDb;
import be.helha.group04.personnage.Personnage;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonnageService {

    @Autowired
    private ConnectionDb connectionDb;

    // Créer une personne
    public void createPersonne(Personnage personnage) {
        try (Connection conn = connectionDb.getConnection()) {
            String sql = "INSERT INTO personnage (Id, Nom,Pv, Mana) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, personnage.getId());
            pstmt.setString(2, personnage.getNom());
            pstmt.setInt(3, personnage.getPv());
            pstmt.setInt(4, personnage.getMana());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtenir une personne par ID
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
                personnage.setId(rs.getInt("Pv"));
                personnage.setId(rs.getInt("Mana"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personnage;
    }

    // Obtenir toutes les personnes
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
                personnages.add(personnage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personnages;
    }

    // Mettre à jour une personne
    public void updatePersonne(Personnage personnage) {
        try (Connection conn = connectionDb.getConnection()) {
            String sql = "UPDATE personne SET Nom = ? , Pv = ? , Mana = ? WHERE Id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, personnage.getNom());
            pstmt.setInt(2, personnage.getPv());
            pstmt.setInt(3, personnage.getMana());
            pstmt.setInt(4, personnage.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer une personne par ID
    public void deletePersonneById(Integer id) {
        try (Connection conn = connectionDb.getConnection()) {
            String sql = "DELETE FROM personne WHERE Id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
