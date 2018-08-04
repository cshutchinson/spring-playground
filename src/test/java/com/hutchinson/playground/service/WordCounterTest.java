package com.hutchinson.playground.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

class WordCounterTest {
  private WordCounter wordCounter;

  @BeforeEach
  void setUp() {
    wordCounter= new WordCounter();
  }

  @Test
  void count_returnsTheExpectedMap() {
    String inputString = "This is a test of this method";
    Map<String, Integer> expected = new HashMap<>();
    expected.put("this", 2);
    expected.put("is", 1);
    expected.put("a", 1);
    expected.put("of", 1);
    expected.put("method", 1);
    expected.put("test", 1);

    Map<String, Integer> actual = wordCounter.count(inputString);

    assertTrue(expected.entrySet().containsAll(actual.entrySet()));
  }

  @Test
  void count_returnsTheExpectedMapLessAnyPunctuationMarks() {
    String inputString = "?This is a, test ,of this method!";
    Map<String, Integer> expected = new HashMap<>();
    expected.put("this", 2);
    expected.put("is", 1);
    expected.put("a", 1);
    expected.put("of", 1);
    expected.put("method", 1);
    expected.put("test", 1);

    Map<String, Integer> actual = wordCounter.count(inputString);

    assertTrue(expected.entrySet().containsAll(actual.entrySet()));
  }
}