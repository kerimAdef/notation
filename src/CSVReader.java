
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    // Méthode pour lire les données d'un fichier CSV et les convertir en une liste d'objets Student
    public static List<Etudiant> readCSV(String filePath) {
        List<Etudiant> etudiants = new ArrayList<>();  // Liste pour stocker les étudiants
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            boolean firstLine = true; // Flag pour ignorer la première ligne
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Ignorer la première ligne
                }
                String[] etudiantData = line.split(csvSplitBy);
                try {
                    Etudiant etudiant = new Etudiant(
                            Integer.parseInt(etudiantData[0]),
                            etudiantData[1],
                            Double.parseDouble(etudiantData[2])
                    );
                    etudiants.add(etudiant);
                } catch (IllegalArgumentException e) {
                    System.err.println("Erreur lors de l'ajout de l'étudiant : " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return etudiants;
    }
}
