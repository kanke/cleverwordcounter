import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TxtWordCounter {

    protected static Map<String, Integer> txtWordCounter(String fileName) {

        Map<String, Integer> wordMap = new HashMap<>();
        Scanner scanner;

        try {
            scanner = new Scanner(new File(fileName));
            boolean hasNext = scanner.hasNext();

            if (!hasNext) {
                System.out.println("\nSorry! this file is empty O_O \n");
            }

            while (hasNext) {
                String word = scanner.next();

                if (wordMap.containsKey(word)) {
                    wordMap.put(word, wordMap.get(word) + 1);
                } else {
                    wordMap.put(word, 1);
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("\nCan't find txt file, please enter a valid path and try again -_-\n");
        }

        return wordMap;
    }
}
