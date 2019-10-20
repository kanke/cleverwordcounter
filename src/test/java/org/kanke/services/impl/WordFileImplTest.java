package org.kanke.services.impl;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

public class WordFileImplTest {

  @Rule
  public final ExpectedException thrown = ExpectedException.none();

  @Test
  public void constructorInputNotNullOutputNotNull() {
    final WordFileImpl actual = new WordFileImpl("?");
    Assert.assertNotNull(actual);
  }

  @Test
  public void constructorInputNotNullOutputNotNull1() {
    final String arg0 = "";
    final WordFileImpl actual = new WordFileImpl(arg0);
    Assert.assertNotNull(actual);
  }

  @Test
  public void countWordsOutputIOException() throws IOException {
    final WordFileImpl thisObj = new WordFileImpl("");
    thrown.expect(IOException.class);
    thisObj.countWords();
  }
}
