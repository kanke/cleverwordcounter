
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class WordCounterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void shouldCountNumberOfWordsInCSVFile(){

       String fileName = getFilePath("countries.csv");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordCount(fileName);

        WordCounter.printWordCount(wordFile);

        assertEquals("\nChina: 3\n" +
                "\n" +
                "CN: 3\n" +
                "\n" +
                "JP: 2\n" +
                "\n" +
                "Japan: 2\n" +
                "\n" +
                "AU: 1\n" +
                "\n" +
                "TH: 1\n" +
                "\n" +
                "Australia: 1\n" +
                "\n" +
                "Thailand: 1\n", outContent.toString());
    }

    @Test
    public void shouldCountNumberOfWordsInTxtFile(){

        String fileName = getFilePath("actor.txt");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordCount(fileName);

        WordCounter.printWordCount(wordFile);

        assertEquals("\nL: 3\n" +
                "\n" +
                "Fire: 1\n" +
                "\n" +
                "samuel: 1\n" +
                "\n" +
                "Jackson: 1\n", outContent.toString());

    }

    @Test
    public void shouldNotCountNumberOfWordsForUnsupportedFile(){

        String fileName = getFilePath("sad.xml");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordCount(fileName);

        WordCounter.printWordCount(wordFile);

        assertEquals("Unsupported file type -_- \n", outContent.toString());
    }

    @Test
    public void shouldNotCountNumberOfWordsForWrongFilePathCSV(){

        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordCount("zero.csv");

        WordCounter.printWordCount(wordFile);

        assertEquals("\nCan't find csv file, please enter a valid path and try again -_-\n\n", outContent.toString());
    }

    @Test
    public void shouldNotCountNumberOfWordsForWrongFilePathTXT(){

        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordCount("zero.txt");

        WordCounter.printWordCount(wordFile);

        assertEquals("\nCan't find txt file, please enter a valid path and try again -_-\n\n", outContent.toString());
    }

    @Test
    public void shouldNotCountNumberOfWordsForWrongEmptyTxtFile(){

        String fileName = getFilePath("emptytxt.txt");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordCount(fileName);

        WordCounter.printWordCount(wordFile);

        assertEquals("\nSorry! this txt file is empty O_O \n\n", outContent.toString());
    }

    @Test
    public void shouldNotCountNumberOfWordsForWrongEmptyCSVFile(){

        String fileName = getFilePath("emptycsv.csv");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordCount(fileName);

        WordCounter.printWordCount(wordFile);

        assertEquals("\nSorry! this csv file is empty O_O \n\n", outContent.toString());
    }

    private String getFilePath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        String file = new File(classLoader.getResource(fileName).getFile()).getAbsolutePath();
        return file;

    }
}
