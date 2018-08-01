package com.hutchinson.playground.controller;

import com.hutchinson.playground.model.Lesson;
import com.hutchinson.playground.repo.LessonRepository;
import org.junit.Test;
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

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LessonControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private LessonRepository lessonRepository;

  @Test
  @Transactional
  @Rollback
  public void get_returnsCorrectResponse() throws Exception{
    Lesson lesson = new Lesson("Don't Read This Book", new Date(1522550617000L));
    lesson.setId(1L);
    when(lessonRepository.findAll()).thenReturn(Collections.singletonList(lesson));

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
    Lesson lesson = new Lesson("Read this", new Date(1522550617000L));
    lesson.setId(1L);
    when(lessonRepository.save(any())).thenReturn(lesson);

    MockHttpServletRequestBuilder request = post("/lessons")
      .contentType(MediaType.APPLICATION_JSON)
      .content("{\"title\": \"Read this\"}");

    this.mvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id", instanceOf(Number.class)))
      .andExpect(jsonPath("$.title", equalTo("Read this")));
  }

  @Test
  @Transactional
  @Rollback
  public void patch_updatesAnExistingRecord() throws Exception{
    Lesson lesson = new Lesson(1L,"Don't Read This Book", new Date(1522550617000L));

    when(lessonRepository.findById(any())).thenReturn(Optional.of(lesson));
    when(lessonRepository.save(any())).thenReturn(lesson);

    String uri = String.format("/lessons/%s", lesson.getId());

    MockHttpServletRequestBuilder request = patch(uri)
      .contentType(MediaType.APPLICATION_JSON)
      .content("{\"title\": \"Please Don't Read This Book\", \"deliveredOn\": \"2018-04-02\"}");

    this.mvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.title", equalTo("Please Don't Read This Book")))
      .andExpect(jsonPath("$.deliveredOn", equalTo("2018-04-02")));
  }
}
