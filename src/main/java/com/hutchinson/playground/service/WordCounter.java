package com.hutchinson.playground.service;

import com.hutchinson.playground.config.WordCountConfig;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static java.util.AbstractMap.SimpleEntry;
import static java.util.stream.Collectors.toMap;

@Component
public class WordCounter {
  private final WordCountConfig wordCount;

  public WordCounter(WordCountConfig wordCount) {
    this.wordCount = wordCount;
  }

  public Map<String, Integer> count(String input) {
    boolean caseSensitive = wordCount.isCaseSensitive();
    List<String> skip = wordCount.getWords().getSkip();

    return Arrays.stream(input.replaceAll("[^a-zA-Z ]", "").split(" "))
      .map(elem -> this.mapToLowerCase(elem, caseSensitive))
      .filter(elem -> !skip.contains(elem))
      .map(word -> new SimpleEntry<>(word, 1))
      .collect(toMap(SimpleEntry::getKey, SimpleEntry::getValue, this::sum));
  }

  private int sum(Integer i1, Integer i2) {
    return i1 + i2;
  }

  private String mapToLowerCase(String input, boolean caseSensitive){
    if(caseSensitive){
      return input;
    } else {
      return input.toLowerCase();
    }
  }
}