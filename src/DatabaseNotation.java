import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseNotation {
    // URL de la base de données SQLite
    private static final String URL = "jdbc:sqlite:etudiant.db";
    // Requête SQL pour créer la table des étudiants
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS etudiants (" +
            "id TEXT PRIMARY KEY, " +
            "nom TEXT NOT NULL, " +
            "note REAL NOT NULL)";
    // Requête SQL pour insérer un étudiant dans la table
    private static final String INSERT_STUDENT_SQL = "INSERT INTO etudiants (id, nom, note) VALUES (?, ?, ?)";

    // Méthode pour se connecter à la base de données SQLite
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);  // Établir la connexion
        } catch (SQLException e) {
            System.out.println(e.getMessage());  // Afficher une erreur en cas de problème de connexion
        }
        return conn;  // Retourner la connexion
    }

    // Méthode pour créer la table des étudiants si elle n'existe pas
    public static void createTable() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_TABLE_SQL);  // Exécuter la requête pour créer la table
        } catch (SQLException e) {
            System.out.println(e.getMessage());  // Afficher une erreur en cas de problème d'exécution de la requête
        }
    }

    // Méthode pour insérer une liste d'étudiants dans la table
    public static void AjoutEtudiant(List<Etudiant> etudiants) {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_STUDENT_SQL)) {
            for (Etudiant etudiant : etudiants) {
                // Définir les valeurs des paramètres de la requête
                pstmt.setString(1, etudiant.getId());
                pstmt.setString(2, etudiant.getNom());
                pstmt.setDouble(3, etudiant.getNote());
                pstmt.addBatch();  // Ajouter la requête à un lot
            }
            pstmt.executeBatch();  // Exécuter le lot de requêtes
        } catch (SQLException e) {
            System.out.println(e.getMessage());  // Afficher une erreur en cas de problème d'exécution des requêtes
        }
    }
}
