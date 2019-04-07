package org.kanke;

import org.junit.jupiter.api.Test;
import org.kanke.services.WordFile;
import org.kanke.services.WordFileFactory;

import java.io.File;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordCounterTest {

    //Better way to test, perhaps use a BDD framework?
    @Test
    public void shouldCountNumberOfWordsInCSVFile() {

        String fileName = getFilePath("countries.csv");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);

        String outPutString = WordCounter.getProgramOutput(wordFile);

        assertEquals("\nCN: 3\n" +
                "\n" +
                "China: 2\n" +
                "\n" +
                "JP: 2\n" +
                "\n" +
                "Japan: 2\n" +
                "\n" +
                "Ch\"ina: 1\n" +
                "\n" +
                "AU: 1\n" +
                "\n" +
                "TH: 1\n" +
                "\n" +
                "Australia: 1\n" +
                "\n" +
                "Thailand: 1",
                outPutString);
    }

    @Test
    public void shouldCountNumberOfWordsInXMLFile() {

        String fileName = getFilePath("testpom.xml");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);

        String outPutString = WordCounter.getProgramOutput(wordFile);

        assertTrue(outPutString.contains("\n" +
                "groupId: 24\n" +
                "\n" +
                "artifactId: 24\n" +
                "\n" +
                "version: 23\n" +
                "\n" +
                "dependency: 19\n" +
                "\n" +
                "org: 14\n" +
                "\n" +
                "maven: 10"));
    }

    @Test
    public void shouldCountNumberOfWordsInCSVFileWithSpecialCharacters() {

        String fileName = getFilePath("specialcharacters.csv");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);

        String outPutString = WordCounter.getProgramOutput(wordFile);

        assertEquals("", outPutString);
    }

    @Test
    public void shouldCountNumberOfWordsInJSONFile() {

        String fileName = getFilePath("glossary.json");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);

        String outPutString = WordCounter.getProgramOutput(wordFile);

        assertTrue(outPutString.contains("\n" +
                "SGML: 3\n" +
                "\n" +
                "markup: 3\n" +
                "\n" +
                "title: 2\n" +
                "\n" +
                "glossary: 2\n" +
                "\n" +
                "GlossSee: 1\n" +
                "\n" +
                "A: 1\n" +
                "\n" +
                "DocBook: 1"));
    }

    @Test
    public void shouldCountNumberOfWordsInTXTFileWithSpecialCharacters() {

        String fileName = getFilePath("specialcharacters.txt");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);

        String outPutString = WordCounter.getProgramOutput(wordFile);

        assertEquals("\nt: 1\n" +
                "\n" +
                "varA: 1", outPutString);
    }

    @Test
    public void shouldCountNumberOfWordsInTxtFile() {

        String fileName = getFilePath("actor.txt");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);

        String outPutString = WordCounter.getProgramOutput(wordFile);

        assertEquals("\nL: 6\n" +
                "\n" +
                "Fire: 2\n" +
                "\n" +
                "samuel: 2\n" +
                "\n" +
                "Jackson: 2", outPutString);
    }

    @Test
    public void shouldCountNumberOfWordsInTxtFileRegardlessOfOrder() {

        //Testing two files with same frequency of words but the words appear in different order
        String fileName = getFilePath("actor.txt");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);
        String outPutString = WordCounter.getProgramOutput(wordFile);
        String expectedFile1 = "\nL: 6\n" +
                "\n" +
                "Fire: 2\n" +
                "\n" +
                "samuel: 2\n" +
                "\n" +
                "Jackson: 2";
        assertEquals(expectedFile1, outPutString);

        String fileName2 = getFilePath("actor2.txt");
        WordFileFactory wordFileFactory2 = new WordFileFactory();
        WordFile wordFile2 = wordFileFactory2.getWordFile(fileName2);
        String outPutString2 = WordCounter.getProgramOutput(wordFile2);
        String expectedFile2 = "\nL: 6\n" +
                "\n" +
                "Fire: 2\n" +
                "\n" +
                "samuel: 2\n" +
                "\n" +
                "Jackson: 2";
        assertEquals(expectedFile2, outPutString2);

        assertEquals(expectedFile1, expectedFile2);
    }

    @Test
    public void shouldThrowIllegalArgumentErrorForUnsupportedFile() {

        try {
            String fileName = getFilePath("sad.html");
            WordFileFactory wordFileFactory = new WordFileFactory();
            wordFileFactory.getWordFile(fileName);

        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("Unsupported file type -_-"));
        }
    }

    @Test
    public void shouldNotCountNumberOfWordsInJSONFileWithSpecialCharacters() {

        String fileName = getFilePath("specialcharacters.json");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);

        String outPutString = WordCounter.getProgramOutput(wordFile);

        assertEquals("\nSorry! this file contains no valid words O_O: 0", outPutString);
    }

    @Test
    public void shouldNotCountNumberOfWordsInCSVFileWithSpecialCharacters() {

        String fileName = getFilePath("wrong.csv");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);

        String outPutString = WordCounter.getProgramOutput(wordFile);

        assertEquals("\nProblems reading from file, the error has been logged. please check file and try again later -_-\n",outPutString);
    }

    @Test
    public void shouldNotCountNumberOfWordsForWrongFilePathCSV() {

        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile("zero.csv");

        String outPutString = WordCounter.getProgramOutput(wordFile);

        assertEquals("\nCan't find file, please enter a valid path and try again -_-\n", outPutString);
    }

    @Test
    public void shouldNotCountNumberOfWordsForWrongFilePathTXT() {

        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile("'\'zero.txt");

        String outPutString = WordCounter.getProgramOutput(wordFile);

        assertEquals("\nDoes this file exist? please enter a valid path and try again -_-\n", outPutString);
    }

    @Test
    public void shouldNotCountNumberOfWordsForWrongEmptyTxtFile() {

        String fileName = getFilePath("emptytxt.txt");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);

        String outPutString = WordCounter.getProgramOutput(wordFile);

        assertEquals("\nSorry! this file contains no valid words O_O: 0", outPutString);
    }

    @Test
    public void shouldNotCountNumberOfWordsForWrongEmptyCSVFile() {

        String fileName = getFilePath("emptycsv.csv");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);

        String outPutString = WordCounter.getProgramOutput(wordFile);

        assertEquals("\nSorry! this file contains no valid words O_O: 0", outPutString);
    }

    @Test
    public void shouldNotCountNumberOfWordsForWrongEmptyXMLFile() {

        String fileName = getFilePath("emptyxml.xml");
        WordFileFactory wordFileFactory = new WordFileFactory();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);

        String outPutString = WordCounter.getProgramOutput(wordFile);

        assertEquals("\nSorry! this file contains no valid words O_O: 0", outPutString);
    }

    private String getFilePath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        String filePath = file.exists() ? file.getAbsolutePath() : "";
        return filePath;

    }

}
