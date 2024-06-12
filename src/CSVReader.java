
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    // Méthode pour lire les données d'un fichier CSV et les convertir en une liste d'objets Student
    public static List<Etudiant> readCSV(String filePath) {
        List<Etudiant> students = new ArrayList<>();  // Liste pour stocker les étudiants
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {  // Lire chaque ligne du fichier CSV
                String[] values = line.split(",");  // Séparer les valeurs par des virgules
                if (values.length == 3) {  // Vérifier que la ligne contient exactement 3 valeurs
                    // Créer un nouvel objet Student et l'ajouter à la liste
                    students.add(new Etudiant(values[0], values[1], Double.parseDouble(values[2])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  // Afficher une erreur en cas de problème de lecture du fichier
        }
        return students;  // Retourner la liste des étudiants
    }
}
