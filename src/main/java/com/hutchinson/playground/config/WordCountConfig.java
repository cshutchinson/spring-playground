package com.hutchinson.playground.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties("wordcount")
public class WordCountConfig {
  private Words words;
  private boolean caseSensitive;

  public boolean isCaseSensitive() {
    return caseSensitive;
  }

  public Words getWords() {
    return words;
  }

  public void setWords(Words words) {
    this.words = words;
  }

  public void setCaseSensitive(boolean caseSensitive) {
    this.caseSensitive = caseSensitive;
  }

  public static class Words {
    private List<String> skip;

    public List<String> getSkip() {
      return skip;
    }

    public void setSkip(List<String> skip) {
      this.skip = skip;
    }
  }

}
