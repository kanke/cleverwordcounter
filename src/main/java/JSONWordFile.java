import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class JSONWordFile implements WordFile {

    private String fileName;

    public JSONWordFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Map<String, Integer> countWords() {

        Map<String, Integer> wordMap = new HashMap<>();
        PrintStream errorPrintStream = null;

        try {
            errorPrintStream = new PrintStream("csvErrorLog.txt");
            CSVReader reader = new CSVReader(new FileReader(fileName));
//
//            JsonReader reader =
//                    Json.createReader(new FileReader("./src/main/resources/jsonObject.json"));


        } catch (FileNotFoundException e) {
            System.out.println("\nCan't find csv file, please enter a valid path and try again -_-\n");

        } catch (IOException e) {
            System.setErr(errorPrintStream);
            e.printStackTrace();
            System.out.println("\nProblems reading from CSV file, the error has been logged. please try again later -_-\n");
        }

        return wordMap;
    }
}
