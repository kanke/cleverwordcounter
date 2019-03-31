package org.kanke.services;

import java.io.IOException;
import java.util.Map;

public interface WordFile {

    Map<String, Long> countWords() throws IOException;
}
