package assignmentjavafile.utility;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {
    private CSVReader() {
        throw new IllegalStateException("Utility class");
    }
    public static final String DELIMITER = ";";
    public static List<List<String>> getDataFromCSV(String fileLocation) {
        List<List<String>> data = new ArrayList<>();

        try {
            File file = new File(fileLocation);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String row;
            List<String> dataRow;
            while ((row = bufferedReader.readLine()) != null) {
                dataRow = Arrays.asList(row.split(DELIMITER));
                data.add(dataRow);
            }
            bufferedReader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return data;
    }
}
