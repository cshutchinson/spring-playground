package com.hutchinson.playground.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordCounterTest {
  @Autowired
  private WordCounter wordCounter;

  @Test
  public void count_returnsTheExpectedMap() {
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
  public void count_returnsTheExpectedMapLessAnyPunctuationMarks() {
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

  @Test
  public void count_returnsCorrectResponseGivenAConfigClassWordCount() {
    String inputString = "The BROWN cow jumps over a brown fox";
    Map<String, Integer> expected = new HashMap<>();
    expected.put("brown", 2);
    expected.put("cow", 1);
    expected.put("jumps", 1);
    expected.put("over", 1);
    expected.put("fox", 1);

    Map<String, Integer> actual = wordCounter.count(inputString);
    System.out.println("actual = " + actual);
    assertTrue(expected.entrySet().containsAll(actual.entrySet()));
  }

  @Test
  public void count_returnsCorrectResponseGivenAnotherStringAndConfigClassWordCount() {
    String inputString = "To the moon, to the MOON";
    Map<String, Integer> expected = new HashMap<>();
    expected.put("moon", 2);
    expected.put("to", 2);

    Map<String, Integer> actual = wordCounter.count(inputString);
    System.out.println("actual = " + actual);
    assertTrue(expected.entrySet().containsAll(actual.entrySet()));
  }
}