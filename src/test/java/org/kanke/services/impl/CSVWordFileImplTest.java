package org.kanke.services.impl;

import org.junit.Assert;
import org.junit.Test;

public class CSVWordFileImplTest {

  @Test
  public void constructorInputNotNullOutputNotNull() {
    final CSVWordFileImpl actual = new CSVWordFileImpl("?");
    Assert.assertNotNull(actual);
  }

  @Test
  public void constructorInputNotNullOutputNotNull1() {
    final String arg0 = "aaaaa";
    final CSVWordFileImpl actual = new CSVWordFileImpl(arg0);
    Assert.assertNotNull(actual);
  }
}
