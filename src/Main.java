import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -jar student-grades.jar <path_to_csv>");
            return;  // Arrêter le programme si le nombre d'arguments est incorrect
        }


        String csvFilePath = args[0];  // Récupérer le chemin du fichier CSV depuis les arguments
        List<Etudiant> etudiants = CSVReader.readCSV(csvFilePath);  // Lire les étudiants depuis le fichier CSV

        DatabaseNotation.createTable();  // Créer la table des étudiants dans la base de données
        DatabaseNotation.AjoutEtudiant(etudiants);  // Insérer les étudiants dans la base de données

        System.out.println("Students have been successfully imported into the database.");  // Message de confirmation
    }
}
