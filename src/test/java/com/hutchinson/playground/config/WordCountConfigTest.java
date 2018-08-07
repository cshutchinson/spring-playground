package com.hutchinson.playground.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
  "wordcount.words.skip[0]=Hello",
  "wordcount.words.skip[1]=There",
  "wordcount.caseSensitive=true"
})
public class WordCountConfigTest {
  @Autowired
  private WordCountConfig wordCountConfig;

  @Test
  public void testPropertiesAreMappedCorrectly(){
    assertThat(wordCountConfig.isCaseSensitive(), equalTo(true));
    assertThat(wordCountConfig.getWords().getSkip(), containsInAnyOrder("There", "Hello" ));
  }

}