import java.util.Map;
import java.util.Scanner;

public class WordCounter {

    public static void main(String[] args) {

        System.out.println("\n\n***** ^_^ Welcome to Clever WordFile Counter ^_^ ******\n");

        System.out.println("Kindly enter a wordFile path below to continue #^_^# :");

        WordFileFactory wordFileFactory = new WordFileFactory();
        Scanner scan = new Scanner(System.in);
        String fileName = scan.next();
        WordFile wordFile = wordFileFactory.getWordCount(fileName);

        printWordCount(wordFile);

    }

    protected static void printWordCount(WordFile wordFile) {
        if (wordFile != null) {
            wordFile.countWords().entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .forEachOrdered(wordEntry -> System.out.println("\n" + wordEntry.getKey() + ": " + wordEntry.getValue()));
        } else {
            System.out.println("Unsupported file type -_- ");
        }
    }
}

