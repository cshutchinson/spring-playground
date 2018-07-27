package com.hutchinson.playground.controller;

import com.hutchinson.playground.model.Lesson;
import com.hutchinson.playground.repo.LessonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class LessonController {
  private final LessonRepository lessonRepository;

  public LessonController(LessonRepository lessonRepository) {
    this.lessonRepository = lessonRepository;
  }

  @GetMapping("")
  public Iterable<Lesson> all(){
    return this.lessonRepository.findAll();
  }

  @PostMapping("")
  public Lesson create(@RequestBody Lesson lesson){
    return this.lessonRepository.save(lesson);
  }

  @GetMapping("/{id}")
  public Optional<Lesson> getOne(@PathVariable Long id){
    return this.lessonRepository.findById(id);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id){
    this.lessonRepository.deleteById(id);
  }
}
