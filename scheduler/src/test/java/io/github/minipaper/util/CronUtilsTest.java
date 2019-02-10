package io.github.minipaper.util;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CronUtilsTest {

  @Test
  public void check() {
    assertTrue(CronUtils.check("0/10 * * * * ?"));
    assertFalse(CronUtils.check("123456"));
  }

  @Test
  public void nextList() {
    List<String> nextList = CronUtils.nextList("0/10 * * * * ?", 5);
    nextList.forEach(System.out::println);
    assertEquals(5, nextList.size());
  }
}