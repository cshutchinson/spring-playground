package com.hutchinson.playground.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Map;

import static java.util.AbstractMap.SimpleEntry;
import static java.util.stream.Collectors.*;

@Configuration
public class WordCounter {

  public Map<String, Integer> count(String input) {
    return Arrays.stream(input.replaceAll("[^a-zA-Z ]", "").split(" "))
      .map(String::toLowerCase)
      .map(word -> new SimpleEntry<>(word, 1))
      .collect(toMap(SimpleEntry::getKey, SimpleEntry::getValue, this::sum));
  }

  private int sum(Integer i1, Integer i2) {
    return i1 + i2;
  }
}