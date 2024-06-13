import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class CSVGenerator {
    public static void main(String[] args) {
        String csvFile = "etudiants.csv";
        String header = "id,nom,note";
        String[] data = {
                "1,MOMO,15.5",
                "2,ADEF,19.5",
                "3,CEDRICK,12.0",
                "4,JEROME,18.0",
                "5,YVAN,13.5",
                "6, ALLAN,13",
                "7,MODRIC, 18",
        };

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            writer.println(header);
            for (String row : data) {
                writer.println(row);
            }
            System.out.println("Fichier CSV créé avec succès : " + csvFile);
        } catch (IOException e) {
            System.out.println("Erreur lors de la création du fichier CSV");
            e.printStackTrace();
        }
    }
}
