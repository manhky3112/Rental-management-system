import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public static List<String[]> readCSV(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
        return data;
    }
}
