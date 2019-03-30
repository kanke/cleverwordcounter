package org.kanke.services;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Map;

public interface WordFile {

    Map<String, Long> countWords() throws IOException, ParseException;
}
