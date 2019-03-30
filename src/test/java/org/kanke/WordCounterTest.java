package org.kanke;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class WordCounterTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void shouldCountNumberOfWordsInCSVFile(){

       String fileName = getFilePath("countries.csv");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);

        WordCounter.printProgramOutput(wordFile);

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
    public void shouldCountNumberOfWordsInCSVFileWithSpecialCharacters(){

        String fileName = getFilePath("wrong.csv");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);

        WordCounter.printProgramOutput(wordFile);

        assertEquals("", outContent.toString());
    }

//    @Test
//    public void shouldCountNumberOfWordsInTXTFileWithSpecialCharacters(){
//
//        String fileName = getFilePath("wrong.txt");
//        org.kanke.WordFileFactory wordFileFactory = new org.kanke.WordFileFactory();
//        org.kanke.WordFile wordFile = wordFileFactory.getWordFile(fileName);
//
//        org.kanke.WordCounter.printProgramOutput(wordFile);
//
//        assertEquals("", outContent.toString());
//    }

    @Test
    public void shouldCountNumberOfWordsInTxtFile(){

        String fileName = getFilePath("actor.txt");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);

        WordCounter.printProgramOutput(wordFile);

        assertEquals("\nL: 6\n" +
                "\n" +
                "Fire: 2\n" +
                "\n" +
                "samuel: 2\n" +
                "\n" +
                "Jackson: 2\n", outContent.toString());

    }

    @Test
    public void shouldCountNumberOfWordsInTxtFileRegardlessOfOrder(){

        //Testing two files with same frequency of words but the words appear in different order
        String fileName = getFilePath("actor.txt");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);

        WordCounter.printProgramOutput(wordFile);

        String expectedFile1 = "\nL: 6\n" +
                "\n" +
                "Fire: 2\n" +
                "\n" +
                "samuel: 2\n" +
                "\n" +
                "Jackson: 2\n";
        assertEquals(expectedFile1, outContent.toString());

        String fileName2 = getFilePath("actor2.txt");
        WordFileFactory wordFileFactory2 = new WordFileFactory();
        WordFile wordFile2 = wordFileFactory2.getWordFile(fileName2);

        WordCounter.printProgramOutput(wordFile2);

        String expectedFile2 = "\nL: 6\n" +
                "\n" +
                "Fire: 2\n" +
                "\n" +
                "samuel: 2\n" +
                "\n" +
                "Jackson: 2\n";
        assertEquals(expectedFile1 + expectedFile2, outContent.toString());

        assertEquals(expectedFile1, expectedFile2);
    }

    @Test
    public void shouldNotCountNumberOfWordsForUnsupportedFile(){

        exception.expect(IllegalArgumentException.class);

        String fileName = getFilePath("sad.xml");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);

        WordCounter.printProgramOutput(wordFile);

        assertEquals("Unsupported file type -_- \n", outContent.toString());
    }

    @Test
    public void shouldNotCountNumberOfWordsForWrongFilePathCSV(){

        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile("zero.csv");

        WordCounter.printProgramOutput(wordFile);

        assertEquals("\nCan't find file, please enter a valid path and try again -_-\n\n", outContent.toString());
    }

    @Test
    public void shouldNotCountNumberOfWordsForWrongFilePathTXT(){

        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile("zero.txt");

        WordCounter.printProgramOutput(wordFile);

        assertEquals("\nCan't find file, please enter a valid path and try again -_-\n\n", outContent.toString());
    }

    @Test
    public void shouldNotCountNumberOfWordsForWrongEmptyTxtFile(){

        String fileName = getFilePath("emptytxt.txt");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);

        WordCounter.printProgramOutput(wordFile);

        assertEquals("\nSorry! this txt file is empty O_O \n\n", outContent.toString());
    }

    @Test
    public void shouldNotCountNumberOfWordsForWrongEmptyCSVFile(){

        String fileName = getFilePath("emptycsv.csv");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);

        WordCounter.printProgramOutput(wordFile);

        assertEquals("\nSorry! this csv file is empty O_O \n\n", outContent.toString());
    }

    private String getFilePath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        String filePath = file.exists() ? file.getAbsolutePath() : "";
        return filePath;

    }
}
