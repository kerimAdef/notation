import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Classe pour gérer la base de données SQLite
public class DatabaseManager {
    // URL de la base de données SQLite
    private static final String DB_URL = "jdbc:sqlite:etudiants.db";

    // Méthode pour créer la base de données et la table des étudiants
    public void createDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS etudiants ("
                        + "id INTEGER PRIMARY KEY,"
                        + "nom TEXT NOT NULL,"
                        + "note REAL"
                        + ");";
                PreparedStatement pstmt = conn.prepareStatement(createTableSQL);
                pstmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour insérer un étudiant dans la base de données
    public void insertEtudiant(Étudiant etudiant) {
        String insertSQL = "INSERT INTO etudiants(id, nom, note) VALUES(?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setInt(1, etudiant.getId());      // Paramètre pour l'id
            pstmt.setString(2, etudiant.getNom());  // Paramètre pour le nom
            pstmt.setDouble(3, etudiant.getNote()); // Paramètre pour la note
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
