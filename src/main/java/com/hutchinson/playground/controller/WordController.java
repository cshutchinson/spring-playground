package com.hutchinson.playground.controller;

import com.hutchinson.playground.service.WordCounter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/words")
public class WordController {

  private WordCounter wordCounter;

  public WordController(WordCounter wordCounter) {
    this.wordCounter = wordCounter;
  }

  @PostMapping("/count")
  public Map<String, Integer> count(@RequestBody String body){
    return wordCounter.count(body);
  }
}
