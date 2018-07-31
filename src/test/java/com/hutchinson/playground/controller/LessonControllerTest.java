package com.hutchinson.playground.controller;

import com.hutchinson.playground.model.Lesson;
import com.hutchinson.playground.repo.LessonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LessonControllerTest {
  @Autowired
  MockMvc mvc;

  @Autowired
  LessonRepository lessonRepository;

  @Test
  @Transactional
  @Rollback
  public void get_returnsCorrectResponse() throws Exception{
    Lesson lesson = new Lesson("Don't Read This Book", new Date(1522550617000L));
    lessonRepository.save(lesson);

    MockHttpServletRequestBuilder request = get("/lessons")
      .contentType(MediaType.APPLICATION_JSON);

    this.mvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].id", instanceOf(Number.class)))
      .andExpect(jsonPath("$[0].title", equalTo("Don't Read This Book")))
      .andExpect(jsonPath("$[0].deliveredOn", equalTo("2018-04-01")));
  }

  @Test
  @Transactional
  @Rollback
  public void post_insertRecordIntoDb() throws Exception{
    MockHttpServletRequestBuilder request = post("/lessons")
      .contentType(MediaType.APPLICATION_JSON)
      .content("{\"title\": \"Read this\"}");

    this.mvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id", instanceOf(Number.class)))
      .andExpect(jsonPath("$.title", equalTo("Read this")));





  }
}
