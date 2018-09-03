package com.hutchinson.playground.controller;

import com.hutchinson.playground.service.WordCounter;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
public class WordControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private WordCounter wordCounter;

  @BeforeEach
  void setUp() {

  }

  @Test
  @Transactional
  @Rollback
  public void wordCount_returnsCorrectResponse() throws Exception{
    Map<String, Integer> wordCount = new HashMap<>();
    wordCount.put("the", 2);

    when(wordCounter.count(any())).thenReturn(wordCount);

    MockHttpServletRequestBuilder request = post("/words/count")
      .contentType(MediaType.APPLICATION_JSON)
      .content("The the");

    this.mvc.perform(request)
      .andDo(print())
      .andExpect(status().isOk());
  }
}