import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseNotation {
    // URL de la base de données SQLite
    private static final String URL = "jdbc:sqlite:etudiants.db";

    // Requête SQL pour créer la table des étudiants
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS etudiants (" +
            "id INTEGER PRIMARY KEY, " +
            "nom TEXT NOT NULL, " +
            "note REAL NOT NULL)";

    // Requête SQL pour insérer un étudiant dans la table
    private static final String INSERT_STUDENT_SQL = "INSERT INTO etudiants (id, nom, note) VALUES (?, ?, ?)";

    // Requête SQL pour sélectionner tous les étudiants
    private static final String SELECT_ALL_SQL = "SELECT * FROM etudiants";

    // Requête SQL pour calculer la moyenne des notes de tous les étudiants
    private static final String SELECT_AVERAGE_SQL = "SELECT AVG(note) AS average FROM etudiants";

    // Requête SQL pour sélectionner un étudiant par ID
    private static final String SELECT_ETUDIANT_SQL = "SELECT * FROM etudiants WHERE id = ?";

    // Requête SQL pour mettre à jour la note d'un étudiant
    private static final String UPDATE_NOTE_SQL = "UPDATE etudiants SET note = ? WHERE id = ?";

    // Requête SQL pour calculer la moyenne des notes d'un étudiant
    private static final String SELECT_ETUDIANT_AVERAGE_SQL = "SELECT AVG(note) AS average FROM etudiants WHERE id = ?";

    // Bloc statique pour charger explicitement le pilote JDBC pour SQLite
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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
                pstmt.setInt(1, etudiant.getId());
                pstmt.setString(2, etudiant.getNom());
                pstmt.setDouble(3, etudiant.getNote());
                pstmt.addBatch();  // Ajouter la requête à un lot
            }
            pstmt.executeBatch();  // Exécuter le lot de requêtes
        } catch (SQLException e) {
            System.out.println(e.getMessage());  // Afficher une erreur en cas de problème d'exécution des requêtes
        }
    }

    // Méthode pour afficher tous les étudiants de la base de données
    public static void afficherEtudiants() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL)) {
            while (rs.next()) {
                int id = rs.getInt("id");  // Récupérer l'ID de l'étudiant
                String nom = rs.getString("nom");  // Récupérer le nom de l'étudiant
                double note = rs.getDouble("note");  // Récupérer la note de l'étudiant
                System.out.println("ID: " + id + ", Nom: " + nom + ", Note: " + note);  // Afficher les détails de l'étudiant
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());  // Afficher une erreur en cas de problème de requête
        }
    }

    // Méthode pour afficher la moyenne de tous les étudiants
    public static void afficherMoyenne() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_AVERAGE_SQL)) {
            if (rs.next()) {
                double average = rs.getDouble("average");  // Récupérer la moyenne des notes
                System.out.println("Moyenne des notes de tous les étudiants: " + average);  // Afficher la moyenne
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());  // Afficher une erreur en cas de problème de requête
        }
    }

    // Méthode pour afficher les notes d'un étudiant particulier
    public static void afficherNotesEtudiant(int id) {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ETUDIANT_SQL)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("nom");
                double note = rs.getDouble("note");
                System.out.println("ID: " + id + ", Nom: " + nom + ", Note: " + note);
            } else {
                System.out.println("Aucun étudiant trouvé avec l'ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Méthode pour mettre à jour la note d'un étudiant
    public static void ajouterNoteEtudiant(int id, double note) {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_NOTE_SQL)) {
            pstmt.setDouble(1, note);
            pstmt.setInt(2, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Note mise à jour avec succès pour l'étudiant avec l'ID: " + id);
            } else {
                System.out.println("Aucun étudiant trouvé avec l'ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Méthode pour afficher la moyenne des notes d'un étudiant
    public static void afficherMoyenneEtudiant(int id) {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ETUDIANT_AVERAGE_SQL)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                double average = rs.getDouble("average");  // Récupérer la moyenne des notes
                System.out.println("Moyenne des notes de l'étudiant avec l'ID " + id + ": " + average);  // Afficher la moyenne
            } else {
                System.out.println("Aucun étudiant trouvé avec l'ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
