import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -jar student-grades.jar <path_to_csv>");
            return;
        }

        String csvFilePath = args[0];

        CSVReader csvReader = new CSVReader();
        List<Etudiant> etudiants = CSVReader.readCSV(csvFilePath);

        DatabaseNotation.createTable();

        DatabaseNotation.AjoutEtudiant(etudiants);

        System.out.println("Les étudiants ont été importés avec succès dans la base de données.");
        System.out.println("Liste des étudiants dans la base de données :");
        DatabaseNotation.afficherEtudiants();  // Afficher les étudiants dans la base de données
    }
}
