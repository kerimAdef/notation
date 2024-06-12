import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class CSVRead {
    public static List<Etudiant> readCSV(String filePath) {
        List<Etudiant> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) {
                    students.add(new Etudiant(values[0], values[1], Double.parseDouble(values[2])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}

